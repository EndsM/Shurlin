package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class CultivationInfoScreenHandler extends ScreenHandler {
    private final StorageAdapter storageAdapter;
    private final CultivationType cultivationType;
    int currentStage;

    public CultivationInfoScreenHandler(int syncId, PlayerEntity player) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
        storageAdapter = (StorageAdapter) player;
        cultivationType = storageAdapter.LoadCultivationType();
        currentStage = storageAdapter.LoadCurrentStage();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public CultivationType getCultivationType() {
        return cultivationType;
    }

    public int getCurrentStage() {
        return currentStage;
    }
}
