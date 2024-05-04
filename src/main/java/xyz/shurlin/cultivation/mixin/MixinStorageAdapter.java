package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;

import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class MixinStorageAdapter implements StorageAdapter {
    @Shadow
    public abstract void resetLastAttackedTicks();

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToNbt(NbtCompound nbt, CallbackInfo ci) {
    }

    @Inject(method = "readCustomDataFromNbt", at=@At("TAIL"))
    private void readFromNbt(NbtCompound nbt, CallbackInfo ci) {
    }

    @Override
    public boolean SaveCultivationType() {
        // To be implemented
        return false;
    }

    @Override
    public CultivationType LoadCultivationType() {
        return null;
    }

    @Override
    public boolean SaveCultivationStages() {
        return false;
    }

    @Override
    public List<CultivationRealm> LoadCultivationStages() {
        return Collections.emptyList();
    }
}
