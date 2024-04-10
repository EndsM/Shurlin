package xyz.shurlin.cultivation.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import xyz.shurlin.cultivation.CultivatedPlayerAccessor;

// This will be Cultivation Menu Screen
public class CultivationUI implements NamedScreenHandlerFactory {
    private static final TranslatableText TITLE = new TranslatableText("screen.shurlin.cul_screen");
private CultivatedPlayerAccessor accessor;

    public CultivationUI(CultivatedPlayerAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CultivationScreenHandler(syncId, ((CultivatedPlayerAccessor) player).getRealm());
    }
}
