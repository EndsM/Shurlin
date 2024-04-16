package xyz.shurlin.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import xyz.shurlin.Shurlin;

public class FeatureKeys {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ANCIENT_TREE_KEY;


    private static RegistryKey<ConfiguredFeature<?, ?>> register(String registryName) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, registryName));
    }

    static {
        ANCIENT_TREE_KEY = register("ancient_tree");
    }
}
