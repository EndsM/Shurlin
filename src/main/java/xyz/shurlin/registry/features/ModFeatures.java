package xyz.shurlin.registry.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import xyz.shurlin.Shurlin;
import xyz.shurlin.world.biome.ModBiomeKeys;
import xyz.shurlin.world.gen.feature.MysteriousStonePillarFeature;
import xyz.shurlin.world.gen.feature.PlantObsidianHeapFeature;

// Use this class to init every feature related things
// Which means other classes in this package
public class ModFeatures {
    // Each Feature are placed to their separate class, for it might be very long
    public static Feature<DefaultFeatureConfig> MYSTERIOUS_STONE_PILLAR = new MysteriousStonePillarFeature(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> PLANT_OBSIDIAN_HEAP = new PlantObsidianHeapFeature(DefaultFeatureConfig.CODEC);

    public static void RegisterAndHandle() {
        // TODO
        registerFeatures();
        ModConfiguredFeatures.Register();
        ModOreFeatures.Register();
        // Handle this in the last, so every thing it needed will be registered while handle them
        handleBiomeModifications();
    }

    private static void registerFeatures() {
        // Use this to register Feature<>
        Registry.register(Registry.FEATURE, new Identifier(Shurlin.MODID, "mysterious_stone_pillar"), MYSTERIOUS_STONE_PILLAR);
        Registry.register(Registry.FEATURE, new Identifier(Shurlin.MODID, "plant_obsidian_heap"), PLANT_OBSIDIAN_HEAP);
    }

    private static void handleBiomeModifications() {
        // TODO
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomeKeys.FIRE_LAND_KEY),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "patch_fire"))
        );
        // Ores
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_plant_iron"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_plant_gold"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_plant_jade"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_metal_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_wood_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_water_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_fire_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.EXTREME_HILLS),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_earth_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.PLAINS),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_wind_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DESERT),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_light_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_darkness_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SWAMP),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_poison_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_MOUNTAINS),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_lightning_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.ICY),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_ice_spirit"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, "ore_tenuous_time_space_spirit"))
        );
        // Migrate old biome modifications here:

    }
}
