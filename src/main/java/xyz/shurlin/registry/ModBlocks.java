package xyz.shurlin.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.*;
import xyz.shurlin.block.coral.*;
import xyz.shurlin.block.plant.BasicSaplingBlock;
import xyz.shurlin.block.worker.BreakerBlock;
import xyz.shurlin.block.worker.CollectorBlock;
import xyz.shurlin.block.worker.ConcentratorBlock;
import xyz.shurlin.block.worker.ExtractorBlock;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.registry.features.ModConfiguredFeatures;
import xyz.shurlin.registry.setting.BlockSettings;

public class ModBlocks {
    // So basically, this will serve as the block var we can use other places.
    // And registry goes like just a process, not an assignment process
    // Can change these variables to constants later
    public static Block PLANT_IRON_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block PLANT_IRON_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
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
    public static Block LLANDUDNO;
    public static Block PEAR_SAPLING;
    public static Block PHOENIX_SAPLING;
    public static Block DEAD_LEAVE_CORAL;
    public static Block LEAVE_CORAL;
    public static Block DEAD_LEAVE_CORAL_FAN;
    public static Block LEAVE_CORAL_FAN;
    public static Block DEAD_LEAVE_CORAL_WALL_FAN;
    public static Block LEAVE_CORAL_WALL_FAN;
    public static Block DEAD_LEAVE_CORAL_BLOCK;
    public static Block LEAVE_CORAL_BLOCK;
    public static Block HOT_FIRE_STONE;
    public static Block HOT_FIRE_DIRT;
    public static Block TENUOUS_METAL_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_WOOD_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_WATER_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_FIRE_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_EARTH_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_WIND_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_LIGHT_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_POISON_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_ICE_SPIRIT_ORE_BLOCK;
    public static Block TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK;

