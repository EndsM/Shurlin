package xyz.shurlin.registry.setting;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockSettings {
    // Constants
    public static final FabricBlockSettings ORE;
    public static final FabricBlockSettings STONE;
    public static final FabricBlockSettings DIRT;
    public static final FabricBlockSettings LOG;
    public static final FabricBlockSettings LEAVES;
    public static final FabricBlockSettings PLANKS;
    public static final FabricBlockSettings PLANT;
    public static final FabricBlockSettings CORAL;
    public static final FabricBlockSettings DEAD_CORAL;
    public static final FabricBlockSettings CORAL_BLOCK;
    public static final FabricBlockSettings DEAD_CORAL_BLOCK;
    public static final FabricBlockSettings OBSIDIAN;
    public static final FabricBlockSettings WORKER;
    public static final FabricBlockSettings LLANDUDNO;

    static {
        PLANT = FabricBlockSettings.of(Material.PLANT)
                .noCollision().ticksRandomly().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS);
        LOG = FabricBlockSettings.of(Material.WOOD)
                .strength(2.0f).sounds(BlockSoundGroup.WOOD);
        LEAVES = FabricBlockSettings.of(Material.LEAVES)
                .strength(0.2f, 0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque();
        PLANKS = FabricBlockSettings.of(Material.WOOD)
                .strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
        ORE = FabricBlockSettings.of(Material.STONE)
                .requiresTool().strength(3.0f, 3.0f);
        CORAL = FabricBlockSettings.of(Material.UNDERWATER_PLANT, MapColor.GREEN)
                .noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS);
        DEAD_CORAL = FabricBlockSettings.of(Material.STONE, MapColor.GRAY)
                .requiresTool().noCollision().breakInstantly();
        CORAL_BLOCK = FabricBlockSettings.of(Material.STONE, MapColor.GREEN)
                .requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL);
        DEAD_CORAL_BLOCK = FabricBlockSettings.of(Material.STONE, MapColor.GRAY)
                .requiresTool().strength(1.5F, 6.0F);
        OBSIDIAN = FabricBlockSettings.of(Material.STONE)
                .requiresTool().strength(60.0F, 1200.0F);
        WORKER = FabricBlockSettings.of(Material.STONE)
                .strength(10.0f, 10.0f);
        STONE = FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
                .requiresTool().strength(1.5F, 6.0F);
        DIRT = FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN)
                .strength(0.5F).sounds(BlockSoundGroup.GRAVEL);
        LLANDUDNO = FabricBlockSettings.of(Material.METAL)
                .strength(20.0f, 100.0f).requiresTool().sounds(BlockSoundGroup.METAL);
    }
}
