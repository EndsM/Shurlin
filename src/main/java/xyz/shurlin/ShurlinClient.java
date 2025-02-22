package xyz.shurlin;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import xyz.shurlin.client.options.KeyBindings;
import xyz.shurlin.client.render.entity.RoamingSpiritEntityRender;
import xyz.shurlin.entity.EntityTypes;
import xyz.shurlin.registry.ModBlocks;
import xyz.shurlin.registry.gui.client.ModScreens;

@Environment(EnvType.CLIENT)
public class ShurlinClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.SMALL_BUD,
                ModBlocks.PEAR_SAPLING,
                ModBlocks.PHOENIX_SAPLING,
                ModBlocks.PLATYCODON_GRANDIFLORUS,
                ModBlocks.PEAR_DOOR,
                ModBlocks.LEAVE_CORAL,
                ModBlocks.DEAD_LEAVE_CORAL,
                ModBlocks.LEAVE_CORAL_FAN,
                ModBlocks.DEAD_LEAVE_CORAL_FAN,
                ModBlocks.LEAVE_CORAL_WALL_FAN,
                ModBlocks.DEAD_LEAVE_CORAL_WALL_FAN);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.HOLY_FARMER_PORTAL,
                ModBlocks.CULTIVATION_CRYSTAL);

        EntityRendererRegistry.INSTANCE
                .register(EntityTypes.ROAMING_SPIRIT_ENTITY_TYPE, (manager, context) -> new RoamingSpiritEntityRender(manager));

        ModScreens.Register();
        KeyBindings.init();
    }
}
