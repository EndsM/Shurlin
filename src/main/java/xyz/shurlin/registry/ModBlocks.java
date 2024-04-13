package xyz.shurlin.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.*;
import xyz.shurlin.block.worker.BreakerBlock;
import xyz.shurlin.block.worker.CollectorBlock;
import xyz.shurlin.block.worker.ConcentratorBlock;
import xyz.shurlin.block.worker.ExtractorBlock;
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
    public static Block PEAR_DOOR;
    public static Block PHOENIX_LOG;
    public static Block PHOENIX_PLANKS;
    public static Block PHOENIX_LEAVES;
    public static Block BREAKER_BLOCK;
    public static Block COLLECTOR_BLOCK;
    public static Block CONCENTRATOR_BLOCK;
    public static Block EXTRACTOR_BLOCK;
    public static Block HOLY_PEAR_ALTAR;
    public static Block STARRY_ALTAR;
    public static Block MYSTERIOUS_STONE;
    public static Block PLANT_OBSIDIAN;
    public static Block HOLY_FARMER_PORTAL;
    public static Block CULTIVATION_CRYSTAL;
    public static Block SUCCULENT_BLOCK;

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
        PEAR_PLANKS = registerBlock("pear_planks", new Block(BlockSettings.PLANKS), ItemGroups.SHURLIN);
        PEAR_LEAVES = registerBlock("pear_leaves", new LeavesBlock(BlockSettings.LEAVES), ItemGroups.SHURLIN);
        PEAR_RIPE_LEAVES = registerBlock("pear_ripe_leaves", new LeavesBlock(BlockSettings.LEAVES.strength(0.3f, 0.3f)), ItemGroups.SHURLIN);
        PEAR_DOOR = registerBlock("pear_door", new BasicDoorBlock(), ItemGroups.SHURLIN);
        PHOENIX_LOG = registerBlock("phoenix_log", new PillarBlock(BlockSettings.LOG), ItemGroups.SHURLIN);
        PHOENIX_PLANKS = registerBlock("phoenix_planks", new Block(BlockSettings.PLANKS), ItemGroups.SHURLIN);
        PHOENIX_LEAVES = registerBlock("phoenix_leaves", new LeavesBlock(BlockSettings.LEAVES), ItemGroups.SHURLIN);
        // Worker Blocks
        BREAKER_BLOCK = registerBlock("breaker_block", new BreakerBlock(BlockSettings.WORKER), ItemGroups.SHURLIN);
        COLLECTOR_BLOCK = registerBlock("collector_block", new CollectorBlock(BlockSettings.WORKER), ItemGroups.SHURLIN);
        CONCENTRATOR_BLOCK = registerBlock("concentrator_block", new ConcentratorBlock(BlockSettings.WORKER), ItemGroups.SHURLIN);
        EXTRACTOR_BLOCK = registerBlock("extractor_block", new ExtractorBlock(BlockSettings.WORKER), ItemGroups.SHURLIN);
        // Altars
        // Refactor these two later
        HOLY_PEAR_ALTAR = registerBlock("holy_pear_altar", new HolyPearAltarBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN)), ItemGroups.SHURLIN);
        STARRY_ALTAR = registerBlock("starry_altar", new StarryAltarBlock(FabricBlockSettings.of(Material.STONE).strength(10.0f).luminance(12)), ItemGroups.SHURLIN);
        MYSTERIOUS_STONE = registerBlock("mysterious_stone", new MysteriousStoneBlock(FabricBlockSettings.of(Material.STONE).strength(-1, 3600)), ItemGroups.SHURLIN);
        PLANT_OBSIDIAN = registerBlock("plant_obsidian", new Block(BlockSettings.OBSIDIAN), ItemGroups.SHURLIN);
        HOLY_FARMER_PORTAL = registerBlock("holy_farmer_portal", new HolyFarmerPortalBlock(FabricBlockSettings.of(Material.PORTAL).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GLASS).luminance(12)), ItemGroups.SHURLIN);
        CULTIVATION_CRYSTAL = registerBlock("cultivation_crystal", new CultivationCrystalBlock(FabricBlockSettings.of(Material.GLASS).strength(-1).nonOpaque()), ItemGroups.SHURLIN);
        SUCCULENT_BLOCK =registerBlock("succulent_block", new SucculentBlock(
                FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.PALE_GREEN).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME).nonOpaque()
                        .luminance((state)-> state.get(SucculentBlock.SHINING)? 8:0)
        ),ItemGroups.SHURLIN);

    }
}
