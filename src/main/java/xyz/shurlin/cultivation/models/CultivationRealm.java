package xyz.shurlin.cultivation.models;

// This class represent one stage in stages of a cultivation system
public class CultivationRealm {
    private int id;
    // This will not be used to store the name of it, but the translatable key of it
    private String nameKey;
    // Sometimes the name of the energy from the cultivation system would change according to realm
    // example: 真气->真元
    private String energyNameKey;
}
