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
    public static final Item PLANT_MIXTURE = null;

    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
        DEAD_LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.DEAD_LEAVE_CORAL_FAN), ModBlocks.DEAD_LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.LEAVE_CORAL_FAN), ModBlocks.LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
    }
}
