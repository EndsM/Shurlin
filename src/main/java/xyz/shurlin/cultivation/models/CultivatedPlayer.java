package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.List;

public class CultivatedPlayer {
    // I guess I need something I'm familiar with
    private CultivationType cultivationType;
    // Realms of a cultivation system will not be hard coded, though I need put more thought to it later
    // It will be created for player as they select their cultivation system, and write to their dataset.
    // However, this solution need beware of the system's update, if player already have the dataset in them
    private List<CultivationRealm> cultivationStages;
    // Use this as temp solution of specify which stage player is on
    // Range[0, cultivationType.numberOfRealms - 1]
    private int currentStage;
    // This will represent the progress in current realm
    // Once reach cultivationStages[currentStage].progressBar, will set as this value,
    // then wait for breakthrough
    private double currentCulProgress;
    private RealmStage realmStage;

    // I have two solutions now:
    // First one:
    // Call Mixin at these functions directly, and this class itself will be interface's impl
    // Second one:
    // Make a INBTOperator interface and a NBTOperator impl
    // Call them in these functions instead
    // Third one(provided by foss llm):
    // Use a StorageAdapter interface and a MixinStorageAdapter implementation
    // Create a StorageAdapter interface that defines the methods for storing and retrieving data.
    // Then, create a MixinStorageAdapter implementation that uses the Mixin library to handle the actual storage and retrieval.
    // Finally, inject the StorageAdapter into the CultivatedPlayer class using dependency injection.
    // This solution provides the benefits of both the first and second solutions while minimizing their drawbacks.
    // It decouples the class from the storage mechanism, allows for easier testing and maintenance, and keeps the code simple by using Mixin in the implementation.
    public CultivationType getCultivationType() {
        return cultivationType;
    }

    public void setCultivationType(CultivationType cultivationType) {
        this.cultivationType = cultivationType;
    }

    public List<CultivationRealm> getCultivationStages() {
        return cultivationStages;
    }

    public void setCultivationStages(List<CultivationRealm> cultivationStages) {
        this.cultivationStages = cultivationStages;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public double getCurrentCulProgress() {
        return currentCulProgress;
    }

    public void setCurrentCulProgress(double currentCulProgress) {
        this.currentCulProgress = currentCulProgress;
    }

    public RealmStage getRealmStage() {
        return realmStage;
    }

    public void setRealmStage(RealmStage realmStage) {
        this.realmStage = realmStage;
    }
}
