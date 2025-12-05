package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;

/**
 * @author EndsM
 */
@Mixin(PlayerEntity.class)
public abstract class MixinStorageAdapter implements StorageAdapter {
    @Unique
    private static final String NBT_KEY = "shurlin_cultivation";

    // This will be stored in memory while the player is active
    // And initialized while read, stored back to player data while write
    @Unique
    private CultivatedPlayer cultivatedPlayer;

    /**
     * Initialize the CultivatedPlayer object when the PlayerEntity is created.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        cultivatedPlayer = new CultivatedPlayer();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToNbt(NbtCompound nbt, CallbackInfo ci) {
        // This is triggered when a player logs off or saves.
        if (cultivatedPlayer != null) {
            NbtCompound tag = new NbtCompound();

            tag.putString("TypeId", cultivatedPlayer.getCultivationTypeId().toString());
            tag.putInt("MajorIndex", cultivatedPlayer.getMajorRealmIndex());
            tag.putInt("MinorIndex", cultivatedPlayer.getMinorRealmIndex());
            tag.putDouble("Progress", cultivatedPlayer.getCurrentProgress());
            tag.putBoolean("Bottleneck", cultivatedPlayer.isBottlenecked());

            nbt.put(NBT_KEY, tag);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readFromNbt(NbtCompound nbt, CallbackInfo ci) {
        // This is triggered when a player enters a world.
        if (nbt.contains(NBT_KEY)) {
            NbtCompound tag = nbt.getCompound(NBT_KEY);
            // Restore state using ID
            try {
                cultivatedPlayer.setCultivationTypeId(new Identifier(tag.getString("TypeId")));
                cultivatedPlayer.setMajorRealmIndex(tag.getInt("MajorIndex"));
                cultivatedPlayer.setMinorRealmIndex(tag.getInt("MinorIndex"));
                cultivatedPlayer.setCurrentProgress(tag.getDouble("Progress"));
                cultivatedPlayer.setBottlenecked(tag.getBoolean("Bottleneck"));
            } catch (Exception e) {
                Shurlin.LOGGER.error("Failed to load cultivation data", e);
                // Fallback reset if data is corrupted
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
        // Reset all progress when switching cultivation paths
        this.cultivatedPlayer.setMajorRealmIndex(0);
        this.cultivatedPlayer.setMinorRealmIndex(0);
        this.cultivatedPlayer.setCurrentProgress(0);
        this.cultivatedPlayer.setBottlenecked(false);
    }

    @Override
    public double GetMaxProgress() {
        CultivationRealm realmDef = cultivatedPlayer.getCurrentRealmDefinition();
        if (realmDef == null) return Double.MAX_VALUE; // Prevent division by zero or logic errors
        return realmDef.getRequirement(cultivatedPlayer.getMinorRealmIndex());
    }

    @Override
    public boolean AddCultivationProgress(double amount) {
        CultivationType type = cultivatedPlayer.getCultivationType();

        // 1. Validation: No type selected or invalid type
        if (type == null) return false;

        // 2. Check Bottleneck: Cannot gain XP if stuck at major realm peak
        if (cultivatedPlayer.isBottlenecked()) {
            notifyPlayer("§c[Cultivation] You have reached a bottleneck. You must find a way to break through!", true);
            return false;
        }

        double current = cultivatedPlayer.getCurrentProgress();
        double max = GetMaxProgress();
        double newTotal = current + amount;

        // 3. Handle Level Up Logic
        if (newTotal >= max) {
            double overflow = newTotal - max;
            CultivationRealm realmDef = cultivatedPlayer.getCurrentRealmDefinition();

            if (realmDef == null) return false;

            int currentMinor = cultivatedPlayer.getMinorRealmIndex();
            int maxMinor = realmDef.getMaxMinorStages();

            // Check if there is a next minor stage in the current major realm
            if (currentMinor < maxMinor - 1) {
                // Case A: Minor Realm Advancement (e.g., Qi Condensation 1 -> 2)
                cultivatedPlayer.setMinorRealmIndex(currentMinor + 1);
                cultivatedPlayer.setCurrentProgress(0); // Reset base

                notifyLevelUp(false);

                // Recursively add overflow to handle multi-level up in one go
                if (overflow > 0) {
                    AddCultivationProgress(overflow);
                }
            } else {
                // Case B: Reached the peak of Major Realm (e.g., Qi Condensation 9 -> Bottleneck)
                cultivatedPlayer.setCurrentProgress(max); // Cap at max visual
                cultivatedPlayer.setBottlenecked(true);
                notifyBottleneck();
            }
        } else {
            // Case C: Normal Progress
            cultivatedPlayer.setCurrentProgress(newTotal);
        }

        // TODO: Sync packet to client if needed
        return true;
    }

    @Override
    public boolean AttemptBreakthrough() {
        // Can only breakthrough if at a bottleneck
        if (!cultivatedPlayer.isBottlenecked()) return false;

        CultivationType type = cultivatedPlayer.getCultivationType();
        if (type == null) return false;

        // TODO: Check for Breakthrough Items or Environmental Conditions here via API callbacks

        // Logic for advancing Major Realm
        int currentMajor = cultivatedPlayer.getMajorRealmIndex();
        int maxMajor = type.getRealmCount();

        // Check if there is a next major realm
        if (currentMajor < maxMajor - 1) {
            // Success: Advance to next Major Realm
            cultivatedPlayer.setMajorRealmIndex(currentMajor + 1);
            cultivatedPlayer.setMinorRealmIndex(0);
            cultivatedPlayer.setCurrentProgress(0);
            cultivatedPlayer.setBottlenecked(false);

            notifyLevelUp(true);
            return true;
        } else {
            // Player has reached the absolute peak of this cultivation system (End Game)
            notifyPlayer("§6[Cultivation] You have reached the apex of this path!", false);
            return false;
        }
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

            // Different sounds for major/minor
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