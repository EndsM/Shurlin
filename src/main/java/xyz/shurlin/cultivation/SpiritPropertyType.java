package xyz.shurlin.cultivation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public enum SpiritPropertyType {
    METAL("metal", 1),
    WOOD("wood", 2),
    WATER("water", 3),
    FIRE("fire", 4),
    EARTH("earth", 5),
    WIND("wind", 6),
    LIGHT("light", 7),
    DARKNESS("darkness", 8),
    POISON("poison", 9),
    LIGHTNING("lightning", 10),
    ICE("ice", 11),
    TIME_SPACE("time_space", 12);

    public static final SpiritPropertyType[] GROUPS = values();
    private final String name;
    private final int id;

    SpiritPropertyType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getTranslation() {
        return "type.shurlin." + this.name;
    }

    @Environment(EnvType.CLIENT)
    public int getId() {
        return this.id;
    }
}
