package xyz.shurlin.registry.gui;

import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.gui.CultivationInfoScreenHandler;

public class ModScreenHandlerTypes {
    public static ScreenHandlerType<CultivationInfoScreenHandler> CULTIVATION_INFO = new ExtendedScreenHandlerType<>((syncId, inventory, buf) -> new CultivationInfoScreenHandler(syncId,inventory.player));

    public static void Register() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(Shurlin.MODID, "cultivation_info"), CULTIVATION_INFO);
    }
}
