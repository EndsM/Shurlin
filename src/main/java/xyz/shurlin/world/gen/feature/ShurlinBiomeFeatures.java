package xyz.shurlin.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.GenerationSettings.Builder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import xyz.shurlin.registry.features.ModConfiguredFeatures;

public class ShurlinBiomeFeatures {
    // Oh my word...
    public static void addPearTrees(Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.TREES_PEAR);
    }

    public static void addPhoenixTrees(Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.TREES_PHOENIX
        );
    }

    public static void addSmallBud(Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.SMALL_BUD

        );
    }

    public static void addPlatycodonGrandiflorus(Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.PLATYCODON_GRANDIFLORUS);
    }

    public static void addBlock(Builder builder, Block block, int tries) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.RANDOM_PATCH.configure(getConfig(block.getDefaultState(), tries)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
    }

    private static RandomPatchFeatureConfig getConfig(BlockState state, int tries) {
        return (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(state), SimpleBlockPlacer.INSTANCE)).tries(tries).build();
    }

    public static void addHotSprings(Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA.decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(5))));
    }
}
