package xyz.shurlin.cultivation.models.enums;

// This use to make in realm stages
public enum RealmStage {
    NONE(0, "", "none"),
    LOW(1, "", "low"),
    MIDDLE(2, "§a", "middle"),
    HIGH(3, "§9", "high");

    private final int level;
    private final String color;
    private final String name;

    RealmStage(int level, String color, String name) {
        this.level = level;
        this.color = color;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
