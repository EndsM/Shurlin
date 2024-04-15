package xyz.shurlin.registry.features;

import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;

public class ModConfiguredFeatures {
    // Experimental solution to register problem
    // Use this to combine ShurlinOreFeatures and ShurlinConfiguredFeatures
    // And separate them with comments
    public static ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.PEAR_LOG.getDefaultState()),
            // This config the weight of leaves with pear and without pear
            new WeightedBlockStateProvider()
                    .addState(ModBlocks.PEAR_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 9)
                    .addState(ModBlocks.PEAR_RIPE_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 1),
            new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
            new StraightTrunkPlacer(5, 2, 0),
            new TwoLayersFeatureSize(1, 0, 1)
    ).build());

    private static void registerConfiguredFeature(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Shurlin.MODID, id), configuredFeature);
    }

    // Accessible in the same package
    static void Register() {
        registerConfiguredFeature("pear_tree", PEAR_TREE);
    }
}
