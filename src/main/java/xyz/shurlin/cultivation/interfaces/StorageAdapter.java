package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;

import java.util.List;


public interface StorageAdapter {
    boolean SaveCultivationType();

    CultivationType LoadCultivationType();

    boolean SaveCultivationStages();

    List<CultivationRealm> LoadCultivationStages();
}
