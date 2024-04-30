package xyz.shurlin.cultivation.models.types;

// This use to make in realm stages
public enum RealmStage {
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

    private int getLevel() {
        return level;
    }

    private String getColor() {
        return color;
    }

    private String getName() {
        return name;
    }
}
