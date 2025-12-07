package xyz.shurlin.cultivation.models;

import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.CultivationRegistry;

import java.util.HashMap;
import java.util.Map;

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

    private double currentQi = 0.0;
    // Each spirit root could have a value indicate its quality
    private final Map<Identifier, Integer> spiritRoots = new HashMap<>();
    private Identifier activeTechniqueId = null;


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

    public CultivationTechnique getActiveTechnique() {
        if (activeTechniqueId == null) return null;
        return CultivationRegistry.getTechnique(activeTechniqueId);
    }


    // Standard getter and setter

    public double getCurrentQi() {
        return currentQi;
    }

    public void setCurrentQi(double currentQi) {
        this.currentQi = currentQi;
    }

    public int getRootValue(SpiritElement element) {
        return spiritRoots.getOrDefault(element.getId(), 0);
    }

    public void setRootValue(SpiritElement element, int value) {
        spiritRoots.put(element.getId(), value);
    }

    public Map<Identifier, Integer> getSpiritRoots() {
        return spiritRoots;
    }

    public Identifier getActiveTechniqueId() {
        return activeTechniqueId;
    }

    public void setActiveTechniqueId(Identifier activeTechniqueId) {
        this.activeTechniqueId = activeTechniqueId;
    }

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
