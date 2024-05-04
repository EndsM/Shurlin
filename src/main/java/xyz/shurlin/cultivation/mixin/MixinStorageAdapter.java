package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class MixinStorageAdapter implements StorageAdapter {
    private static final String namespace = "cultivation";

    @Unique
    private CultivatedPlayer cultivatedPlayer ;
    MixinStorageAdapter(){
        this.cultivatedPlayer = new CultivatedPlayer();
        this.cultivatedPlayer.SetCultivationType(CultivationType.SHURLIN); // Initialize with a default cultivation type
        this.cultivatedPlayer.SetCultivationStages(Collections.singletonMap(1, new CultivationRealm())); // Initialize with a default cultivation stage
        this.cultivatedPlayer.SetCurrentStage(0); // Initialize with a default current stage
        this.cultivatedPlayer.SetCurrentCulProgress(0.0); // Initialize with a default current cultivation progress
        this.cultivatedPlayer.SetRealmStage(RealmStage.LOW); // Initialize with a default realm stage

    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (cultivatedPlayer != null) {
            NbtCompound CultivationData = new NbtCompound();
            CultivationData.putString("CultivationType", cultivatedPlayer.GetCultivationType().name());
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
    }

    @Override
    public boolean SaveCultivationType(CultivationType cultivationType) {
        // To be implemented
        return false;
    }

    @Override
    public CultivationType LoadCultivationType() {
        return null;
    }

    @Override
    public boolean SaveCultivationStages(Map<Integer, CultivationRealm> cultivationStages) {
        return false;
    }

    @Override
    public List<CultivationRealm> LoadCultivationStages() {
        return Collections.emptyList();
    }
}
