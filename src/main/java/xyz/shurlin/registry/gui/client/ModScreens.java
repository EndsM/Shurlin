package xyz.shurlin.registry.gui.client;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import xyz.shurlin.client.gui.screen.BiggerContainerScreen;
import xyz.shurlin.client.gui.screen.worker.BreakerScreen;
import xyz.shurlin.client.gui.screen.worker.CollectorScreen;
import xyz.shurlin.client.gui.screen.worker.ConcentratorScreen;
import xyz.shurlin.client.gui.screen.worker.ExtractorScreen;
import xyz.shurlin.cultivation.gui.CultivationInfoScreen;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;
import xyz.shurlin.screen.ScreenHandlerTypes;

public class ModScreens {
    public static void Register() {
        // from mod original stuff
        ScreenRegistry.register(ScreenHandlerTypes.BREAKER_SCREEN_HANDLER_TYPE, BreakerScreen::new);
        ScreenRegistry.register(ScreenHandlerTypes.COLLECTOR_SCREEN_HANDLER_TYPE, CollectorScreen::new);
        ScreenRegistry.register(ScreenHandlerTypes.CONCENTRATOR_SCREEN_HANDLER_TYPE, ConcentratorScreen::new);
        ScreenRegistry.register(ScreenHandlerTypes.EXTRACTOR_SCREEN_HANDLER_TYPE, ExtractorScreen::new);
        ScreenRegistry.register(ScreenHandlerTypes.BIGGER_CONTAINER_SCREEN_HANDLER_TYPE, BiggerContainerScreen::new);
        // Cultivation Related
        ScreenRegistry.register(ModScreenHandlerTypes.CULTIVATION_INFO, CultivationInfoScreen::new);
    }
}
