package xyz.shurlin.registry;


import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.entity.AlchemyFurnaceBlockEntity;
import xyz.shurlin.block.entity.CultivationCrystalBlockEntity;
import xyz.shurlin.block.worker.entity.BreakerBlockEntity;
import xyz.shurlin.block.worker.entity.CollectorBlockEntity;
import xyz.shurlin.block.worker.entity.ConcentratorBlockEntity;
import xyz.shurlin.block.worker.entity.ExtractorBlockEntity;

public class ModBlockEntities {
    // TODO: implement datafixer in higher version of Minecraft, use .build(null) as temp solution
    public static BlockEntityType<CultivationCrystalBlockEntity> CULTIVATION_CRYSTAL_BLOCK_ENTITY = BlockEntityType.Builder.create(
            CultivationCrystalBlockEntity::new, ModBlocks.CULTIVATION_CRYSTAL
    ).build(null);
    public static BlockEntityType<BreakerBlockEntity> BREAKER_BLOCK_ENTITY = BlockEntityType.Builder.create(
            BreakerBlockEntity::new, ModBlocks.BREAKER_BLOCK
    ).build(null);
    public static BlockEntityType<CollectorBlockEntity> COLLECTOR_BLOCK_ENTITY = BlockEntityType.Builder.create(
            CollectorBlockEntity::new, ModBlocks.COLLECTOR_BLOCK
    ).build(null);
    public static BlockEntityType<ConcentratorBlockEntity> CONCENTRATOR_BLOCK_ENTITY = BlockEntityType.Builder.create(
            ConcentratorBlockEntity::new, ModBlocks.CONCENTRATOR_BLOCK
    ).build(null);
    public static BlockEntityType<ExtractorBlockEntity> EXTRACTOR_BLOCK_ENTITY = BlockEntityType.Builder.create(
            ExtractorBlockEntity::new, ModBlocks.EXTRACTOR_BLOCK
    ).build(null);
    public static BlockEntityType<AlchemyFurnaceBlockEntity> LLANDUDNO_BLOCK_ENTITY = BlockEntityType.Builder.create(
            AlchemyFurnaceBlockEntity::new, ModBlocks.LLANDUDNO
    ).build(null);

    private static void registerBlockEntity(String id, BlockEntityType<?> blockEntityType) {
        // This function should take builder as parameter in the future
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Shurlin.MODID, id), blockEntityType);
    }

    public static void Register() {
        registerBlockEntity("cultivation_crystal_block_entity", CULTIVATION_CRYSTAL_BLOCK_ENTITY);
        registerBlockEntity("breaker_block_entity", BREAKER_BLOCK_ENTITY);
        registerBlockEntity("collector_block_entity", COLLECTOR_BLOCK_ENTITY);
        registerBlockEntity("concentrator_block_entity", CONCENTRATOR_BLOCK_ENTITY);
        registerBlockEntity("extractor_block_entity", EXTRACTOR_BLOCK_ENTITY);
        registerBlockEntity("llandudno_block_entity", LLANDUDNO_BLOCK_ENTITY);
    }
}
