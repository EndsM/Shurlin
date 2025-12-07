package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.models.SpiritElement;

public interface CultivationLogic {
    // Calculate the current max Qi of a player
    double CalculateMaxQi(CultivatedPlayer player, CultivationRealm cultivationRealm, GeneratedTechnique technique);

    // Calculate the Qi regen of player per tick
    double CalculateQiRegen(CultivatedPlayer player, CultivationRealm cultivationRealm, GeneratedTechnique technique);

    // Get list of spirit elements for display
    SpiritElement[] GetDisplayElements();
}
