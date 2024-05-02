package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;

@Mixin(PlayerEntity.class)
public class MixinStorageAdapter implements StorageAdapter {
    @Override
    public boolean WriteCultivationTypeToNBT(){
        // To be implemented
        return false;
    }
}
