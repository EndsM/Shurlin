package xyz.shurlin.cultivation.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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

    public enum Page {
        HUB,
        GENERAL,
        TECHNIQUES,
        SKILLS
    }

    private Page currentPage = Page.HUB;

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
        initPage();
    }

    private void initPage() {
        this.buttons.clear();

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        if (currentPage == Page.HUB) {
            // General Info Button
            this.addButton(new ButtonWidget(centerX - 50, centerY - 40, 100, 20, new TranslatableText("gui.shurlin.btn.general"), (btn) -> {
                this.currentPage = Page.GENERAL;
                this.initPage();
            }));

            // Techniques Button
            this.addButton(new ButtonWidget(centerX - 50, centerY - 15, 100, 20, new TranslatableText("gui.shurlin.btn.techniques"), (btn) -> {
                this.currentPage = Page.TECHNIQUES;
                this.initPage();
            }));

            // Skills Button (Placeholder)
            this.addButton(new ButtonWidget(centerX - 50, centerY + 10, 100, 20, new TranslatableText("gui.shurlin.btn.skills"), (btn) -> {
                this.currentPage = Page.SKILLS;
                this.initPage();
            }));

        } else {
            // --- SUB-SCREENS (ALL HAVE A BACK BUTTON) ---

            // Add a Back Button to return to Hub
            this.addButton(new ButtonWidget(this.x + 10, this.y + 10, 40, 20, new TranslatableText("gui.shurlin.btn.back"), (btn) -> {
                this.currentPage = Page.HUB;
                this.initPage();
            }));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);

        // Render logic specific to the page
        if (currentPage == Page.TECHNIQUES) {
            drawTechniqueList(matrices, mouseX, mouseY);
        }
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
        // Foreground rendering depends on Page
        if (currentPage == Page.HUB) {
            drawCenteredText(matrices, textRenderer, new TranslatableText("gui.shurlin.hub.title"), this.backgroundWidth / 2, 15, 0xFFFFFF);
        } else if (currentPage == Page.GENERAL) {
            drawGeneralInfo(matrices);
        } else if (currentPage == Page.TECHNIQUES) {
            drawTechniquesInfo(matrices);
        } else if (currentPage == Page.SKILLS) {
            drawCenteredText(matrices, textRenderer, new LiteralText("Coming Soon..."), this.backgroundWidth / 2, 80, 0xAAAAAA);
        }
    }

    private void drawGeneralInfo(MatrixStack matrices) {
        drawCenteredText(matrices, textRenderer, new TranslatableText("gui.shurlin.path", handler.getTypeName()), this.backgroundWidth / 2, 40, 0xFFFFFF);
        drawCenteredText(matrices, textRenderer, new TranslatableText("gui.shurlin.realm", handler.getRealmName()), this.backgroundWidth / 2, 55, 0xFFFFFF);

        String progressText = String.format("%.1f%%", handler.getProgressPercentage() * 100);
        drawCenteredText(matrices, textRenderer, new LiteralText(progressText), this.backgroundWidth / 2, 85, 0x66ccff);
    }

    private void drawTechniquesInfo(MatrixStack matrices) {
        // Label for list
        textRenderer.draw(matrices, new TranslatableText("gui.shurlin.techniques"), LIST_X, 10, 0xFFFFFF);
    }


    private void drawTechniqueList(MatrixStack matrices, int mouseX, int mouseY) {
        // Your existing technique drawing logic
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

            // Using formatting from summary
            Text name = new LiteralText(tech.name).formatted(tech.color);
            textRenderer.draw(matrices, name, xPos, yPos, 0xFFFFFF);

            if (isActive) {
                textRenderer.draw(matrices, new TranslatableText("gui.shurlin.active_tag"), xPos, yPos + 10, 0x00FF00);
            }
        }
    }

    private boolean isMouseOverItem(int mouseX, int mouseY, int index) {
        if (currentPage != Page.TECHNIQUES) return false;

        int yPos = this.y + LIST_Y + (index * ITEM_HEIGHT);
        int xPos = this.x + LIST_X;
        return mouseX >= xPos - 2 && mouseX <= xPos + 80 && mouseY >= yPos - 2 && mouseY < yPos + ITEM_HEIGHT - 2;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Only handle technique list clicks if on that page
        if (currentPage == Page.TECHNIQUES && button == 0) {
            List<CultivationInfoScreenHandler.TechniqueSummary> techs = handler.getLearnedTechniques();
            for (int i = 0; i < MAX_VISIBLE_ITEMS; i++) {
                int index = scrollOffset + i;
                if (index >= techs.size()) break;

                if (isMouseOverItem((int) mouseX, (int) mouseY, i)) {
                    UUID selectedId = techs.get(index).id;
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeUuid(selectedId);
                    ClientPlayNetworking.send(Utils.SELECT_TECHNIQUE, buf);
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
        // Allow pressing the KeyBinding ('O') to close the menu
        if (KeyBindings.open_cul_menu.matchesKey(keyCode, scanCode)) {
            this.onClose();
            return true;
        }

        if (keyCode == 256 && currentPage != Page.HUB) {
            currentPage = Page.HUB;
            initPage();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
