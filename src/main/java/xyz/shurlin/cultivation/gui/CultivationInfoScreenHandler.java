package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class CultivationInfoScreenHandler extends ScreenHandler {
    public CultivationInfoScreenHandler(int syncId) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
