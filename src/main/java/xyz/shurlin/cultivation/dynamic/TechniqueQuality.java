package xyz.shurlin.cultivation.dynamic;

public enum TechniqueQuality {
    LOW("下品"),
    MEDIUM("中品"),
    HIGH("上品"),
    PERFECT("极品");

    private final String name;

    TechniqueQuality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
