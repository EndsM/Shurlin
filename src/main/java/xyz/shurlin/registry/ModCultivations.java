package xyz.shurlin.registry;

import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;

public class ModCultivations {
    public static final CultivationType SHURLIN_PATH = new CultivationType.Builder()
            .addRealm(new CultivationRealm("realm.shurlin.soldier", 9, 15.0, 1.2))
            .addRealm(new CultivationRealm("realm.shurlin.emissary", 9, 50.0, 1.2))
            .addRealm(new CultivationRealm("realm.shurlin.teacher", 3, 200.0, 1.3))
            .build();
}
