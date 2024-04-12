package xyz.shurlin.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;

public class ModBlocks {
    public static Block NEW_FORMAT_TEST_BLOCK;

    private static Block registerBlock(String name, Block block, boolean doRegisterBlockItem) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        if (doRegisterBlockItem){
            registerBlockItem(name,block);
        }
        return registeredBlock;
    }

    private static Item registerBlockItem(String name, Block block) {
        Item registeredBlockItem = Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), new BlockItem(block, new Item.Settings()));
        return registeredBlockItem;
    }

    public static void Register(){
        NEW_FORMAT_TEST_BLOCK = registerBlock("new_format_test_block", new Block(FabricBlockSettings.copy(Blocks.COAL_BLOCK)),true);

    }
}
