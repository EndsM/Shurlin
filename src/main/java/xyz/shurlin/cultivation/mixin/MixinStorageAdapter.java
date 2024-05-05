package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class MixinStorageAdapter implements StorageAdapter {
    @Shadow
    public abstract Text getName();

    @Unique
    private static final String namespace = "cultivation";

    // This will be stored in memory while the player is active
    // And initialize while read, store back to player data while write
    @Unique
    private CultivatedPlayer cultivatedPlayer;

    // <init> means this will be injected to constructor, so I can finally do dependency injection
    // in Java mixin now, I'm saved
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        cultivatedPlayer = new CultivatedPlayer();
        // Dummy data purely for test
        cultivatedPlayer.SetCultivationType(CultivationType.SHURLIN); // Initialize with a default cultivation type
        cultivatedPlayer.SetCultivationStages(Collections.singletonMap(1, new CultivationRealm())); // Initialize with a default cultivation stage
        cultivatedPlayer.SetCurrentStage(0); // Initialize with a default current stage
        cultivatedPlayer.SetCurrentCulProgress(0.0); // Initialize with a default current cultivation progress
        cultivatedPlayer.SetRealmStage(RealmStage.LOW); // Initialize with a default realm stage
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToNbt(NbtCompound nbt, CallbackInfo ci) {
        // This is triggered when a player logged off a world.
        // Also works while player pause the game in client env. maybe also timed save
        Shurlin.LOGGER.info("Try to save cultivation data for {}", getName());
        if (cultivatedPlayer != null) {
            NbtCompound CultivationData = new NbtCompound();
            CultivationData.putInt("CultivationType", cultivatedPlayer.GetCultivationType().getId());
            CultivationData.putInt("CurrentStage", cultivatedPlayer.GetCurrentStage());
            CultivationData.putDouble("CurrentCulProgress", cultivatedPlayer.GetCurrentCulProgress());
            CultivationData.putInt("RealmStage", cultivatedPlayer.GetRealmStage().getLevel());

            NbtCompound CultivationStagesNbt = new NbtCompound();
            for (Map.Entry<Integer, CultivationRealm> entry : cultivatedPlayer.GetCultivationStages().entrySet()) {
                NbtCompound realmNbt = new NbtCompound();
                realmNbt.putInt("Id", entry.getValue().getId());
                CultivationStagesNbt.put("Realm" + entry.getKey(), realmNbt);
            }
            CultivationData.put("CultivationStages", CultivationStagesNbt);
            nbt.put(namespace, CultivationData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readFromNbt(NbtCompound nbt, CallbackInfo ci) {
        // This is triggered when a player enters a world.
        Shurlin.LOGGER.info("Try to load cultivation data for {}", getName());
        NbtCompound CultivationData = nbt.getCompound(namespace);
        if (CultivationData != null) {
            cultivatedPlayer.SetCultivationType(CultivationType.getById(CultivationData.getInt("CultivationType")));
            cultivatedPlayer.SetCurrentStage(CultivationData.getInt("CurrentStage"));
            cultivatedPlayer.SetCurrentCulProgress(CultivationData.getDouble("CurrentCulProgress"));
            cultivatedPlayer.SetRealmStage(RealmStage.getByLevel(CultivationData.getInt("RealmStage")));

            NbtCompound CultivationStagesNbt = CultivationData.getCompound("CultivationStages");
            Map<Integer, CultivationRealm> cultivationStages = new HashMap<>();
            for (String key : CultivationStagesNbt.getKeys()) {
                // Check if this match the pattern while written
                if (key.startsWith("Realm")) {
                    int stageId = Integer.parseInt(key.substring(5));
                    NbtCompound realmNbt = CultivationStagesNbt.getCompound(key);
                    CultivationRealm realm = new CultivationRealm();
                    realm.setId(stageId);
                    // More data are to be impl
                    cultivationStages.put(stageId, realm);
                }
            }
            cultivatedPlayer.SetCultivationStages(cultivationStages);
        }
    }

    @Override
    public boolean SaveCultivationType(CultivationType cultivationType) {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return false;
        }

        try {
            cultivatedPlayer.SetCultivationType(cultivationType);
            return true;
        } catch (Exception e) {
            Shurlin.LOGGER.error("Error saving cultivation type: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public CultivationType LoadCultivationType() {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return null;
        }
        return cultivatedPlayer.GetCultivationType();
    }

    @Override
    public boolean SaveCultivationStages(Map<Integer, CultivationRealm> cultivationStages) {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return false;
        }

        try {
            cultivatedPlayer.SetCultivationStages(cultivationStages);
            return true;
        } catch (Exception e) {
            Shurlin.LOGGER.error("Error saving cultivation stages: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Map<Integer, CultivationRealm> LoadCultivationStages() {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return null;
        }
        return cultivatedPlayer.GetCultivationStages();
    }

    @Override
    public boolean SaveCurrentStage(int currentStage) {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return false;
        }

        try {
            cultivatedPlayer.SetCurrentStage(currentStage);
            return true;
        } catch (Exception e) {
            Shurlin.LOGGER.error("Error saving current stage: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public int LoadCurrentStage() {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return 0;
        }
        return cultivatedPlayer.GetCurrentStage();
    }

    @Override
    public boolean SaveCurrentCulProgress(double currentCulProgress) {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return false;
        }

        try {
            cultivatedPlayer.SetCurrentCulProgress(currentCulProgress);
            return true;
        } catch (Exception e) {
            Shurlin.LOGGER.error("Error saving current cultivation progress: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public double LoadCurrentCulProgress() {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return 0;
        }
        return cultivatedPlayer.GetCurrentCulProgress();
    }

    @Override
    public boolean SaveRealmStage(RealmStage realmStage) {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return false;
        }

        try {
            cultivatedPlayer.SetRealmStage(realmStage);
            return true;
        } catch (Exception e) {
            Shurlin.LOGGER.error("Error saving realm stage: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public RealmStage LoadRealmStage() {
        if (cultivatedPlayer == null) {
            Shurlin.LOGGER.error("Cultivated player is not initialized.");
            return null;
        }
        return cultivatedPlayer.GetRealmStage();
    }
}
