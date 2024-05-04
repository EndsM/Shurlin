package xyz.shurlin.cultivation.models.enums;

import java.util.Arrays;

public enum CultivationType {
    NONE(0, "none", 0),
    SHURLIN(1, "shurlin", 12);

    // Just added the id, or I may find it hard to retrieve the data
    private final int id;
    // The displayed translatable name of the CultivationType
    private final String nameKey;
    // Number of realm this type would have
    private final int numberOfRealms;
    // TODO: add more properties to this stuff

    CultivationType(int id, String nameKey, int numberOfRealms) {
        this.id = id;
        this.nameKey = nameKey;
        this.numberOfRealms = numberOfRealms;
    }

    public int getId() {
        return id;
    }

    public String getNameKey() {
        return nameKey;
    }

    public int getNumberOfRealms() {
        return numberOfRealms;
    }

    public static CultivationType getById(int id) {
        return Arrays.stream(values())
                .filter(type -> type.id == id)
                .findFirst()
                .orElse(null); // or throw an exception if not found
    }

}
