package xyz.shurlin.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.BasicBlock;
import xyz.shurlin.block.SpiritOreBlock;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.registry.setting.BlockSettings;

import java.util.Optional;

public class ModBlocks {
    public static Block PLANT_IRON_ORE_BLOCK;
    public static Block PLANT_IRON_BLOCK;
    public  static Block PLANT_GOLD_ORE_BLOCK;

    private static Block registerBlock(String name, Block block, ItemGroup itemGroup) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        if (itemGroup!=null ) {
            registerBlockItem(name, block,itemGroup);
        }
        return registeredBlock;
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup itemGroup) {
        Item registeredBlockItem = Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), new BlockItem(block, new Item.Settings().group(itemGroup)));
        return registeredBlockItem;
    }

    public static void Register() {
        // Register blocks and block items
        PLANT_IRON_ORE_BLOCK = registerBlock("plant_iron_ore_block", new SpiritOreBlock(BlockSettings.ORE), ItemGroups.SHURLIN);
        PLANT_IRON_BLOCK = registerBlock("plant_iron_block", new BasicBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK)),ItemGroups.SHURLIN);
        PLANT_GOLD_ORE_BLOCK = registerBlock("plant_gold_ore_block",new SpiritOreBlock(BlockSettings.ORE),ItemGroups.SHURLIN);
    }
}
