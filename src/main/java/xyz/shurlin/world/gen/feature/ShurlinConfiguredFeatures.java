package xyz.shurlin.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
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
    private static RegistryKey<ConfiguredFeature<?, ?>> getRegKey(String registryName) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Shurlin.MODID, registryName));
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
            FIRE = Blocks.FIRE.getDefaultState();
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