    // We can do this later, just separate register Blocks and BlockItems, and remove this itemGroup parameter.
    // Also, we can move ItemGroup to each of item types after remove the BasicItem and BasicBlockItem
    private static Block registerBlock(String name, Block block, boolean doRegisterDefaultBlockItem) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        if (doRegisterDefaultBlockItem) {
            // Keep the temp solution cause the corals
            registerBlockItem(name, block, ItemGroups.SHURLIN);
        }
        return registeredBlock;
    }

    // Will make each registry also use this later
    private static Item registerBlockItem(String name, Block block, ItemGroup itemGroup) {
        Item registeredBlockItem = Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), new BlockItem(block, new Item.Settings().group(itemGroup)));
        return registeredBlockItem;
    }

    public static Item registerWallStandingBlockItem(String name, Block block, ItemGroup itemGroup) {
        Item registeredBlockItem = Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), new WallStandingBlockItem(block, block, new Item.Settings().group(itemGroup)));
        return registeredBlockItem;
    }

    // TODO: make it looks like ModConfiguredFeature.java
    public static void Register() {
        // Register blocks and block items
        PLANT_IRON_ORE_BLOCK = registerBlock("plant_iron_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        PLANT_IRON_BLOCK = registerBlock("plant_iron_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK)), true);
        PLANT_GOLD_ORE_BLOCK = registerBlock("plant_gold_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        PLANT_GOLD_BLOCK = registerBlock("plant_gold_block", new Block(FabricBlockSettings.copy(Blocks.GOLD_BLOCK)), true);
        PLANT_JADE_ORE_BLOCK = registerBlock("plant_jade_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        PLANT_JADE_BLOCK = registerBlock("plant_jade_block", new Block(FabricBlockSettings.copy(Blocks.DIAMOND_BLOCK)), true);
        SMALL_BUD = registerBlock("small_bud", new SmallBudBlock(BlockSettings.PLANT), true);
        PLATYCODON_GRANDIFLORUS = registerBlock("platycodon_grandiflorus", new PlatycodonGrandiflorusBlock(StatusEffects.REGENERATION, 1314, BlockSettings.PLANT), true);
        PEAR_LOG = registerBlock("pear_log", new PillarBlock(BlockSettings.LOG), true);
        PEAR_PLANKS = registerBlock("pear_planks", new Block(BlockSettings.PLANKS), true);
        PEAR_LEAVES = registerBlock("pear_leaves", new LeavesBlock(BlockSettings.LEAVES), true);
        PEAR_RIPE_LEAVES = registerBlock("pear_ripe_leaves", new LeavesBlock(BlockSettings.LEAVES.strength(0.3f, 0.3f)), true);
        PEAR_DOOR = registerBlock("pear_door", new BasicDoorBlock(), true);
        PHOENIX_LOG = registerBlock("phoenix_log", new PillarBlock(BlockSettings.LOG), true);
        PHOENIX_PLANKS = registerBlock("phoenix_planks", new Block(BlockSettings.PLANKS), true);
        PHOENIX_LEAVES = registerBlock("phoenix_leaves", new LeavesBlock(BlockSettings.LEAVES), true);
        // Worker Blocks
        BREAKER_BLOCK = registerBlock("breaker_block", new BreakerBlock(BlockSettings.WORKER), true);
        COLLECTOR_BLOCK = registerBlock("collector_block", new CollectorBlock(BlockSettings.WORKER), true);
        CONCENTRATOR_BLOCK = registerBlock("concentrator_block", new ConcentratorBlock(BlockSettings.WORKER), true);
        EXTRACTOR_BLOCK = registerBlock("extractor_block", new ExtractorBlock(BlockSettings.WORKER), true);
        // Altars
        // Refactor these two later
        HOLY_PEAR_ALTAR = registerBlock("holy_pear_altar", new HolyPearAltarBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN)), true);
        STARRY_ALTAR = registerBlock("starry_altar", new StarryAltarBlock(FabricBlockSettings.of(Material.STONE).strength(10.0f).luminance(12)), true);
        MYSTERIOUS_STONE = registerBlock("mysterious_stone", new MysteriousStoneBlock(FabricBlockSettings.of(Material.STONE).strength(-1, 3600)), true);
        PLANT_OBSIDIAN = registerBlock("plant_obsidian", new Block(BlockSettings.OBSIDIAN), true);
        // Four stuff below are not supposed to have BlockItem
        HOLY_FARMER_PORTAL = registerBlock("holy_farmer_portal", new HolyFarmerPortalBlock(FabricBlockSettings.of(Material.PORTAL).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GLASS).luminance(12)), false);
        CULTIVATION_CRYSTAL = registerBlock("cultivation_crystal", new CultivationCrystalBlock(FabricBlockSettings.of(Material.GLASS).strength(-1).nonOpaque()), false);
        SUCCULENT_BLOCK = registerBlock("succulent_block", new SucculentBlock(
                FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.PALE_GREEN).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME).nonOpaque()
                        .luminance((state) -> state.get(SucculentBlock.SHINING) ? 8 : 0)
        ), false);
        LLANDUDNO = registerBlock("llandudno_block", new Block(BlockSettings.LLANDUDNO), false);
        // Saplings
        PEAR_SAPLING = registerBlock("pear_sapling", new BasicSaplingBlock(ModConfiguredFeatures.PEAR_TREE, BlockSettings.PLANT), true);
        PHOENIX_SAPLING = registerBlock("phoenix_sapling", new BasicSaplingBlock(ModConfiguredFeatures.PHOENIX_TREE, BlockSettings.PLANT), true);
        // Coral related, Leave Coral related stuff need naming and structure changes, will figure it out later.
        DEAD_LEAVE_CORAL = registerBlock("dead_leave_coral", new DeadLeaveCoralBlock(BlockSettings.DEAD_CORAL), true);
        LEAVE_CORAL = registerBlock("leave_coral", new LeaveCoralBlock(DEAD_LEAVE_CORAL, BlockSettings.CORAL), true);
        DEAD_LEAVE_CORAL_FAN = registerBlock("dead_leave_coral_fan", new DeadLeaveCoralFanBlock(BlockSettings.DEAD_CORAL), false);
        LEAVE_CORAL_FAN = registerBlock("leave_coral_fan", new LeaveCoralFanBlock(DEAD_LEAVE_CORAL_FAN, BlockSettings.CORAL), false);
        DEAD_LEAVE_CORAL_WALL_FAN = registerBlock("dead_leave_coral_wall_fan", new DeadLeaveCoralWallFanBlock(BlockSettings.DEAD_CORAL.dropsLike(DEAD_LEAVE_CORAL_FAN)), false);
        LEAVE_CORAL_WALL_FAN = registerBlock("leave_coral_wall_fan", new LeaveCoralWallFanBlock(DEAD_LEAVE_CORAL_WALL_FAN, BlockSettings.CORAL.dropsLike(LEAVE_CORAL_FAN)), false);
        DEAD_LEAVE_CORAL_BLOCK = registerBlock("dead_leave_coral_block", new Block(BlockSettings.DEAD_CORAL_BLOCK), true);
        LEAVE_CORAL_BLOCK = registerBlock("leave_coral_block", new LeaveCoralBlockBlock(DEAD_LEAVE_CORAL_BLOCK, BlockSettings.CORAL_BLOCK), true);
        // Fire land blocks
        HOT_FIRE_STONE = registerBlock("hot_fire_stone", new Block(BlockSettings.STONE), true);
        HOT_FIRE_DIRT = registerBlock("hot_fire_dirt", new Block(BlockSettings.DIRT), true);
        // Spirit ore blocks
        TENUOUS_METAL_SPIRIT_ORE_BLOCK = registerBlock("tenuous_metal_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_WOOD_SPIRIT_ORE_BLOCK = registerBlock("tenuous_wood_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_WATER_SPIRIT_ORE_BLOCK = registerBlock("tenuous_water_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_FIRE_SPIRIT_ORE_BLOCK = registerBlock("tenuous_fire_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_EARTH_SPIRIT_ORE_BLOCK = registerBlock("tenuous_earth_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_WIND_SPIRIT_ORE_BLOCK = registerBlock("tenuous_wind_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_LIGHT_SPIRIT_ORE_BLOCK = registerBlock("tenuous_light_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK = registerBlock("tenuous_darkness_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_POISON_SPIRIT_ORE_BLOCK = registerBlock("tenuous_poison_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK = registerBlock("tenuous_lightning_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
        TENUOUS_ICE_SPIRIT_ORE_BLOCK = registerBlock("tenuous_ice_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE_IN_ICE), true);
        TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK = registerBlock("tenuous_time_space_spirit_ore_block", new SpiritOreBlock(BlockSettings.ORE), true);
    }
}
