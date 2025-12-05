package xyz.shurlin.cultivation.models;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

// This class represent one stage in stages of a cultivation system
public class CultivationRealm {
    private final String translationKey;
    private final int maxMinorStages;
    private final double baseReq;
    private final double multiplier;

    public CultivationRealm(String translationKey, int maxMinorStages, double baseReq, double multiplier) {
        this.translationKey = translationKey;
        this.maxMinorStages = maxMinorStages;
        this.baseReq = baseReq;
        this.multiplier = multiplier;
    }

    public Text getName() {
        return new TranslatableText(translationKey);
    }

    public int getMaxMinorStages() {
        return maxMinorStages;
    }

    public double getRequirement(int minorStage) {
        return baseReq * Math.pow(multiplier, minorStage);
    }
}
