package xyz.shurlin.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.PlatycodonGrandiflorusBlock;
import xyz.shurlin.block.SmallBudBlock;
import xyz.shurlin.block.SpiritOreBlock;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.registry.setting.BlockSettings;

public class ModBlocks {
    public static Block PLANT_IRON_ORE_BLOCK;
    public static Block PLANT_IRON_BLOCK;
    public static Block PLANT_GOLD_ORE_BLOCK;
    public static Block PLANT_GOLD_BLOCK;
    public static Block PLANT_JADE_ORE_BLOCK;
    public static Block PLANT_JADE_BLOCK;
    public static Block SMALL_BUD;
    public static Block PLATYCODON_GRANDIFLORUS;
    public static Block PEAR_LOG;
    public static Block PEAR_PLANKS;
    public static Block PEAR_LEAVES;
    public static Block PEAR_RIPE_LEAVES;

    // We can do this later, just separate register Blocks and BlockItems, and remove this itemGroup parameter.
    // Also, we can move ItemGroup to each of item types after remove the BasicItem and BasicBlockItem
    private static Block registerBlock(String name, Block block, ItemGroup itemGroup) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        if (itemGroup != null) {
            registerBlockItem(name, block, itemGroup);
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
        PLANT_IRON_BLOCK = registerBlock("plant_iron_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK)), ItemGroups.SHURLIN);
        PLANT_GOLD_ORE_BLOCK = registerBlock("plant_gold_ore_block", new SpiritOreBlock(BlockSettings.ORE), ItemGroups.SHURLIN);
        PLANT_GOLD_BLOCK = registerBlock("plant_gold_block", new Block(FabricBlockSettings.copy(Blocks.GOLD_BLOCK)), ItemGroups.SHURLIN);
        PLANT_JADE_ORE_BLOCK = registerBlock("plant_jade_ore_block", new SpiritOreBlock(BlockSettings.ORE), ItemGroups.SHURLIN);
        PLANT_JADE_BLOCK = registerBlock("plant_jade_block", new Block(FabricBlockSettings.copy(Blocks.DIAMOND_BLOCK)), ItemGroups.SHURLIN);
        SMALL_BUD = registerBlock("small_bud", new SmallBudBlock(BlockSettings.PLANT), ItemGroups.SHURLIN);
        PLATYCODON_GRANDIFLORUS = registerBlock("platycodon_grandiflorus", new PlatycodonGrandiflorusBlock(StatusEffects.REGENERATION, 1314, BlockSettings.PLANT), ItemGroups.SHURLIN);
        PEAR_LOG = registerBlock("pear_log", new PillarBlock(BlockSettings.LOG), ItemGroups.SHURLIN);
        PEAR_PLANKS = registerBlock("pear_planks" , new Block(BlockSettings.PLANKS),ItemGroups.SHURLIN);
        PEAR_LEAVES = registerBlock("pear_leaves", new LeavesBlock(BlockSettings.LEAVES),ItemGroups.SHURLIN);
        PEAR_RIPE_LEAVES = registerBlock("pear_ripe_leaves", new LeavesBlock(BlockSettings.LEAVES.strength(0.3f,0.3f)),ItemGroups.SHURLIN);
    }
}
