package xyz.shurlin.registry.features;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;

import java.util.HashSet;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    // Maybe separate utility class of FeatureConfig later.
    public static final ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.PEAR_LOG.getDefaultState()),
            // This config the weight of leaves with pear and without pear
            new WeightedBlockStateProvider()
                    .addState(ModBlocks.PEAR_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 9)
                    .addState(ModBlocks.PEAR_RIPE_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 1),
            new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
            new StraightTrunkPlacer(5, 2, 0),
            new TwoLayersFeatureSize(1, 0, 1)
    ).build());
    public static final ConfiguredFeature<TreeFeatureConfig, ?> PHOENIX_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.PHOENIX_LOG.getDefaultState()),
            // This tree don't have other types of leaves though
            new SimpleBlockStateProvider(ModBlocks.PHOENIX_LEAVES.getDefaultState()),
            new DarkOakFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
            new DarkOakTrunkPlacer(6, 2, 1),
            new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
    ).build());
    // Tree define how the tree looks like, and below is how they distribute
    public static final ConfiguredFeature<?, ?> TREES_PEAR = PEAR_TREE
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1f, 1)));
    public static final ConfiguredFeature<?, ?> TREES_PHOENIX = PHOENIX_TREE
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1f, 1)));
    public static final ConfiguredFeature<?, ?> SMALL_BUD = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.SMALL_BUD.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE
            ).tries(12).build())
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1f, 1)));
    public static final ConfiguredFeature<?, ?> PLATYCODON_GRANDIFLORUS = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.PLATYCODON_GRANDIFLORUS.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE
            ).tries(4).build())
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(6, 0.1f, 1)));
    public static final ConfiguredFeature<?, ?> PATCH_FIRE = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.FIRE.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE
            ).tries(64).whitelist(new HashSet<Block>() {{
                add(ModBlocks.HOT_FIRE_DIRT);
            }}).cannotProject().build())
            .decorate(ConfiguredFeatures.Decorators.FIRE);

    // A little helper to reduce the length of a registry
    private static void registerConfiguredFeature(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Shurlin.MODID, id), configuredFeature);
    }

    // Accessible in the same package
    static void Register() {
        registerConfiguredFeature("pear_tree", PEAR_TREE);
        registerConfiguredFeature("phoenix_tree", PHOENIX_TREE);
        registerConfiguredFeature("trees_pear", TREES_PEAR);
        registerConfiguredFeature("trees_phoenix", TREES_PHOENIX);
        registerConfiguredFeature("small_bud", SMALL_BUD);
        registerConfiguredFeature("platycodon_grandiflorus", PLATYCODON_GRANDIFLORUS);
        registerConfiguredFeature("patch_fire", PATCH_FIRE);
    }
}
