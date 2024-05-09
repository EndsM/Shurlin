package xyz.shurlin.client.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import xyz.shurlin.Shurlin;
import xyz.shurlin.util.Utils;

@Environment(EnvType.CLIENT)
public class KeyBindings {
    public static KeyBinding perform_cul_act = new KeyBinding(
            "key.shurlin.perform_cul_act",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            "category." + Shurlin.MODID
    );
    public static KeyBinding open_cul_menu = new KeyBinding(
            "key.shurlin.open_cul_menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category." + Shurlin.MODID
    );
    public static KeyBinding infuse_spirit = new KeyBinding(
            "key.shurlin.infuse_spirit",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category." + Shurlin.MODID
    );

    public static void init() {
        KeyBindingHelper.registerKeyBinding(perform_cul_act);
        KeyBindingHelper.registerKeyBinding(open_cul_menu);
        KeyBindingHelper.registerKeyBinding(infuse_spirit);

        // Test act

        // This need fix later
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (open_cul_menu.isPressed()) {
                PacketByteBuf buffer = PacketByteBufs.create();
                ClientPlayNetworking.send(Utils.OPEN_CUL, buffer);
            }
        });
    }
}
