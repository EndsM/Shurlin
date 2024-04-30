package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.CultivationRealm;

// CultivatedPlayerInterface (ICultivatedPlayer)
// Use this so will be an interface of all Cultivation related stuff of Player.
public interface CultivatedPlayerAccessor {
    CultivationRealm getRealm();

    void setRealm(CultivationRealm realm);
}
