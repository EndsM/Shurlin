package xyz.shurlin.registry.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import xyz.shurlin.Shurlin;
import xyz.shurlin.world.biome.BiomeKeys;

// Use this class to init every feature related things
// Which means other classes in this package
public class ModFeatures {

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
    }

    private static void handleBiomeModifications() {
        // TODO
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FIRE_LAND_KEY),
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
    }
}
