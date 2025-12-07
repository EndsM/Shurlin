package xyz.shurlin.cultivation.models;

import net.minecraft.util.Identifier;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.interfaces.CultivationLogic;
import xyz.shurlin.registry.ModElements;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private double maxQi = 0.0; // Cache max Qi
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

    // Called every tick to handle passive updates like Qi regeneration
    public void tick() {
        CultivationType type = getCultivationType();
        if (type == null || type.getLogic() == null) return;

        CultivationRealm realm = getCurrentRealmDefinition();
        if (realm == null) return;

        CultivationLogic logic = type.getLogic();
        CultivationTechnique technique = getActiveTechnique();

        // Update Max Qi Cache
        this.maxQi = logic.CalculateMaxQi(this, realm, technique);

        // Regenerate Qi
        if (this.currentQi < this.maxQi) {
            double regen = logic.CalculateQiRegen(this, realm, technique);
            // Assuming tick runs 20 times a second, adjust regen if logic returns "per second" or keep as "per tick"
            this.currentQi = Math.min(this.currentQi + regen, this.maxQi);
        }
    }

    public void generateRandomRoots() {
        Random rand = Shurlin.random;
        spiritRoots.clear();

        // Give random values (0-100) to all Shurlin Elements
        // Or specific logic: 1-3 random high roots, others low
        for (SpiritElement element : ModElements.SHURLIN_ELEMENTS) {
            // Skewed distribution: mostly low, rarely high
            int quality = rand.nextInt(40); // Base 0-40
            if (rand.nextFloat() < 0.1) quality += rand.nextInt(40); // 10% chance for +0-40
            if (rand.nextFloat() < 0.05) quality += rand.nextInt(21); // 5% chance for +0-20 (Total max 100)

            spiritRoots.put(element.getId(), quality);
        }
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
