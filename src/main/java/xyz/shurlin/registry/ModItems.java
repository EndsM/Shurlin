package xyz.shurlin.registry;


import net.minecraft.block.Block;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.SpiritConstants;
import xyz.shurlin.cultivation.SpiritPropertyType;
import xyz.shurlin.item.*;
import xyz.shurlin.item.Spiritual.SpiritItem;
import xyz.shurlin.registry.helper.ModArmorItems;
import xyz.shurlin.registry.helper.ModToolItems;

public class ModItems {
    // These two are using ModBlocks class' function
    public static Item DEAD_LEAVE_CORAL_FAN_BLOCK;
    public static Item LEAVE_CORAL_FAN_BLOCK;
    // Down below will just use constants I think...
    // maybe separate assignment and declare later
    public static final Item PLANT_MIXTURE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_MIXTURE_HEAP = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    // Plant Essences
    public static final Item PLANT_ESSENCE_PARTICLE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    // Basically nugget
    public static final Item PLANT_ESSENCE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_ESSENCE_INGOT = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    // End of Plant Essences
    public static final Item PLANT_DREGS = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_IRON_INGOT = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_IRON_NUGGET = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_GOLD_INGOT = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_GOLD_NUGGET = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_JADE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PEAR = new Item(
            new Item.Settings()
                    .food(new FoodComponent.Builder().hunger(3).snack().saturationModifier(0.3f).build())
                    .group(ItemGroups.SHURLIN)
    );
    public static final Item HOLY_PEAR = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    // Will need to consider should we still using such mode for special items later.
    // Because I plan to have cultivation equipments crafting system that have randomized or generative stats
    public static final Item HOLY_PEAR_SWORD = new HolyPearSwordItem();
    public static final Item HOLY_PEAR_BOW = new HolyPearBowItem();
    public static final Item HOLY_PEAR_WAND = new HolyPearWandItem();
    public static final Item BEAN = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item CHAIN_MINER = new ChainMinerItem();
    public static final Item LIFE_AMULET = new LifeAmuletItem();
    public static final Item CORAL_AMULET = new CoralAmuletItem();
    // backpack thing, may rework to space storage equipment like in ae2
    public static final Item HOLY_PEAR_BAG = new HolyPearBagItem();
    // Back to other items
    public static final Item PLANT_WAND = new PlantWandItem();
    public static final Item MYSTERIOUS_SPIRIT_OF_PLANT = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item PLANT_EXTRACTANT = new PlantExtractantItem(64);

