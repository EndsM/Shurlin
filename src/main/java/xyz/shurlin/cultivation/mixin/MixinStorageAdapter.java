package xyz.shurlin.cultivation.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;

import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public class MixinStorageAdapter implements StorageAdapter {
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
