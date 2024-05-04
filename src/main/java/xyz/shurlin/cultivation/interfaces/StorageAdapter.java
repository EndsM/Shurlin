package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;

import java.util.List;
import java.util.Map;


public interface StorageAdapter {
    boolean SaveCultivationType(CultivationType cultivationType);

    CultivationType LoadCultivationType();

    boolean SaveCultivationStages(Map<Integer, CultivationRealm> cultivationStages);

    List<CultivationRealm> LoadCultivationStages();
}
