package xyz.shurlin.cultivation.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

// HandledScreen and ScreenHandler in Minecraft have a close relationship and work together to provide a user interface for interacting with inventories or containers.
//
// HandledScreen:
// - HandledScreen is a class that extends Screen and provides additional functionality for screens that interact with inventories or containers.
// - It handles input events such as mouse clicks and key presses, and manages the rendering of items and slots within the screen.
// - HandledScreen is analogous to a "page" in a web application, as it represents the visual interface that the player interacts with.
//
// ScreenHandler:
// - ScreenHandler is a class that manages the interaction between the player and the HandledScreen.
// - It handles events related to inventory management, item transfers, and container interactions.
// - ScreenHandler is responsible for updating the HandledScreen with relevant information, such as inventory contents and slot highlights.
// - ScreenHandler is similar to a "controller" or "presenter" in a Model-View-Controller (MVC) pattern, as it handles the logic and data associated with the HandledScreen.
//
// Together, HandledScreen and ScreenHandler work in tandem to provide a seamless and interactive user experience for managing inventories or performing actions within the game.
// They ensure that the player's interactions are properly handled and that the screen is updated accordingly.
@Environment(EnvType.CLIENT)
public class CultivationInfoScreen extends HandledScreen<CultivationInfoScreenHandler> {
    CultivationInfoScreenHandler handler;

    public CultivationInfoScreen(CultivationInfoScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.handler = handler;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.clearColor(1, 1, 1, 1);
        drawCenteredText(matrices, textRenderer, Text.of("Hello World"), width / 2, 15, 0x66ccff);
        drawCenteredText(matrices, textRenderer, Text.of("Cultivation Type: " + handler.getCultivationType().name()), width / 2, 30, 0xffffff);
        drawCenteredText(matrices,textRenderer,Text.of("Cultivation Stage: "+handler.getCurrentRealm().getNameKey()),width/2,45,0xffffff);

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

    }
}
