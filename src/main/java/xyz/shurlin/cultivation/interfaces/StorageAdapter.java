package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.models.enums.CultivationType;

public interface StorageAdapter {
    boolean SaveCultivationType();

    CultivationType LoadCultivationType();

    boolean SaveCultivationStages();
}
