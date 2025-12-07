package xyz.shurlin.cultivation.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.shurlin.Shurlin;
import xyz.shurlin.client.options.KeyBindings;
import xyz.shurlin.util.Utils;

import java.util.List;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class CultivationInfoScreen extends HandledScreen<CultivationInfoScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier(Shurlin.MODID, "textures/gui/cultivation_info.png");
    private static final int TextColor = 0xffffff;
    private static final int HighlightColor = 0x66ccff;

    // Scroll/List variables
    private int scrollOffset = 0;
    private static final int LIST_X = 160; // Right side of GUI
    private static final int LIST_Y = 20;
    private static final int ITEM_HEIGHT = 12;
    private static final int MAX_VISIBLE_ITEMS = 8;

    public CultivationInfoScreen(CultivationInfoScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 256;
        this.backgroundHeight = 200;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);

        // Render Technique List (Basic Implementation)
        drawTechniqueList(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.client != null) {
            this.client.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        }
        this.drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        int centerX = this.backgroundWidth / 2;

        drawCenteredText(matrices, textRenderer, Text.of("Path: " + handler.getTypeName()), 80, 30, TextColor);
        drawCenteredText(matrices, textRenderer, Text.of("Realm: " + handler.getRealmName()), 80, 45, TextColor);

        String progressText = String.format("%.1f%%", handler.getProgressPercentage() * 100);
        drawCenteredText(matrices, textRenderer, Text.of(progressText), 80, 75, HighlightColor);

        // Label for list
        textRenderer.draw(matrices, "Techniques:", LIST_X, 10, TextColor);
    }

    private void drawTechniqueList(MatrixStack matrices, int mouseX, int mouseY) {
        List<CultivationInfoScreenHandler.TechniqueSummary> techs = handler.getLearnedTechniques();

        for (int i = 0; i < MAX_VISIBLE_ITEMS; i++) {
            int index = scrollOffset + i;
            if (index >= techs.size()) break;

            CultivationInfoScreenHandler.TechniqueSummary tech = techs.get(index);
            int yPos = this.y + LIST_Y + (i * ITEM_HEIGHT);
            int xPos = this.x + LIST_X;

            boolean isActive = tech.id.equals(handler.getActiveTechniqueId());
            boolean isHovered = isMouseOverItem(mouseX, mouseY, i);

            // Draw selection box
            if (isActive) {
                fill(matrices, xPos - 2, yPos - 2, xPos + 80, yPos + ITEM_HEIGHT - 2, 0x4000FF00); // Green Highlight
            } else if (isHovered) {
                fill(matrices, xPos - 2, yPos - 2, xPos + 80, yPos + ITEM_HEIGHT - 2, 0x40FFFFFF); // White Hover
            }

            Text name = new LiteralText(tech.name).formatted(tech.color);
            textRenderer.draw(matrices, name, xPos, yPos, 0xFFFFFF);
            if (isActive) {
                textRenderer.draw(matrices, "§a[Active]", xPos, yPos + 10, 0xFFFFFF); // Small indicator below if needed, layout might overlap
            }
        }
    }

    private boolean isMouseOverItem(int mouseX, int mouseY, int index) {
        int yPos = this.y + LIST_Y + (index * ITEM_HEIGHT);
        int xPos = this.x + LIST_X;
        return mouseX >= xPos - 2 && mouseX <= xPos + 80 && mouseY >= yPos - 2 && mouseY < yPos + ITEM_HEIGHT - 2;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left Click
            List<CultivationInfoScreenHandler.TechniqueSummary> techs = handler.getLearnedTechniques();
            for (int i = 0; i < MAX_VISIBLE_ITEMS; i++) {
                int index = scrollOffset + i;
                if (index >= techs.size()) break;

                if (isMouseOverItem((int) mouseX, (int) mouseY, i)) {
                    // Send packet to select
                    UUID selectedId = techs.get(index).id;
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeUuid(selectedId);
                    ClientPlayNetworking.send(Utils.SELECT_TECHNIQUE, buf);

                    // Optimistic update (handler will be refreshed on reopen, but visual feedback is good)
                    // Note: Handler active ID update strictly depends on packet/constructor usually,
                    // but we can't easily set it here without public setter. Ideally, close/reopen or wait for server ack.
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount > 0 && scrollOffset > 0) {
            scrollOffset--;
            return true;
        } else if (amount < 0 && scrollOffset < handler.getLearnedTechniques().size() - MAX_VISIBLE_ITEMS) {
            scrollOffset++;
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeyBindings.open_cul_menu.matchesKey(keyCode, scanCode)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
