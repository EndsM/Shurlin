package xyz.shurlin.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.impl.ShurlinCultivationLogic;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;

public class ModCultivations {
    public static final CultivationType SHURLIN_PATH = new CultivationType.Builder()
            .addRealm(new CultivationRealm("realm.shurlin.novice", 9, 20.0, 1.4))
            .addRealm(new CultivationRealm("realm.shurlin.adept", 9, 350.0, 1.3))
            .addRealm(new CultivationRealm("realm.shurlin.master", 9, 3000.0, 1.25))
            .setLogic(new ShurlinCultivationLogic())
            .build();

    public static void Register() {
        Registry.register(CultivationRegistry.INSTANCE, new Identifier(Shurlin.MODID, "shurlin_path"), SHURLIN_PATH);
    }
}
