package xyz.shurlin.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;

public class ModCultivations {
    public static final CultivationType SHURLIN_PATH = new CultivationType.Builder()
            .addRealm(new CultivationRealm("realm.shurlin.soldier", 9, 20.0, 1.4))
            .addRealm(new CultivationRealm("realm.shurlin.emissary", 9, 350.0, 1.3))
            .addRealm(new CultivationRealm("realm.shurlin.teacher", 9, 3000.0, 1.25))
            .build();

    public static void Register() {
        Registry.register(CultivationRegistry.INSTANCE, new Identifier(Shurlin.MODID, "shurlin_path"), SHURLIN_PATH);
    }
}
