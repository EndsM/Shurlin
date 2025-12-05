package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.HashMap;
import java.util.Map;

// This class represent one stage in stages of a cultivation system
public class CultivationRealm {
    private int id;
    // This will not be used to store the name of it, but the translatable key of it
    private String nameKey;
    // Sometimes the name of the energy from the cultivation system would change according to realm
    // example: 真气->真元
    private String energyNameKey;
    // Uses a map to pair the realm stages and value needed
    private Map<RealmStage, Double> stageRequirements = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public void setStageRequirement(RealmStage stage, double requirement) {
        this.stageRequirements.put(stage, requirement);
    }

    public double getStageRequirement(RealmStage stage) {
        return this.stageRequirements.getOrDefault(stage, 100.0);
    }
}
