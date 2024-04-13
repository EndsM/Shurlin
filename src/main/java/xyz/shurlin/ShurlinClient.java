package xyz.shurlin;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import xyz.shurlin.block.Blocks;
import xyz.shurlin.client.gui.screen.HandledScreens;
import xyz.shurlin.client.options.KeyBindings;
import xyz.shurlin.client.render.entity.RoamingSpiritEntityRender;
import xyz.shurlin.entity.EntityTypes;
import xyz.shurlin.linkage.Linkage;
import xyz.shurlin.registry.ModBlocks;

@Environment(EnvType.CLIENT)
public class ShurlinClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.SMALL_BUD,
                Blocks.PEAR_SAPLING,
                Blocks.PHOENIX_SAPLING,
                ModBlocks.PLATYCODON_GRANDIFLORUS,
                Blocks.PEAR_DOOR,
                Blocks.LEAVE_CORAL,
                Blocks.DEAD_LEAVE_CORAL,
                Blocks.LEAVE_CORAL_FAN,
                Blocks.DEAD_LEAVE_CORAL_FAN,
                Blocks.LEAVE_CORAL_WALL_FAN,
                Blocks.DEAD_LEAVE_CORAL_WALL_FAN);
        // BlockRenderLayerMap.INSTANCE.putItem(Items.TENUOUS_WOOD_SPIRIT, RenderLayer.());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                Blocks.HOLY_FARMER_PORTAL,
                Blocks.CULTIVATION_CRYSTAL);

        // EntityRendererRegistry.INSTANCE.register(EntityTypes.BEAN_ENTITY_TYPE, BeanEntityRender::new);
        // EntityRendererRegistry.INSTANCE.register(EntityTypes.HOLY_PEAR_ARROW_ENTITY_TYPE, HolyPearArrowEntityRender::new);
        EntityRendererRegistry.INSTANCE
                .register(EntityTypes.ROAMING_SPIRIT_ENTITY_TYPE, (manager, context) -> new RoamingSpiritEntityRender(manager));

        HandledScreens.registerAll();
        KeyBindings.load();
        Linkage.initClient();
    }
}
