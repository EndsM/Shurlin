package xyz.shurlin.registry.features;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;

public class ModOreFeatures {
    public static ConfiguredFeature<?, ?> ORE_PLANT_IRON = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.PLANT_IRON_ORE_BLOCK.getDefaultState(), 6
    ));

    private static void registerOreFeature(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Shurlin.MODID, id), configuredFeature);
    }

    public static void Register() {

    }
}
