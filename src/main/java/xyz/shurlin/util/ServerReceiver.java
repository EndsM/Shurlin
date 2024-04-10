package xyz.shurlin.util;

import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.shurlin.block.HolyPearAltarBlock;
import xyz.shurlin.cultivation.CultivatedPlayerAccessor;
import xyz.shurlin.cultivation.screen.CultivationUI;

public class ServerReceiver {
    public static void load() {
        ServerSidePacketRegistryImpl.INSTANCE.register(Utils.PACKET_ID_1, (packetContext, packetByteBuf) -> {
            BlockPos pos = packetByteBuf.readBlockPos();
            Block block = packetContext.getPlayer().world.getBlockState(pos).getBlock();
            if (block instanceof HolyPearAltarBlock) {
                ((HolyPearAltarBlock) block).setOwner(packetContext.getPlayer());
            }
        });
        ServerSidePacketRegistryImpl.INSTANCE.register(Utils.OPEN_CUL, (packetContext, packetByteBuf) -> {
            PlayerEntity player = packetContext.getPlayer();

            if (player instanceof PlayerEntity) {
                CultivatedPlayerAccessor accessor = (CultivatedPlayerAccessor) player;

                // Will use player as parameter to open screen later
                player.openHandledScreen(new CultivationUI(accessor));
            }
        });
    }
}
