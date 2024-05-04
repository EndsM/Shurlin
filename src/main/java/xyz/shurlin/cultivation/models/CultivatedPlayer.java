package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.Map;

public class CultivatedPlayer {
    // I guess I need something I'm familiar with
    private CultivationType cultivationType;
    // Realms of a cultivation system will not be hard coded, though I need put more thought to it later
    // It will be created for player as they select their cultivation system, and write to their dataset.
    // However, this solution need beware of the system's update, if player already have the dataset in them
    private Map<Integer, CultivationRealm> cultivationStages;
    // Use this as temp solution of specify which stage player is on
    // Range[0, cultivationType.numberOfRealms - 1]
    private int currentStage;
    // This will represent the progress in current realm
    // Once reach cultivationStages[currentStage].progressBar, will set as this value,
    // then wait for breakthrough
    private double currentCulProgress;
    private RealmStage realmStage;

    // Use a StorageAdapter interface and a MixinStorageAdapter implementation
    // Create a StorageAdapter interface that defines the methods for storing and retrieving data.
    // Then, create a MixinStorageAdapter implementation that uses the Mixin library to handle the actual storage and retrieval.
    // Finally, inject the StorageAdapter into the CultivatedPlayer class using dependency injection.
    // This solution provides the benefits of both the first and second solutions while minimizing their drawbacks.
    // It decouples the class from the storage mechanism, allows for easier testing and maintenance, and keeps the code simple by using Mixin in the implementation.
    public CultivationType GetCultivationType() {
        return cultivationType;
    }

    public void SetCultivationType(CultivationType cultivationType) {
        this.cultivationType = cultivationType;
    }

    public Map<Integer, CultivationRealm> GetCultivationStages() {
        return cultivationStages;
    }

    public void SetCultivationStages(Map<Integer, CultivationRealm> cultivationStages) {
        this.cultivationStages = cultivationStages;
    }

    public int GetCurrentStage() {
        return currentStage;
    }

    public void SetCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public double GetCurrentCulProgress() {
        return currentCulProgress;
    }

    public void SetCurrentCulProgress(double currentCulProgress) {
        this.currentCulProgress = currentCulProgress;
    }

    public RealmStage GetRealmStage() {
        return realmStage;
    }

    public void SetRealmStage(RealmStage realmStage) {
        this.realmStage = realmStage;
    }
}
