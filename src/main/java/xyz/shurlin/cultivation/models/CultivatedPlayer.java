package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.Map;

/**
 * Represents a player entity that has undergone cultivation and possesses cultivation-related attributes.
 *
 * @author EndsM
 */
public class CultivatedPlayer {
    /**
     * The type of cultivation practiced by the player.
     */
    private CultivationType cultivationType;
    /**
     * A map that stores the player's cultivation stages. The key is the stage number, and the value is the {@code CultivationRealm} object.
     */
    private Map<Integer, CultivationRealm> cultivationStages;
    /**
     * The current stage of cultivation the player is in within their chosen cultivation system.
     * This value corresponds to the integer keys in the {@code cultivationStages} map.
     */
    private int currentStage;
    /**
     * The progress made by the player in the current cultivation stage.
     */
    private double currentCulProgress;
    /**
     * The realm stage achieved by the player in the current cultivation stage.
     */
    private RealmStage realmStage;

    /**
     * Returns the type of cultivation practiced by the player.
     *
     * @return The cultivation type chosen by the player.
     */
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
