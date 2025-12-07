package xyz.shurlin.cultivation.impl;

import xyz.shurlin.cultivation.interfaces.CultivationLogic;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationTechnique;
import xyz.shurlin.cultivation.models.SpiritElement;
import xyz.shurlin.registry.ModElements;

public class ShurlinCultivationLogic implements CultivationLogic {
    @Override
    public double CalculateMaxQi(CultivatedPlayer player, CultivationRealm cultivationRealm, CultivationTechnique technique) {
        // Just uses the minor realm's exp req for cal now, will use a better logic later
        double baseCap = cultivationRealm.getRequirement(player.getMinorRealmIndex());

        double techMod = (technique != null) ? technique.getCapacityModifier() : 1.0;

        // Realm Base * 1.5 * Technique Modifier
        return baseCap * 1.5 * techMod;
    }

    @Override
    public double CalculateQiRegen(CultivatedPlayer player, CultivationRealm cultivationRealm, CultivationTechnique technique) {
        if (technique == null) return 0.1; // Very slow base regen without technique

        double efficiency = technique.getEfficiency();

        // Check Spirit Root affinity
        // If player has a strong root matching the technique's element, regen is faster
        int rootValue = player.getRootValue(technique.getPrimaryElement());
        double rootMultiplier = 1.0 + (rootValue / 100.0);

        // Base regen derived from Realm level (Higher realms breathe faster/more potent Qi)
        double realmBase = 1.0 + (player.getMajorRealmIndex() * 0.5) + (player.getMinorRealmIndex() * 0.1);

        return realmBase * efficiency * rootMultiplier;
    }

    @Override
    public SpiritElement[] GetDisplayElements() {
        return ModElements.SHURLIN_ELEMENTS;
    }
}
