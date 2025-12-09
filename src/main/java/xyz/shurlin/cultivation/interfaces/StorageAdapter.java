package xyz.shurlin.cultivation.interfaces;

import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.models.CultivatedPlayer;

import java.util.UUID;

public interface StorageAdapter {
    CultivatedPlayer GetCultivatedPlayer();

    void SetCultivationType(Identifier typeId);

    boolean AddCultivationProgress(double amount);

    boolean AttemptBreakthrough();

    boolean IsBottlenecked();

    int GetMajorRealmIndex();

    int GetMinorRealmIndex();

    double GetProgress();

    double GetMaxProgress();

    void LearnTechnique(UUID techniqueId);

    void SetActiveTechnique(UUID techniqueId);

    void refreshStats();
}
