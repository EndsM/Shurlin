package xyz.shurlin.cultivation.dynamic;

import net.minecraft.util.Formatting;

public enum TechniqueGrade {
    YELLOW(0, "黄阶", 2, Formatting.YELLOW),
    BLACK(1, "玄阶", 5, Formatting.BLUE),
    EARTH(2, "地阶", 8, Formatting.DARK_PURPLE),
    HEAVEN(3, "天阶", 11, Formatting.GOLD);

    private final int id;
    // Uses localization later
    private final String name;
    // The maximum realm can this technique progress to
    private final int maxRealmIndex;
    private final Formatting color;

    TechniqueGrade(int id, String name, int maxRealmIndex, Formatting color) {
        this.id = id;
        this.name = name;
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
        return name;
    }
}
