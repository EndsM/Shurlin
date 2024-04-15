package xyz.shurlin.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;
import xyz.shurlin.registry.features.ModConfiguredFeatures;
import xyz.shurlin.world.biome.BiomeKeys;

import java.util.HashSet;
import java.util.OptionalInt;

public class ShurlinConfiguredFeatures {
    // Oh my word, I need do something to this too...
    public static final ConfiguredFeature<TreeFeatureConfig, ?> PHOENIX_TREE;
    public static final ConfiguredFeature<?, ?> TREES_PEAR;
    public static final ConfiguredFeature<?, ?> TREES_PHOENIX;
    public static final ConfiguredFeature<?, ?> SMALL_BUD;
    public static final ConfiguredFeature<?, ?> PLATYCODON_GRANDIFLORUS;
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
        PHOENIX_TREE = register("phoenix_tree", Feature.TREE.configure(Configs.PHOENIX_TREE_CONFIG));
        TREES_PEAR = register("trees_pear", ModConfiguredFeatures.PEAR_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1))));
        TREES_PHOENIX = register("trees_phoenix", PHOENIX_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1F, 1))));
        SMALL_BUD = register("small_bud", Feature.RANDOM_PATCH.configure(Configs.SMALL_BUD_CONFIG).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1f, 1))));
        PLATYCODON_GRANDIFLORUS = register("platycodon_grandiflorus", Feature.RANDOM_PATCH.configure(Configs.PLATYCODON_GRANDIFLORUS_CONFIG).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1f, 1))));
        PATCH_FIRE = register("patch_fire", Feature.RANDOM_PATCH.configure(Configs.PATCH_FIRE_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.FIRE));

    }

    private static final class Configs {
        private static final TreeFeatureConfig PHOENIX_TREE_CONFIG;
        private static final RandomPatchFeatureConfig SMALL_BUD_CONFIG;
        private static final RandomPatchFeatureConfig PLATYCODON_GRANDIFLORUS_CONFIG;
        private static final RandomPatchFeatureConfig PATCH_FIRE_CONFIG;

        static {
            PHOENIX_TREE_CONFIG = new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(States.PHOENIX_LOG),
                    new SimpleBlockStateProvider(States.PHOENIX_LEAVES),
                    new DarkOakFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
                    new DarkOakTrunkPlacer(6, 2, 1),
                    new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).build();
            SMALL_BUD_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.SMALL_BUD), SimpleBlockPlacer.INSTANCE))
                    .tries(12).build();
            PLATYCODON_GRANDIFLORUS_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.PLATYCODON_GRANDIFLORUS), SimpleBlockPlacer.INSTANCE))
                    .tries(4).build();
            PATCH_FIRE_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.FIRE), SimpleBlockPlacer.INSTANCE))
                    .tries(64).whitelist(new HashSet<Block>() {{
                        add(States.HOT_FIRE_DIRT.getBlock());
                    }}).cannotProject().build();
        }
    }

    static final class States {
        private static final BlockState PHOENIX_LOG;
        private static final BlockState PHOENIX_LEAVES;
        private static final BlockState SMALL_BUD;
        private static final BlockState PLATYCODON_GRANDIFLORUS;
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
            PHOENIX_LOG = ModBlocks.PHOENIX_LOG.getDefaultState();
            PHOENIX_LEAVES = ModBlocks.PHOENIX_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true);
            SMALL_BUD = ModBlocks.SMALL_BUD.getDefaultState();
            PLATYCODON_GRANDIFLORUS = ModBlocks.PLATYCODON_GRANDIFLORUS.getDefaultState();
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
