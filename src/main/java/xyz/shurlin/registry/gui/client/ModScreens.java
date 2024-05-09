package xyz.shurlin.registry.gui.client;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import xyz.shurlin.cultivation.gui.CultivationInfoScreen;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class ModScreens {
    public static void Register(){
         ScreenRegistry.register(ModScreenHandlerTypes.CULTIVATION_INFO, CultivationInfoScreen::new);

    }

}
