package xyz.shurlin.cultivation.dynamic;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public enum TechniqueGrade {
    YELLOW(0, "grade.shurlin.yellow", 2, Formatting.YELLOW),
    BLACK(1, "grade.shurlin.black", 5, Formatting.BLUE),
    EARTH(2, "grade.shurlin.earth", 8, Formatting.DARK_PURPLE),
    HEAVEN(3, "grade.shurlin.heaven", 11, Formatting.GOLD);

    private final int id;
    private final String translationKey;
    // The maximum realm can this technique progress to
    private final int maxRealmIndex;
    private final Formatting color;

    TechniqueGrade(int id, String translationKey, int maxRealmIndex, Formatting color) {
        this.id = id;
        this.translationKey = translationKey;
        this.maxRealmIndex = maxRealmIndex;
        this.color = color;
    }

    public int getMaxRealmIndex() {
        return maxRealmIndex;
    }

    public Formatting getColor() {
        return color;
    }

    public String getName() {
        return translationKey;
    }

    public Text getNameText() {
        return new TranslatableText(translationKey);
    }
}
