package xyz.shurlin.cultivation.models.enums;

public enum CultivationType {
    SHURLIN("shurlin", 12);

    // The displayed translatable name of the CultivationType
    private final String name;
    // Number of realm this type would have
    private final int numberOfRealms;
    // TODO: add more properties to this stuff

    CultivationType(String name, int numberOfRealms) {
        this.name = name;
        this.numberOfRealms = numberOfRealms;
    }
}
