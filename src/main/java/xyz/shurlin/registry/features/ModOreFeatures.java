package xyz.shurlin.registry.features;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import xyz.shurlin.Shurlin;
import xyz.shurlin.registry.ModBlocks;

public class ModOreFeatures {
    public static ConfiguredFeature<?, ?> ORE_PLANT_IRON = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.PLANT_IRON_ORE_BLOCK.getDefaultState(),
                    6
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 48)))
            .repeat(6);
    public static ConfiguredFeature<?, ?> ORE_PLANT_GOLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.PLANT_GOLD_ORE_BLOCK.getDefaultState(),
                    6
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 32)))
            .repeat(4);
    public static ConfiguredFeature<?, ?> ORE_PLANT_JADE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.PLANT_JADE_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_METAL_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.NETHERRACK,
                    ModBlocks.TENUOUS_METAL_SPIRIT_ORE_BLOCK.getDefaultState(),
                    2
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 64)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_WOOD_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.TENUOUS_WOOD_SPIRIT_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(4);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_WATER_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.TENUOUS_WATER_SPIRIT_ORE_BLOCK.getDefaultState(),
                    2
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(3);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_FIRE_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.NETHERRACK,
                    ModBlocks.TENUOUS_FIRE_SPIRIT_ORE_BLOCK.getDefaultState(),
                    2
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_EARTH_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.TENUOUS_EARTH_SPIRIT_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_WIND_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.TENUOUS_WIND_SPIRIT_ORE_BLOCK.getDefaultState(),
                    2
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_LIGHT_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.SAND),
                    ModBlocks.TENUOUS_LIGHT_SPIRIT_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 56, 72)))
            .repeat(2);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_DARKNESS_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 48, 80)))
            .repeat(4);
    public static ConfiguredFeature<?, ?> ORE_TENUOUS_POISON_SPIRIT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.TENUOUS_POISON_SPIRIT_ORE_BLOCK.getDefaultState(),
                    4
            ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 16)))
            .repeat(2);

    // Functions below
    private static void registerOreFeature(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Shurlin.MODID, id), configuredFeature);
    }

    public static void Register() {
        registerOreFeature("ore_plant_iron", ORE_PLANT_IRON);
        registerOreFeature("ore_plant_gold", ORE_PLANT_GOLD);
        registerOreFeature("ore_plant_jade", ORE_PLANT_JADE);
        registerOreFeature("ore_tenuous_metal_spirit", ORE_TENUOUS_METAL_SPIRIT);
        registerOreFeature("ore_tenuous_wood_spirit", ORE_TENUOUS_WOOD_SPIRIT);
        registerOreFeature("ore_tenuous_water_spirit", ORE_TENUOUS_WATER_SPIRIT);
        registerOreFeature("ore_tenuous_fire_spirit", ORE_TENUOUS_FIRE_SPIRIT);
        registerOreFeature("ore_tenuous_earth_spirit", ORE_TENUOUS_EARTH_SPIRIT);
        registerOreFeature("ore_tenuous_wind_spirit", ORE_TENUOUS_WIND_SPIRIT);
        registerOreFeature("ore_tenuous_light_spirit", ORE_TENUOUS_LIGHT_SPIRIT);
        registerOreFeature("ore_tenuous_darkness_spirit", ORE_TENUOUS_DARKNESS_SPIRIT);
        registerOreFeature("ore_tenuous_poison_spirit",ORE_TENUOUS_POISON_SPIRIT);
    }
}
