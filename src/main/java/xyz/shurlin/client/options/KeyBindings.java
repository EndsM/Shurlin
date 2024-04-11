package xyz.shurlin.client.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import xyz.shurlin.util.Utils;

@Environment(EnvType.CLIENT)
public class KeyBindings {
    public static KeyBinding keyBinding_j;
    public static KeyBinding open_cul;
    public static KeyBinding inject_spirit;


    public static void load() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (open_cul.isPressed()) {
                PacketByteBuf buffer = PacketByteBufs.create();
                ClientPlayNetworking.send(Utils.OPEN_CUL, buffer);
            }
        });
    }

    static {
        open_cul = new KeyBinding(
                "key.shurlin.open_cul",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "category.shurlin.open_cul"
        );
        inject_spirit = new KeyBinding(
                "key.shurlin.inject_spirit",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "category.shurlin.inject_spirit"
        );
    }
}
