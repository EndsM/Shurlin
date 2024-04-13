package xyz.shurlin.registry;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.item.ItemGroups;

public class ModItems {
    public static Item DEAD_LEAVE_CORAL_FAN_BLOCK;
    public static Item LEAVE_CORAL_FAN_BLOCK;

    private static String getBlockId(Block block){
        return Registry.BLOCK.getId(block).getPath();
    }

    public static void Register(){
        DEAD_LEAVE_CORAL_FAN_BLOCK = ModBlocks.registerWallStandingBlockItem( getBlockId(ModBlocks.DEAD_LEAVE_CORAL_FAN),ModBlocks.DEAD_LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
        LEAVE_CORAL_FAN_BLOCK=ModBlocks.registerWallStandingBlockItem(getBlockId(ModBlocks.LEAVE_CORAL_FAN),ModBlocks.LEAVE_CORAL_FAN, ItemGroups.SHURLIN);
    }
}
