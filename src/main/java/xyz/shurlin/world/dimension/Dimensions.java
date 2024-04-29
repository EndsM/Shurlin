package xyz.shurlin.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import xyz.shurlin.Shurlin;

public class Dimensions {
    public static final RegistryKey<World> HOLY_FARMER;

    private static RegistryKey<World> register(String name) {
        return RegistryKey.of(Registry.WORLD_KEY, new Identifier(Shurlin.MODID, name));
    }

    public static void load() {
    }

    static {
        HOLY_FARMER = register("holy_farmer");
    }
}
