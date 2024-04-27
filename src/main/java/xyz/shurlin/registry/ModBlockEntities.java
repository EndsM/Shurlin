package xyz.shurlin.registry;


import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.entity.CultivationCrystalBlockEntity;

public class ModBlockEntities {
    // TODO: implement datafixer in higher version of Minecraft, use .build(null) as temp solution
    public static BlockEntityType<CultivationCrystalBlockEntity> CULTIVATION_CRYSTAL_BLOCK_ENTITY = BlockEntityType.Builder.create(CultivationCrystalBlockEntity::new, ModBlocks.CULTIVATION_CRYSTAL).build(null);

    private static void registerBlockEntity(String id, BlockEntityType<?> blockEntityType) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Shurlin.MODID, id), blockEntityType);
    }

    public static void Register() {
        registerBlockEntity("cultivation_crystal_block_entity", CULTIVATION_CRYSTAL_BLOCK_ENTITY);
    }
}
