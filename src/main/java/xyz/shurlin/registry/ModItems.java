package xyz.shurlin.registry;


import net.minecraft.block.Block;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.item.*;

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

    // in-class utility functions below
    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
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
    }
}
