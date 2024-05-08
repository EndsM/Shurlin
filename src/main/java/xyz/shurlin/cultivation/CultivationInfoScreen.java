package xyz.shurlin.cultivation;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CultivationInfoScreen extends Screen {
    // About HandledScreen and ScreenHandler, they would look like razor page in ASP.NET.
    // HandledScreen is the page, and ScreenHandler is the PageModel ish thing
    protected CultivationInfoScreen(Text title) {
        super(title);
    }
}
