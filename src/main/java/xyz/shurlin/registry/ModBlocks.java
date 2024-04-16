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
    public static Block PLANT_GOLD_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block PLANT_GOLD_BLOCK = new Block(FabricBlockSettings.copy(Blocks.GOLD_BLOCK));
    public static Block PLANT_JADE_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block PLANT_JADE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.DIAMOND_BLOCK));
    public static Block SMALL_BUD = new SmallBudBlock(BlockSettings.PLANT);
    public static Block PLATYCODON_GRANDIFLORUS = new PlatycodonGrandiflorusBlock(StatusEffects.REGENERATION, 1314, BlockSettings.PLANT);
    public static Block PEAR_LOG = new PillarBlock(BlockSettings.LOG);
    public static Block PEAR_PLANKS = new Block(BlockSettings.PLANKS);
    public static Block PEAR_LEAVES = new LeavesBlock(BlockSettings.LEAVES);
    public static Block PEAR_RIPE_LEAVES = new LeavesBlock(BlockSettings.LEAVES.strength(0.3f, 0.3f));
    public static Block PEAR_DOOR = new BasicDoorBlock();
    public static Block PHOENIX_LOG = new PillarBlock(BlockSettings.LOG);
    public static Block PHOENIX_PLANKS = new Block(BlockSettings.PLANKS);
    public static Block PHOENIX_LEAVES = new LeavesBlock(BlockSettings.LEAVES);
    public static Block BREAKER_BLOCK = new BreakerBlock(BlockSettings.WORKER);
    public static Block COLLECTOR_BLOCK = new CollectorBlock(BlockSettings.WORKER);
    public static Block CONCENTRATOR_BLOCK = new ConcentratorBlock(BlockSettings.WORKER);
    public static Block EXTRACTOR_BLOCK = new ExtractorBlock(BlockSettings.WORKER);
    public static Block HOLY_PEAR_ALTAR = new HolyPearAltarBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN));
    public static Block STARRY_ALTAR = new StarryAltarBlock(FabricBlockSettings.of(Material.STONE).strength(10.0f).luminance(12));
    public static Block MYSTERIOUS_STONE = new MysteriousStoneBlock(FabricBlockSettings.of(Material.STONE).strength(-1, 3600));
    public static Block PLANT_OBSIDIAN = new Block(BlockSettings.OBSIDIAN);
    public static Block HOLY_FARMER_PORTAL = new HolyFarmerPortalBlock(FabricBlockSettings.of(Material.PORTAL).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GLASS).luminance(12));
    public static Block CULTIVATION_CRYSTAL = new CultivationCrystalBlock(FabricBlockSettings.of(Material.GLASS).strength(-1).nonOpaque());
    public static Block SUCCULENT_BLOCK = new SucculentBlock(
            FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.PALE_GREEN).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME).nonOpaque()
                    .luminance((state) -> state.get(SucculentBlock.SHINING) ? 8 : 0)
    );
    public static Block LLANDUDNO = new Block(BlockSettings.LLANDUDNO);
    public static Block PEAR_SAPLING = new BasicSaplingBlock(ModConfiguredFeatures.PEAR_TREE, BlockSettings.PLANT);
    public static Block PHOENIX_SAPLING = new BasicSaplingBlock(ModConfiguredFeatures.PHOENIX_TREE, BlockSettings.PLANT);
    public static Block DEAD_LEAVE_CORAL = new DeadLeaveCoralBlock(BlockSettings.DEAD_CORAL);
    public static Block LEAVE_CORAL = new LeaveCoralBlock(DEAD_LEAVE_CORAL, BlockSettings.CORAL);
    public static Block DEAD_LEAVE_CORAL_FAN = new DeadLeaveCoralFanBlock(BlockSettings.DEAD_CORAL);
    public static Block LEAVE_CORAL_FAN = new LeaveCoralFanBlock(DEAD_LEAVE_CORAL_FAN, BlockSettings.CORAL);
    public static Block DEAD_LEAVE_CORAL_WALL_FAN = new DeadLeaveCoralWallFanBlock(BlockSettings.DEAD_CORAL.dropsLike(DEAD_LEAVE_CORAL_FAN));
    public static Block LEAVE_CORAL_WALL_FAN = new LeaveCoralWallFanBlock(DEAD_LEAVE_CORAL_WALL_FAN, BlockSettings.CORAL.dropsLike(LEAVE_CORAL_FAN));
    public static Block DEAD_LEAVE_CORAL_BLOCK = new Block(BlockSettings.DEAD_CORAL_BLOCK);
    public static Block LEAVE_CORAL_BLOCK = new LeaveCoralBlockBlock(DEAD_LEAVE_CORAL_BLOCK, BlockSettings.CORAL_BLOCK);
    public static Block HOT_FIRE_STONE = new Block(BlockSettings.STONE);
    public static Block HOT_FIRE_DIRT = new Block(BlockSettings.DIRT);
    public static Block TENUOUS_METAL_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_WOOD_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_WATER_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_FIRE_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_EARTH_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_WIND_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_LIGHT_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_POISON_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);
    public static Block TENUOUS_ICE_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE_IN_ICE);
    public static Block TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK = new SpiritOreBlock(BlockSettings.ORE);

    // We can do this later, just separate register Blocks and BlockItems, and remove this itemGroup parameter.
    // Also, we can move ItemGroup to each of item types after remove the BasicItem and BasicBlockItem
    private static void registerBlock(String name, Block block, boolean doRegisterDefaultBlockItem) {
        Block registeredBlock = Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, name), block);
        if (doRegisterDefaultBlockItem) {
            // Keep the temp solution cause the corals
            registerBlockItem(name, block, ItemGroups.SHURLIN);
        }
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
        registerBlock("plant_iron_ore_block", PLANT_IRON_ORE_BLOCK, true);
        registerBlock("plant_iron_block", PLANT_IRON_BLOCK, true);
        registerBlock("plant_gold_ore_block", PLANT_GOLD_ORE_BLOCK, true);
        registerBlock("plant_gold_block", PLANT_GOLD_BLOCK, true);
        registerBlock("plant_jade_ore_block", PLANT_JADE_ORE_BLOCK, true);
        registerBlock("plant_jade_block", PLANT_JADE_BLOCK, true);
        registerBlock("small_bud", SMALL_BUD, true);
        registerBlock("platycodon_grandiflorus", PLATYCODON_GRANDIFLORUS, true);
        registerBlock("pear_log", PEAR_LOG, true);
        registerBlock("pear_planks", PEAR_PLANKS, true);
        registerBlock("pear_leaves", PEAR_LEAVES, true);
        registerBlock("pear_ripe_leaves", PEAR_RIPE_LEAVES, true);
        registerBlock("pear_door", PEAR_DOOR, true);
        registerBlock("phoenix_log", PHOENIX_LOG, true);
        registerBlock("phoenix_planks", PHOENIX_PLANKS, true);
        registerBlock("phoenix_leaves", PHOENIX_LEAVES, true);
        // Worker Blocks
        registerBlock("breaker_block", BREAKER_BLOCK, true);
        registerBlock("collector_block", COLLECTOR_BLOCK, true);
        registerBlock("concentrator_block", CONCENTRATOR_BLOCK, true);
        registerBlock("extractor_block", EXTRACTOR_BLOCK, true);
        // Altars
        // Refactor these two later
        registerBlock("holy_pear_altar", HOLY_PEAR_ALTAR, true);
        registerBlock("starry_altar", STARRY_ALTAR, true);
        registerBlock("mysterious_stone", MYSTERIOUS_STONE, true);
        registerBlock("plant_obsidian", PLANT_OBSIDIAN, true);
        // Four stuff below are not supposed to have BlockItem
        registerBlock("holy_farmer_portal", HOLY_FARMER_PORTAL, false);
        registerBlock("cultivation_crystal", CULTIVATION_CRYSTAL, false);
        registerBlock("succulent_block", SUCCULENT_BLOCK, false);
        registerBlock("llandudno_block", LLANDUDNO, false);
        // Saplings
        registerBlock("pear_sapling", PEAR_SAPLING, true);
        registerBlock("phoenix_sapling", PHOENIX_SAPLING, true);
        // Coral related, Leave Coral related stuff need naming and structure changes, will figure it out later.
        registerBlock("dead_leave_coral", DEAD_LEAVE_CORAL, true);
        registerBlock("leave_coral", LEAVE_CORAL, true);
        registerBlock("dead_leave_coral_fan", DEAD_LEAVE_CORAL_FAN, false);
        registerBlock("leave_coral_fan", LEAVE_CORAL_FAN, false);
        registerBlock("dead_leave_coral_wall_fan", DEAD_LEAVE_CORAL_WALL_FAN, false);
        registerBlock("leave_coral_wall_fan", LEAVE_CORAL_WALL_FAN, false);
        registerBlock("dead_leave_coral_block", DEAD_LEAVE_CORAL_BLOCK, true);
        registerBlock("leave_coral_block", LEAVE_CORAL_BLOCK, true);
        // Fire land blocks
        registerBlock("hot_fire_stone", HOT_FIRE_STONE, true);
        registerBlock("hot_fire_dirt", HOT_FIRE_DIRT, true);
        // Spirit ore blocks
        registerBlock("tenuous_metal_spirit_ore_block", TENUOUS_METAL_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_wood_spirit_ore_block", TENUOUS_WOOD_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_water_spirit_ore_block", TENUOUS_WATER_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_fire_spirit_ore_block", TENUOUS_FIRE_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_earth_spirit_ore_block", TENUOUS_EARTH_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_wind_spirit_ore_block", TENUOUS_WIND_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_light_spirit_ore_block", TENUOUS_LIGHT_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_darkness_spirit_ore_block", TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_poison_spirit_ore_block", TENUOUS_POISON_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_lightning_spirit_ore_block", TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_ice_spirit_ore_block", TENUOUS_ICE_SPIRIT_ORE_BLOCK, true);
        registerBlock("tenuous_time_space_spirit_ore_block", TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK, true);
    }
}
