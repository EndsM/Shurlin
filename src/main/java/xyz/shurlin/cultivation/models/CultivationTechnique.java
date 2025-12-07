package xyz.shurlin.cultivation.models;

import net.minecraft.util.Identifier;

public class CultivationTechnique {
    private final Identifier id;
    private final SpiritElement primaryElement;
    // Speed modifier when using this Technique
    private final double efficiency;
    // The modifier for the energy capacity of the character
    private final double capacityModifier;


    public CultivationTechnique(Identifier id, SpiritElement primaryElement, double efficiency, double capacityModifier) {
        this.id = id;
        this.primaryElement = primaryElement;
        this.efficiency = efficiency;
        this.capacityModifier = capacityModifier;
    }

    public Identifier getId() {
        return id;
    }

    public SpiritElement getPrimaryElement() {
        return primaryElement;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public double getCapacityModifier() {
        return capacityModifier;
    }
}
