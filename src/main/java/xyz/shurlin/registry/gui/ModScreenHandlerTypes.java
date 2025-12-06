package xyz.shurlin.registry.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.SimpleClientHandlerFactory;
import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.gui.CultivationInfoScreenHandler;
import xyz.shurlin.screen.BiggerContainerScreenHandler;
import xyz.shurlin.screen.worker.BreakerScreenHandler;
import xyz.shurlin.screen.worker.CollectorScreenHandler;
import xyz.shurlin.screen.worker.ConcentratorScreenHandler;
import xyz.shurlin.screen.worker.ExtractorScreenHandler;

public class ModScreenHandlerTypes {
    public static ScreenHandlerType<CultivationInfoScreenHandler> CULTIVATION_INFO = new ExtendedScreenHandlerType<>(CultivationInfoScreenHandler::new);

    // --- Migrated Worker Handlers (Simple) ---
    public static ScreenHandlerType<BreakerScreenHandler> BREAKER_SCREEN_HANDLER_TYPE;
    public static ScreenHandlerType<CollectorScreenHandler> COLLECTOR_SCREEN_HANDLER_TYPE;
    public static ScreenHandlerType<ConcentratorScreenHandler> CONCENTRATOR_SCREEN_HANDLER_TYPE;
    public static ScreenHandlerType<ExtractorScreenHandler> EXTRACTOR_SCREEN_HANDLER_TYPE;

    // --- Migrated Container Handlers ---
    public static ScreenHandlerType<BiggerContainerScreenHandler> BIGGER_CONTAINER_SCREEN_HANDLER_TYPE;


    private static <T extends ScreenHandler> ScreenHandlerType<T> registerSimple(String path, SimpleClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(Shurlin.MODID, path), factory);
    }

    public static void Register() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(Shurlin.MODID, "cultivation_info"), CULTIVATION_INFO);

        // Register Workers (Simple)
        BREAKER_SCREEN_HANDLER_TYPE = registerSimple("breaker", BreakerScreenHandler::new);
        COLLECTOR_SCREEN_HANDLER_TYPE = registerSimple("collector", CollectorScreenHandler::new);
        CONCENTRATOR_SCREEN_HANDLER_TYPE = registerSimple("concentrator", ConcentratorScreenHandler::new);
        EXTRACTOR_SCREEN_HANDLER_TYPE = registerSimple("extractor", ExtractorScreenHandler::new);

        // Register Container
        BIGGER_CONTAINER_SCREEN_HANDLER_TYPE = registerSimple("bigger_container", BiggerContainerScreenHandler::new);
    }
}
