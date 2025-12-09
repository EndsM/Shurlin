package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.interfaces.CultivationLogic;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.*;
import xyz.shurlin.cultivation.world.TechniqueManager;
import xyz.shurlin.registry.ModElements;

import java.util.Map;
import java.util.UUID;

/**
 * @author EndsM
 */
@Mixin(PlayerEntity.class)
public abstract class MixinStorageAdapter implements StorageAdapter {
    @Unique
    private static final String NBT_KEY = "shurlin_cultivation";

    @Unique
    private CultivatedPlayer cultivatedPlayer;

    // IDs for modifiers
    // Use UUID just for now, it seems we need wait to 1.21 to make it neat and clean...
    @Unique
    private static final UUID HEALTH_MODIFIER_ID = UUID.nameUUIDFromBytes("shurlin:cultivation_health".getBytes());


    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        cultivatedPlayer = new CultivatedPlayer();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // Only calc for server-side
        if (!player.world.isClient && cultivatedPlayer != null) {
            GeneratedTechnique activeTech = null;
            UUID activeId = cultivatedPlayer.getActiveTechniqueId();

            if (activeId != null && player.world instanceof ServerWorld) {
                // Resolve the technique from the Manager
                activeTech = TechniqueManager.getServerInstance((ServerWorld) player.world).getTechnique(activeId);
            }

            cultivatedPlayer.tick(activeTech);
        }
    }

    @Unique
    private void updateCultivationStats(PlayerEntity player) {
        CultivationType type = cultivatedPlayer.getCultivationType();
        if (type == null || type.getLogic() == null) return;

        CultivationRealm realm = cultivatedPlayer.getCurrentRealmDefinition();
        if (realm == null) return;

        CultivationLogic logic = type.getLogic();

        double healthBonus = logic.CalculateHealthBonus(cultivatedPlayer, realm);

        // Apply Health
        applyAttributeModifier(
                player,
                EntityAttributes.GENERIC_MAX_HEALTH,
                HEALTH_MODIFIER_ID,
                "Shurlin Cultivation Health",
                healthBonus,
                EntityAttributeModifier.Operation.ADDITION
        );
    }


    @Unique
    private void applyAttributeModifier(PlayerEntity player, EntityAttribute attribute, UUID uuid, String name, double value, EntityAttributeModifier.Operation operation) {
        EntityAttributeInstance instance = player.getAttributeInstance(attribute);
        if (instance != null) {
            EntityAttributeModifier existing = instance.getModifier(uuid);

            // Only remove/add if the value actually changed.
            // This prevents lag and unnecessary packet syncing.
            if (existing != null) {
                if (Math.abs(existing.getValue() - value) < 0.01) {
                    return;
                }
                instance.removeModifier(uuid);
            }

            if (value > 0) {
                instance.addPersistentModifier(new EntityAttributeModifier(uuid, name, value, operation));

                // If max health increases, heal the difference so the hearts aren't empty
                if (attribute == EntityAttributes.GENERIC_MAX_HEALTH) {
                    if (player.getHealth() > player.getMaxHealth()) {
                        player.setHealth(player.getMaxHealth());
                    }
                }
            }
        }
    }


    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (cultivatedPlayer != null) {
            NbtCompound tag = new NbtCompound();

            tag.putString("TypeId", cultivatedPlayer.getCultivationTypeId().toString());
            tag.putInt("MajorIndex", cultivatedPlayer.getMajorRealmIndex());
            tag.putInt("MinorIndex", cultivatedPlayer.getMinorRealmIndex());
            tag.putDouble("Progress", cultivatedPlayer.getCurrentProgress());
            tag.putBoolean("Bottleneck", cultivatedPlayer.isBottlenecked());
            tag.putDouble("CurrentQi", cultivatedPlayer.getCurrentQi());

            if (cultivatedPlayer.getActiveTechniqueId() != null) {
                tag.putUuid("ActiveTechnique", cultivatedPlayer.getActiveTechniqueId());
            }

            // Save Learned Techniques
            NbtList learnedList = new NbtList();
            for (UUID id : cultivatedPlayer.getLearnedTechniques()) {
                learnedList.add(NbtHelper.fromUuid(id));
            }
            tag.put("LearnedTechniques", learnedList);

            // Save Spirit Roots
            NbtCompound rootsTag = new NbtCompound();
            for (Map.Entry<Identifier, Integer> entry : cultivatedPlayer.getSpiritRoots().entrySet()) {
                rootsTag.putInt(entry.getKey().toString(), entry.getValue());
            }
            tag.put("SpiritRoots", rootsTag);

            nbt.put(NBT_KEY, tag);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(NBT_KEY)) {
            NbtCompound tag = nbt.getCompound(NBT_KEY);
            try {
                cultivatedPlayer.setCultivationTypeId(new Identifier(tag.getString("TypeId")));
                cultivatedPlayer.setMajorRealmIndex(tag.getInt("MajorIndex"));
                cultivatedPlayer.setMinorRealmIndex(tag.getInt("MinorIndex"));
                cultivatedPlayer.setCurrentProgress(tag.getDouble("Progress"));
                cultivatedPlayer.setBottlenecked(tag.getBoolean("Bottleneck"));
                cultivatedPlayer.setCurrentQi(tag.getDouble("CurrentQi"));

                if (tag.contains("ActiveTechnique")) {
                    cultivatedPlayer.setActiveTechniqueId(tag.getUuid("ActiveTechnique"));
                } else if (tag.contains("ActiveTechniqueOld")) {
                    // Backwards compatibility if needed, otherwise safe to ignore
                }

                if (tag.contains("LearnedTechniques")) {
                    NbtList list = tag.getList("LearnedTechniques", 11); // 11 is IntArray (UUID)
                    for (NbtElement nbtElement : list) {
                        cultivatedPlayer.learnTechnique(NbtHelper.toUuid(nbtElement));
                    }
                }

                if (tag.contains("SpiritRoots")) {
                    NbtCompound rootsTag = tag.getCompound("SpiritRoots");
                    for (String key : rootsTag.getKeys()) {
                        Identifier elementId = new Identifier(key);
                        int value = rootsTag.getInt(key);
                        for (SpiritElement el : ModElements.SHURLIN_ELEMENTS) {
                            if (el.getId().equals(elementId)) {
                                cultivatedPlayer.setRootValue(el, value);
                                break;
                            }
                        }
                    }
                }

                updateCultivationStats((PlayerEntity) (Object) this);
            } catch (Exception e) {
                Shurlin.LOGGER.error("Failed to load cultivation data", e);
                cultivatedPlayer = new CultivatedPlayer();
            }
        }
    }

    // =========================================================
    // Interface Implementation
    // =========================================================

    @Override
    public CultivatedPlayer GetCultivatedPlayer() {
        return cultivatedPlayer;
    }

    @Override
    public void SetCultivationType(Identifier typeId) {
        this.cultivatedPlayer.setCultivationTypeId(typeId);
        this.cultivatedPlayer.setMajorRealmIndex(0);
        this.cultivatedPlayer.setMinorRealmIndex(0);
        this.cultivatedPlayer.setCurrentProgress(0);
        this.cultivatedPlayer.setBottlenecked(false);
    }

    @Override
    public double GetMaxProgress() {
        CultivationRealm realmDef = cultivatedPlayer.getCurrentRealmDefinition();
        if (realmDef == null) return Double.MAX_VALUE;
        return realmDef.getRequirement(cultivatedPlayer.getMinorRealmIndex());
    }

    @Override
    public boolean AddCultivationProgress(double amount) {
        CultivationType type = cultivatedPlayer.getCultivationType();
        if (type == null) return false;

        if (cultivatedPlayer.isBottlenecked()) {
            notifyPlayer("§c[Cultivation] You have reached a bottleneck. You must find a way to break through!", true);
            return false;
        }

        double current = cultivatedPlayer.getCurrentProgress();
        double max = GetMaxProgress();
        double newTotal = current + amount;

        if (newTotal >= max) {
            double overflow = newTotal - max;
            CultivationRealm realmDef = cultivatedPlayer.getCurrentRealmDefinition();

            if (realmDef == null) return false;

            int currentMinor = cultivatedPlayer.getMinorRealmIndex();
            int maxMinor = realmDef.getMaxMinorStages();

            if (currentMinor < maxMinor - 1) {
                cultivatedPlayer.setMinorRealmIndex(currentMinor + 1);
                cultivatedPlayer.setCurrentProgress(0);
                updateCultivationStats((PlayerEntity) (Object) this);
                notifyLevelUp(false);
                if (overflow > 0) {
                    AddCultivationProgress(overflow);
                }
            } else {
                cultivatedPlayer.setCurrentProgress(max);
                cultivatedPlayer.setBottlenecked(true);
                notifyBottleneck();
            }
        } else {
            cultivatedPlayer.setCurrentProgress(newTotal);
        }
        return true;
    }

    @Override
    public boolean AttemptBreakthrough() {
        if (!cultivatedPlayer.isBottlenecked()) return false;

        CultivationType type = cultivatedPlayer.getCultivationType();
        if (type == null) return false;

        int currentMajor = cultivatedPlayer.getMajorRealmIndex();
        int maxMajor = type.getRealmCount();

        if (currentMajor < maxMajor - 1) {
            cultivatedPlayer.setMajorRealmIndex(currentMajor + 1);
            cultivatedPlayer.setMinorRealmIndex(0);
            cultivatedPlayer.setCurrentProgress(0);
            cultivatedPlayer.setBottlenecked(false);

            updateCultivationStats((PlayerEntity) (Object) this);
            notifyLevelUp(true);
            return true;
        } else {
            notifyPlayer("§6[Cultivation] You have reached the apex of this path!", false);
            return false;
        }
    }

    @Override
    public void LearnTechnique(UUID techniqueId) {
        if (!cultivatedPlayer.hasLearned(techniqueId)) {
            cultivatedPlayer.learnTechnique(techniqueId);
            // If it's the first one, set it active automatically
            if (cultivatedPlayer.getActiveTechniqueId() == null) {
                cultivatedPlayer.setActiveTechniqueId(techniqueId);
            }
        }
    }

    @Override
    public void SetActiveTechnique(UUID techniqueId) {
        if (cultivatedPlayer.hasLearned(techniqueId)) {
            cultivatedPlayer.setActiveTechniqueId(techniqueId);
            notifyPlayer("§a[Cultivation] Switched active technique.", true);
        }
    }

    @Override
    public void refreshStats() {
        updateCultivationStats((PlayerEntity) (Object) this);
    }

    // =========================================================
    // Helper Functions
    // =========================================================

    private void notifyLevelUp(boolean isMajor) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.world.isClient) {
            String msg = isMajor
                    ? "§6[Cultivation] §eBreakthrough Successful! You have entered a new Realm!"
                    : "§a[Cultivation] §fYour foundation deepens. Minor Realm Improved.";

            player.sendMessage(new LiteralText(msg), false);
            float pitch = isMajor ? 0.8f : 1.2f;
            player.world.playSound(null, player.getBlockPos(), net.minecraft.sound.SoundEvents.ENTITY_PLAYER_LEVELUP, net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, pitch);
        }
    }

    private void notifyBottleneck() {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.world.isClient) {
            player.sendMessage(new LiteralText("§c[Cultivation] You feel a barrier hindering your progress... (Bottleneck Reached)"), false);
            player.world.playSound(null, player.getBlockPos(), net.minecraft.sound.SoundEvents.BLOCK_ANVIL_LAND, net.minecraft.sound.SoundCategory.PLAYERS, 0.5f, 0.5f);
        }
    }

    private void notifyPlayer(String message, boolean actionBar) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.world.isClient) {
            player.sendMessage(new LiteralText(message), actionBar);
        }
    }

    @Override
    public boolean IsBottlenecked() {
        return cultivatedPlayer.isBottlenecked();
    }

    @Override
    public int GetMajorRealmIndex() {
        return cultivatedPlayer.getMajorRealmIndex();
    }

    @Override
    public int GetMinorRealmIndex() {
        return cultivatedPlayer.getMinorRealmIndex();
    }

    @Override
    public double GetProgress() {
        return cultivatedPlayer.getCurrentProgress();
    }
}