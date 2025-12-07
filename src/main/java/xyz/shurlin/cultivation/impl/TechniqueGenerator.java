package xyz.shurlin.cultivation.impl;

import net.minecraft.util.Identifier;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.dynamic.TechniqueQuality;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.models.SpiritElement;
import xyz.shurlin.registry.ModElements;

import java.util.Random;
import java.util.UUID;

public class TechniqueGenerator {
    private static final Random rand = Shurlin.random;

    private static final String[] SUFFIXES = {"决", "典", "经", "法", "功", "录", "真解"};
    // Simple Prefixes just for five elements and generics
    private static final String[] METAL_PREFIX = {"金光", "锐金", "太白", "庚金"};
    private static final String[] WOOD_PREFIX = {"长春", "青木", "万象", "枯荣", "乙木"};
    private static final String[] WATER_PREFIX = {"葵水", "碧波", "玄冰", "弱水", "浩渺"};
    private static final String[] FIRE_PREFIX = {"焚天", "烈焰", "赤阳", "离火", "九阳"};
    private static final String[] EARTH_PREFIX = {"厚土", "搬山", "戍土", "昆仑"};
    private static final String[] GENERIC_PREFIX = {"无名", "太上", "造化", "混沌"};

    public static GeneratedTechnique createRandom(TechniqueGrade targetGrade) {
        // random element
        SpiritElement element = ModElements.SHURLIN_ELEMENTS[rand.nextInt(ModElements.SHURLIN_ELEMENTS.length)];

        // random quality
        TechniqueQuality quality = rollQuality();

        // Calculate stats
        // Just for test
        double baseEff = 1.0 + targetGrade.ordinal();
        double qualityMod = getQualityModifier(quality);

        double efficiency = (baseEff + rand.nextDouble() * 0.5) * qualityMod;

        double capacity = (1.0 + (targetGrade.ordinal() * 0.3)) * qualityMod;

        String name = generateName(element);

        return new GeneratedTechnique(
                UUID.randomUUID(),
                name,
                targetGrade,
                quality,
                element.getId(),
                efficiency,
                capacity
        );
    }

    private static TechniqueQuality rollQuality() {
        float roll = rand.nextFloat();
        if (roll < 0.50) return TechniqueQuality.LOW;      // 50%
        if (roll < 0.85) return TechniqueQuality.MEDIUM;   // 35%
        if (roll < 0.98) return TechniqueQuality.HIGH;     // 13%
        return TechniqueQuality.PERFECT;                   // 2%
    }

    private static double getQualityModifier(TechniqueQuality quality) {
        switch (quality) {
            case LOW:
                return 0.8;
            case MEDIUM:
                return 1.0;
            case HIGH:
                return 1.2;
            case PERFECT:
                return 1.5;
            default:
                return 1.0;
        }
    }

    private static String generateName(SpiritElement element) {
        String prefix;
        Identifier id = element.getId();

        // Simple hardcoded matching, uses Map<Identifier, String[]> or something like that later
        if (id.getPath().contains("metal")) prefix = METAL_PREFIX[rand.nextInt(METAL_PREFIX.length)];
        else if (id.getPath().contains("wood")) prefix = WOOD_PREFIX[rand.nextInt(WOOD_PREFIX.length)];
        else if (id.getPath().contains("water")) prefix = WATER_PREFIX[rand.nextInt(WATER_PREFIX.length)];
        else if (id.getPath().contains("fire")) prefix = FIRE_PREFIX[rand.nextInt(FIRE_PREFIX.length)];
        else if (id.getPath().contains("earth")) prefix = EARTH_PREFIX[rand.nextInt(EARTH_PREFIX.length)];
        else prefix = GENERIC_PREFIX[rand.nextInt(GENERIC_PREFIX.length)];

        String suffix = SUFFIXES[rand.nextInt(SUFFIXES.length)];
        return prefix + suffix;
    }
}
