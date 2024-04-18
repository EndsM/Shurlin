package xyz.shurlin.registry;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.item.ItemGroups;

public class ModItems {
    // These two are using ModBlocks class' function
    public static Item DEAD_LEAVE_CORAL_FAN_BLOCK;
    public static Item LEAVE_CORAL_FAN_BLOCK;
    // Down below will just use constants I think...
    // maybe separate assignment and declare later
    public static final Item PLANT_MIXTURE = new Item(new Item.Settings());
    public static final Item PLANT_MIXTURE_HEAP = new Item(new Item.Settings());
    // Plant Essences
    public static final Item PLANT_ESSENCE_PARTICLE = new Item(new Item.Settings());
    public static final Item PLANT_ESSENCE =new Item(new Item.Settings());

    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
        // Special BlockItem
        DEAD_LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.DEAD_LEAVE_CORAL_FAN), ModBlocks.DEAD_LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.LEAVE_CORAL_FAN), ModBlocks.LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        // Items
        register("plant_mixture", PLANT_MIXTURE);
        register("plant_mixture_heap", PLANT_MIXTURE_HEAP);
        register("plant_essence_particle", PLANT_ESSENCE_PARTICLE);
        register("plant_essence",PLANT_ESSENCE);
    }
}
