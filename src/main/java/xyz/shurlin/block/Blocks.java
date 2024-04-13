package xyz.shurlin.block;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.coral.*;
import xyz.shurlin.block.plant.BasicSaplingBlock;
import xyz.shurlin.world.gen.feature.ShurlinConfiguredFeatures;

public class Blocks {
    public static final Block LLANDUDNO;

    public static final Block PEAR_SAPLING;
    public static final Block PHOENIX_SAPLING;

    public static final Block DEAD_LEAVE_CORAL;
    public static final Block LEAVE_CORAL;
    public static final Block DEAD_LEAVE_CORAL_FAN;
    public static final Block LEAVE_CORAL_FAN;
    public static final Block DEAD_LEAVE_CORAL_WALL_FAN;
    public static final Block LEAVE_CORAL_WALL_FAN;
    public static final Block DEAD_LEAVE_CORAL_BLOCK;
    public static final Block LEAVE_CORAL_BLOCK;

    public static final Block HOT_FIRE_STONE;
    public static final Block HOT_FIRE_DIRT;

    public static final Block TENUOUS_METAL_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_WOOD_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_WATER_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_FIRE_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_EARTH_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_WIND_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_LIGHT_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_POISON_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_ICE_SPIRIT_ORE_BLOCK;
    public static final Block TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK;

    private static Block register(String registryName, Block.Settings settings) {
        return register(registryName, new BasicBlock(settings));
    }

    private static Block registerOreBlock(String registryName) {
        return register(registryName, new SpiritOreBlock(BlockSettings.ORE));
    }

    private static Block registerOreBlock(String registryName, Material material) {
        return register(registryName, new OreBlock(FabricBlockSettings.of(material).requiresTool().strength(3.0f, 3.0f)));
    }

    private static Block register(String registryName, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Shurlin.MODID, registryName), block);
    }

    static {
        LLANDUDNO = register("llandudno_block", BlockSettings.LLANDUDNO);

        DEAD_LEAVE_CORAL = register("dead_leave_coral", new DeadLeaveCoralBlock(BlockSettings.DEAD_CORAL));
        LEAVE_CORAL = register("leave_coral", new LeaveCoralBlock(DEAD_LEAVE_CORAL, BlockSettings.CORAL));
        DEAD_LEAVE_CORAL_FAN = register("dead_leave_coral_fan", new DeadLeaveCoralFanBlock(BlockSettings.DEAD_CORAL));
        LEAVE_CORAL_FAN = register("leave_coral_fan", new LeaveCoralFanBlock(DEAD_LEAVE_CORAL_FAN, BlockSettings.CORAL));
        DEAD_LEAVE_CORAL_WALL_FAN = register("dead_leave_coral_wall_fan", new DeadLeaveCoralWallFanBlock(BlockSettings.DEAD_CORAL.dropsLike(DEAD_LEAVE_CORAL_FAN)));
        LEAVE_CORAL_WALL_FAN = register("leave_coral_wall_fan", new LeaveCoralWallFanBlock(DEAD_LEAVE_CORAL_WALL_FAN, BlockSettings.CORAL.dropsLike(LEAVE_CORAL_FAN)));
        DEAD_LEAVE_CORAL_BLOCK = register("dead_leave_coral_block", new Block(BlockSettings.DEAD_CORAL_BLOCK));
        LEAVE_CORAL_BLOCK = register("leave_coral_block", new LeaveCoralBlockBlock(DEAD_LEAVE_CORAL_BLOCK, BlockSettings.CORAL_BLOCK));

        HOT_FIRE_STONE = register("hot_fire_stone", BlockSettings.STONE);
        HOT_FIRE_DIRT = register("hot_fire_dirt", BlockSettings.DIRT);

        TENUOUS_METAL_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_metal_spirit_ore_block");
        TENUOUS_WOOD_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_wood_spirit_ore_block");
        TENUOUS_WATER_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_water_spirit_ore_block");
        TENUOUS_FIRE_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_fire_spirit_ore_block");
        TENUOUS_EARTH_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_earth_spirit_ore_block");
        TENUOUS_WIND_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_wind_spirit_ore_block");
        TENUOUS_LIGHT_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_light_spirit_ore_block");
        TENUOUS_DARKNESS_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_darkness_spirit_ore_block");
        TENUOUS_POISON_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_poison_spirit_ore_block");
        TENUOUS_LIGHTNING_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_lightning_spirit_ore_block");
        TENUOUS_ICE_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_ice_spirit_ore_block", Material.ICE);
        TENUOUS_TIME_SPACE_SPIRIT_ORE_BLOCK = registerOreBlock("tenuous_time_space_spirit_ore_block");

        PEAR_SAPLING = register("pear_sapling", new BasicSaplingBlock(ShurlinConfiguredFeatures.PEAR_TREE, BlockSettings.PLANT));
        PHOENIX_SAPLING = register("phoenix_sapling", new BasicSaplingBlock(ShurlinConfiguredFeatures.PHOENIX_TREE, BlockSettings.PLANT));

    }

    private static class BlockSettings {
        private static final FabricBlockSettings ORE;
        private static final FabricBlockSettings STONE;
        private static final FabricBlockSettings DIRT;
        private static final FabricBlockSettings LOG;
        private static final FabricBlockSettings LEAVES;
        private static final FabricBlockSettings PLANKS;
        private static final FabricBlockSettings PLANT;
        private static final FabricBlockSettings CORAL;
        private static final FabricBlockSettings DEAD_CORAL;
        private static final FabricBlockSettings CORAL_BLOCK;
        private static final FabricBlockSettings DEAD_CORAL_BLOCK;
        private static final FabricBlockSettings OBSIDIAN;
        private static final FabricBlockSettings WORKER;
        private static final FabricBlockSettings LLANDUDNO;

        static {
            PLANT = FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS);
            LOG = FabricBlockSettings.of(Material.WOOD).strength(2.0f).sounds(BlockSoundGroup.WOOD);
            LEAVES = FabricBlockSettings.of(Material.LEAVES).strength(0.2f, 0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque();
            PLANKS = FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
            ORE = FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f);
            CORAL = FabricBlockSettings.of(Material.UNDERWATER_PLANT, MapColor.GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS);
            DEAD_CORAL = FabricBlockSettings.of(Material.STONE, MapColor.GRAY).requiresTool().noCollision().breakInstantly();
            CORAL_BLOCK = FabricBlockSettings.of(Material.STONE, MapColor.GREEN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL);
            DEAD_CORAL_BLOCK = FabricBlockSettings.of(Material.STONE, MapColor.GRAY).requiresTool().strength(1.5F, 6.0F);
            OBSIDIAN = FabricBlockSettings.of(Material.STONE).requiresTool().strength(60.0F, 1200.0F);
            WORKER = FabricBlockSettings.of(Material.STONE).strength(10.0f, 10.0f);
            STONE = FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F);
            DIRT = FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL);
            LLANDUDNO = FabricBlockSettings.of(Material.METAL).strength(20.0f, 100.0f).requiresTool().sounds(BlockSoundGroup.METAL);
        }
    }
}
