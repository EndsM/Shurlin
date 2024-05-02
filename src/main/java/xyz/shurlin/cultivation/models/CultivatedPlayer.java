package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.models.enums.CultivationType;

import java.util.List;

public class CultivatedPlayer {
    // I guess I need something I'm familiar with
    private CultivationType cultivationType;
    // Realms of a cultivation system will not be hard coded, though I need put more thought to it later
    // It will be created for player as they select their cultivation system, and write to their dataset.
    // However, this solution need be ware of the system's update, if player already have the dataset in them
    private List<CultivationRealm> cultivationStages;
    // Use this as temp solution of specify which stage player is on
    private int currentStage;
}
