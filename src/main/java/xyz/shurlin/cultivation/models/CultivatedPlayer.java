package xyz.shurlin.cultivation.models;

import net.minecraft.client.realms.Realms;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;

import java.util.List;

public class CultivatedPlayer {
    // I guess I need something I'm familiar with
    private CultivationType cultivationType;
    // Realms of a cultivation system will not be hard coded, though I need put more thought to it later
    // It will be created for player as they select their cultivation system, and write to their dataset.
    // However, this solution need beware of the system's update, if player already have the dataset in them
    private List<CultivationRealm> cultivationStages;
    // Use this as temp solution of specify which stage player is on
    // Range[0, cultivationType.numberOfRealms - 1]
    private int currentStage;
    // This will represent the progress in current realm
    // Once reach cultivationStages[currentStage].progressBar, will set as this value,
    // then wait for breakthrough
    private double currentCulProgress;
    private RealmStage realmStage;
}
