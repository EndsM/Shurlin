package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.mixin.MixinStorageAdapter;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class CultivationInfoScreenHandler extends ScreenHandler {
    StorageAdapter adapter = new MixinStorageAdapter() {
    };
    CultivationType cultivationType;

    public CultivationInfoScreenHandler(int syncId) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
        cultivationType = adapter.LoadCultivationType();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