    public static final Item WORKER_SHELL = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item BREAKER_CORE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item COLLECTOR_CORE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item CONCENTRATOR_CORE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );
    public static final Item EXTRACTOR_CORE = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN)
    );

    public static final Item TENUOUS_METAL_SPIRIT = new SpiritItem(SpiritPropertyType.METAL, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_WOOD_SPIRIT = new SpiritItem(SpiritPropertyType.WOOD, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_WATER_SPIRIT = new SpiritItem(SpiritPropertyType.WATER, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_FIRE_SPIRIT = new SpiritItem(SpiritPropertyType.FIRE, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_EARTH_SPIRIT = new SpiritItem(SpiritPropertyType.EARTH, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_WIND_SPIRIT = new SpiritItem(SpiritPropertyType.WIND, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_LIGHT_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHT, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_DARKNESS_SPIRIT = new SpiritItem(SpiritPropertyType.DARKNESS, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_POISON_SPIRIT = new SpiritItem(SpiritPropertyType.POISON, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_LIGHTNING_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHTNING, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_ICE_SPIRIT = new SpiritItem(SpiritPropertyType.ICE, SpiritConstants.TENUOUS);
    public static final Item TENUOUS_TIME_SPACE_SPIRIT = new SpiritItem(SpiritPropertyType.TIME_SPACE, SpiritConstants.TENUOUS);
    public static final Item COMMON_METAL_SPIRIT = new SpiritItem(SpiritPropertyType.METAL, SpiritConstants.COMMON);
    public static final Item COMMON_WOOD_SPIRIT = new SpiritItem(SpiritPropertyType.WOOD, SpiritConstants.COMMON);
    public static final Item COMMON_WATER_SPIRIT = new SpiritItem(SpiritPropertyType.WATER, SpiritConstants.COMMON);
    public static final Item COMMON_FIRE_SPIRIT = new SpiritItem(SpiritPropertyType.FIRE, SpiritConstants.COMMON);
    public static final Item COMMON_EARTH_SPIRIT = new SpiritItem(SpiritPropertyType.EARTH, SpiritConstants.COMMON);
    public static final Item COMMON_WIND_SPIRIT = new SpiritItem(SpiritPropertyType.WIND, SpiritConstants.COMMON);
    public static final Item COMMON_LIGHT_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHT, SpiritConstants.COMMON);
    public static final Item COMMON_DARKNESS_SPIRIT = new SpiritItem(SpiritPropertyType.DARKNESS, SpiritConstants.COMMON);
    public static final Item COMMON_POISON_SPIRIT = new SpiritItem(SpiritPropertyType.POISON, SpiritConstants.COMMON);
    public static final Item COMMON_LIGHTNING_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHTNING, SpiritConstants.COMMON);
    public static final Item COMMON_ICE_SPIRIT = new SpiritItem(SpiritPropertyType.ICE, SpiritConstants.COMMON);
    public static final Item COMMON_TIME_SPACE_SPIRIT = new SpiritItem(SpiritPropertyType.TIME_SPACE, SpiritConstants.COMMON);
    public static final Item DENSE_METAL_SPIRIT = new SpiritItem(SpiritPropertyType.METAL, SpiritConstants.DENSE);
    public static final Item DENSE_WOOD_SPIRIT = new SpiritItem(SpiritPropertyType.WOOD, SpiritConstants.DENSE);
    public static final Item DENSE_WATER_SPIRIT = new SpiritItem(SpiritPropertyType.WATER, SpiritConstants.DENSE);
    public static final Item DENSE_FIRE_SPIRIT = new SpiritItem(SpiritPropertyType.FIRE, SpiritConstants.DENSE);
    public static final Item DENSE_EARTH_SPIRIT = new SpiritItem(SpiritPropertyType.EARTH, SpiritConstants.DENSE);
    public static final Item DENSE_WIND_SPIRIT = new SpiritItem(SpiritPropertyType.WIND, SpiritConstants.DENSE);
    public static final Item DENSE_LIGHT_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHT, SpiritConstants.DENSE);
    public static final Item DENSE_DARKNESS_SPIRIT = new SpiritItem(SpiritPropertyType.DARKNESS, SpiritConstants.DENSE);
    public static final Item DENSE_POISON_SPIRIT = new SpiritItem(SpiritPropertyType.POISON, SpiritConstants.DENSE);
    public static final Item DENSE_LIGHTNING_SPIRIT = new SpiritItem(SpiritPropertyType.LIGHTNING, SpiritConstants.DENSE);
    public static final Item DENSE_ICE_SPIRIT = new SpiritItem(SpiritPropertyType.ICE, SpiritConstants.DENSE);
    public static final Item DENSE_TIME_SPACE_SPIRIT = new SpiritItem(SpiritPropertyType.TIME_SPACE, SpiritConstants.DENSE);
    // End of Spirit items
    public static final Item SHURLIN_INGOT = new Item(
            new Item.Settings().group(ItemGroups.SHURLIN).fireproof()
    );
    // This need rework or delete, see its class
    public static final Item SHURLIN_POWERFUL_AXE = new ShurlinPowerfulAxeItem();

    // in-class utility functions below
    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    // call this while init
    public static void Register() {
        // Special BlockItem
        DEAD_LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.DEAD_LEAVE_CORAL_FAN), ModBlocks.DEAD_LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.LEAVE_CORAL_FAN), ModBlocks.LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        // Items
        register("plant_mixture", PLANT_MIXTURE);
        register("plant_mixture_heap", PLANT_MIXTURE_HEAP);
        register("plant_essence_particle", PLANT_ESSENCE_PARTICLE);
        register("plant_essence", PLANT_ESSENCE);
        register("plant_essence_ingot", PLANT_ESSENCE_INGOT);
        register("plant_dregs", PLANT_DREGS);
        register("plant_iron_ingot", PLANT_IRON_INGOT);
        register("plant_iron_nugget", PLANT_IRON_NUGGET);
        register("plant_gold_ingot", PLANT_GOLD_INGOT);
        register("plant_gold_nugget", PLANT_GOLD_NUGGET);
        register("plant_jade", PLANT_JADE);
        register("pear", PEAR);
        register("holy_pear", HOLY_PEAR);
        register("holy_pear_sword", HOLY_PEAR_SWORD);
        register("holy_pear_bow", HOLY_PEAR_BOW);
        register("holy_pear_wand", HOLY_PEAR_WAND);
        register("bean", BEAN);
        register("chain_miner", CHAIN_MINER);
        register("life_amulet", LIFE_AMULET);
        register("coral_amulet", CORAL_AMULET);
        register("holy_pear_bag", HOLY_PEAR_BAG);
        register("plant_wand", PLANT_WAND);
        register("mysterious_spirit_of_plant", MYSTERIOUS_SPIRIT_OF_PLANT);
        register("plant_extractant", PLANT_EXTRACTANT);
        register("worker_shell", WORKER_SHELL);
        register("breaker_core", BREAKER_CORE);
        register("collector_core", COLLECTOR_CORE);
        register("concentrator_core", CONCENTRATOR_CORE);
        register("extractor_core", EXTRACTOR_CORE);
        // Spirits below
        register("tenuous_metal_spirit", TENUOUS_METAL_SPIRIT);
        register("tenuous_wood_spirit", TENUOUS_WOOD_SPIRIT);
        register("tenuous_water_spirit", TENUOUS_WATER_SPIRIT);
        register("tenuous_fire_spirit", TENUOUS_FIRE_SPIRIT);
        register("tenuous_earth_spirit", TENUOUS_EARTH_SPIRIT);
        register("tenuous_wind_spirit", TENUOUS_WIND_SPIRIT);
        register("tenuous_light_spirit", TENUOUS_LIGHT_SPIRIT);
        register("tenuous_darkness_spirit", TENUOUS_DARKNESS_SPIRIT);
        register("tenuous_poison_spirit", TENUOUS_POISON_SPIRIT);
        register("tenuous_lightning_spirit", TENUOUS_LIGHTNING_SPIRIT);
        register("tenuous_ice_spirit", TENUOUS_ICE_SPIRIT);
        register("tenuous_time_space_spirit", TENUOUS_TIME_SPACE_SPIRIT);
        register("common_metal_spirit", COMMON_METAL_SPIRIT);
        register("common_wood_spirit", COMMON_WOOD_SPIRIT);
        register("common_water_spirit", COMMON_WATER_SPIRIT);
        register("common_fire_spirit", COMMON_FIRE_SPIRIT);
        register("common_earth_spirit", COMMON_EARTH_SPIRIT);
        register("common_wind_spirit", COMMON_WIND_SPIRIT);
        register("common_light_spirit", COMMON_LIGHT_SPIRIT);
        register("common_darkness_spirit", COMMON_DARKNESS_SPIRIT);
        register("common_poison_spirit", COMMON_POISON_SPIRIT);
        register("common_lightning_spirit", COMMON_LIGHTNING_SPIRIT);
        register("common_ice_spirit", COMMON_ICE_SPIRIT);
        register("common_time_space_spirit", COMMON_TIME_SPACE_SPIRIT);
        register("dense_metal_spirit", DENSE_METAL_SPIRIT);
        register("dense_wood_spirit", DENSE_WOOD_SPIRIT);
        register("dense_water_spirit", DENSE_WATER_SPIRIT);
        register("dense_fire_spirit", DENSE_FIRE_SPIRIT);
        register("dense_earth_spirit", DENSE_EARTH_SPIRIT);
        register("dense_wind_spirit", DENSE_WIND_SPIRIT);
        register("dense_light_spirit", DENSE_LIGHT_SPIRIT);
        register("dense_darkness_spirit", DENSE_DARKNESS_SPIRIT);
        register("dense_poison_spirit", DENSE_POISON_SPIRIT);
        register("dense_lightning_spirit", DENSE_LIGHTNING_SPIRIT);
        register("dense_ice_spirit", DENSE_ICE_SPIRIT);
        register("dense_time_space_spirit", DENSE_TIME_SPACE_SPIRIT);
        // End of Spirit items
        register("shurlin_ingot", SHURLIN_INGOT);
        register("shurlin_powerful_axe", SHURLIN_POWERFUL_AXE);
        // Use utility class to register
        ModToolItems.Register();
        ModArmorItems.Register();
    }
}
