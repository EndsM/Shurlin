package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationTechnique;

public interface CultivationLogic {
    // Calculate the current max Qi of a player
    double calculateMaxQi(CultivatedPlayer player, CultivationRealm cultivationRealm, CultivationTechnique technique);
}
