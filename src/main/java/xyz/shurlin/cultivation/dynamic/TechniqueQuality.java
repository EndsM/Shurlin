package xyz.shurlin.cultivation.dynamic;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public enum TechniqueQuality {
    LOW("quality.shurlin.low"),
    MEDIUM("quality.shurlin.medium"),
    HIGH("quality.shurlin.high"),
    PERFECT("quality.shurlin.perfect");

    private final String translationKey;

    TechniqueQuality(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getName() {
        return translationKey;
    }

    public Text getNameText() {
        return new TranslatableText(translationKey);
    }
}
