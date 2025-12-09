package xyz.shurlin.cultivation.impl;

import xyz.shurlin.cultivation.interfaces.CultivationLogic;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.models.SpiritElement;
import xyz.shurlin.registry.ModElements;

public class ShurlinCultivationLogic implements CultivationLogic {
    // Changed parameter to GeneratedTechnique
    @Override
    public double CalculateMaxQi(CultivatedPlayer player, CultivationRealm cultivationRealm, GeneratedTechnique technique) {
        double baseCap = cultivationRealm.getRequirement(player.getMinorRealmIndex());
        double techMod = (technique != null) ? technique.getCapacityModifier() : 1.0;
        return baseCap * 1.5 * techMod;
    }

    @Override
    public double CalculateQiRegen(CultivatedPlayer player, CultivationRealm cultivationRealm, GeneratedTechnique technique) {
        if (technique == null) return 0.1;

        double efficiency = technique.getEfficiency();

        // Need to match ID from GeneratedTechnique to SpiritElement
        SpiritElement el = null;
        for (SpiritElement s : ModElements.SHURLIN_ELEMENTS) {
            if (s.getId().equals(technique.getElementId())) {
                el = s;
                break;
            }
        }

        int rootValue = (el != null) ? player.getRootValue(el) : 0;
        double rootMultiplier = 1.0 + (rootValue / 100.0);

        double realmBase = 1.0 + (player.getMajorRealmIndex() * 0.5) + (player.getMinorRealmIndex() * 0.1);

        return realmBase * efficiency * rootMultiplier;
    }

    @Override
    public SpiritElement[] GetDisplayElements() {
        return ModElements.SHURLIN_ELEMENTS;
    }

    @Override
    public double CalculateHealthBonus(CultivatedPlayer player, CultivationRealm cultivationRealm) {
        int major = player.getMajorRealmIndex();
        int minor = player.getMinorRealmIndex();

        double majorBonus = major * 5.0;
        double minorBonus = minor * (2.0 * major);

        return majorBonus + minorBonus;
    }
}
