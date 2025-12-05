package xyz.shurlin.cultivation.models;

import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.CultivationRegistry;

/**
 * Represents a player entity that has undergone cultivation and possesses cultivation-related attributes.
 *
 * @author EndsM
 */
public class CultivatedPlayer {
    // Default to an "empty" type usually, or null
    private Identifier cultivationTypeId = new Identifier("minecraft", "empty");
    private int majorRealmIndex = 0;
    private int minorRealmIndex = 0;
    private double currentProgress = 0.0;
    private boolean isBottlenecked = false;


    // Logic Helpers: Gate between stored data and abstract data

    public CultivationType getCultivationType() {
        return CultivationRegistry.INSTANCE.get(this.cultivationTypeId);
    }

    // Retrieves the specific definition of the Major Realm the player is currently in
    public CultivationRealm getCurrentRealmDefinition() {
        CultivationType type = getCultivationType();
        if (type == null) {
            return null;
        }
        return type.getRealm(this.majorRealmIndex);
    }


    // Standard getter and setter

    public Identifier getCultivationTypeId() {
        return cultivationTypeId;
    }

    public void setCultivationTypeId(Identifier cultivationTypeId) {
        this.cultivationTypeId = cultivationTypeId;
    }

    public int getMajorRealmIndex() {
        return majorRealmIndex;
    }

    public void setMajorRealmIndex(int majorRealmIndex) {
        this.majorRealmIndex = majorRealmIndex;
    }

    public int getMinorRealmIndex() {
        return minorRealmIndex;
    }

    public void setMinorRealmIndex(int minorRealmIndex) {
        this.minorRealmIndex = minorRealmIndex;
    }

    public double getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }

    public boolean isBottlenecked() {
        return isBottlenecked;
    }

    public void setBottlenecked(boolean bottlenecked) {
        isBottlenecked = bottlenecked;
    }
}
