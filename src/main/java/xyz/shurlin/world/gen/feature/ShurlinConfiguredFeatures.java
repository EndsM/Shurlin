package xyz.shurlin.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;
import xyz.shurlin.world.biome.BiomeKeys;

import java.util.HashSet;

public class ShurlinConfiguredFeatures {
    // Oh my word, I need do something to this too...
    public static final ConfiguredFeature<?, ?> PATCH_FIRE;

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return register(new Identifier(Shurlin.MODID, id), configuredFeature);
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(Identifier id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> getRegKey(String registryName) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, registryName));
    }

    private static ConfiguredFeature<?, ?> createOre(RuleTest ruleTest, BlockState state, int size, int numPerChunk, int maxy) {
        return Feature.ORE.configure(new OreFeatureConfig(ruleTest, state, size)) // vein size
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                        0, // bottom offset
                        0, // min y level
                        maxy))) // max y level
                .repeat(numPerChunk); // number of veins per chunk
    }

    static {
        PATCH_FIRE = register("patch_fire", Feature.RANDOM_PATCH.configure(Configs.PATCH_FIRE_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.FIRE));

    }

    private static final class Configs {
        private static final RandomPatchFeatureConfig PATCH_FIRE_CONFIG;

        static {
            PATCH_FIRE_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.FIRE), SimpleBlockPlacer.INSTANCE))
                    .tries(64).whitelist(new HashSet<Block>() {{
                        add(States.HOT_FIRE_DIRT.getBlock());
                    }}).cannotProject().build();
        }
    }

    static final class States {
        private static final BlockState FIRE;
        private static final BlockState HOT_FIRE_DIRT;
        static final BlockState PLANT_IRON_ORE_BLOCK;
        static final BlockState PLANT_GOLD_ORE_BLOCK;
        static final BlockState PLANT_JADE_ORE_BLOCK;
        static final BlockState TENUOUS_METAL_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_WOOD_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_WATER_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_FIRE_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_EARTH_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_WIND_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_LIGHT_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_POISON_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_ICE_SPIRIT_ORE_BLOCK;
        static final BlockState TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK;

        static {
            PLANT_IRON_ORE_BLOCK = ModBlocks.PLANT_IRON_ORE_BLOCK.getDefaultState();
            PLANT_GOLD_ORE_BLOCK = ModBlocks.PLANT_GOLD_ORE_BLOCK.getDefaultState();
            PLANT_JADE_ORE_BLOCK = ModBlocks.PLANT_JADE_ORE_BLOCK.getDefaultState();
            FIRE = net.minecraft.block.Blocks.FIRE.getDefaultState();
            HOT_FIRE_DIRT = ModBlocks.HOT_FIRE_DIRT.getDefaultState();
            TENUOUS_METAL_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_METAL_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_WOOD_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_WOOD_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_WATER_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_WATER_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_FIRE_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_FIRE_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_EARTH_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_EARTH_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_WIND_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_WIND_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_LIGHT_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_LIGHT_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_POISON_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_POISON_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_ICE_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_ICE_SPIRIT_ORE_BLOCK.getDefaultState();
            TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK = ModBlocks.TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK.getDefaultState();
        }
    }

    public static class OreGenerators {

    }

    public static void ApplyToBiome() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FIRE_LAND_KEY), GenerationStep.Feature.VEGETAL_DECORATION, getRegKey("patch_fire"));
    }
}
