package xyz.shurlin.registry.features;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {
    // Experimental solution to register problem
    // Use this to combine ShurlinOreFeatures and ShurlinConfiguredFeatures
    // And separate them with comments
    public static ConfiguredFeature<TreeFeatureConfig, ?> PEAR_TREE;


    // Accessible in the same package
    static void Register(){

    }
}
