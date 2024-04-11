package xyz.shurlin.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.shurlin.Shurlin;

public class ModBlocks {
    private static final Logger logger = LogManager.getLogger(Shurlin.MODID);

    private Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        return registeredBlock;
    }

    private Item registerBlockItem(String name, Block block) {
        Item registeredBlockItem = Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), new BlockItem(block, new Item.Settings()));
        return registeredBlockItem;
    }
}
