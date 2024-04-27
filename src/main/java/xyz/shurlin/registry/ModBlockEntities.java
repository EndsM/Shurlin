package xyz.shurlin.registry;


import net.minecraft.block.entity.BlockEntityType;
import xyz.shurlin.block.entity.CultivationCrystalBlockEntity;

public class ModBlockEntities {
    // I guess I also need to move this, so I can migrate the language feature of original one
    public static BlockEntityType<CultivationCrystalBlockEntity> CULTIVATION_CRYSTAL_BLOCK_ENTITY = BlockEntityType.Builder.create(CultivationCrystalBlockEntity::new, ModBlocks.CULTIVATION_CRYSTAL).build(null);

    private static void registerBlockEntity() {

    }

    public static void Register() {

    }
}
