package xyz.shurlin.registry.gui.client;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import xyz.shurlin.client.gui.screen.BiggerContainerScreen;
import xyz.shurlin.client.gui.screen.worker.BreakerScreen;
import xyz.shurlin.client.gui.screen.worker.CollectorScreen;
import xyz.shurlin.client.gui.screen.worker.ConcentratorScreen;
import xyz.shurlin.client.gui.screen.worker.ExtractorScreen;
import xyz.shurlin.cultivation.gui.CultivationInfoScreen;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class ModScreens {
    public static void Register() {
        // Workers
        ScreenRegistry.register(ModScreenHandlerTypes.BREAKER_SCREEN_HANDLER_TYPE, BreakerScreen::new);
        ScreenRegistry.register(ModScreenHandlerTypes.COLLECTOR_SCREEN_HANDLER_TYPE, CollectorScreen::new);
        ScreenRegistry.register(ModScreenHandlerTypes.CONCENTRATOR_SCREEN_HANDLER_TYPE, ConcentratorScreen::new);
        ScreenRegistry.register(ModScreenHandlerTypes.EXTRACTOR_SCREEN_HANDLER_TYPE, ExtractorScreen::new);

        // Container
        ScreenRegistry.register(ModScreenHandlerTypes.BIGGER_CONTAINER_SCREEN_HANDLER_TYPE, BiggerContainerScreen::new);

        // Cultivation Related
        ScreenRegistry.register(ModScreenHandlerTypes.CULTIVATION_INFO, CultivationInfoScreen::new);
    }
}
