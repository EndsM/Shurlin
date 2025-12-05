package xyz.shurlin.util;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import xyz.shurlin.block.HolyPearAltarBlock;
import xyz.shurlin.cultivation.gui.CultivationInfoScreenHandler;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;

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
            StorageAdapter storage = (StorageAdapter) player;

            packetContext.getTaskQueue().execute(() -> {
                ExtendedScreenHandlerFactory factory = new ExtendedScreenHandlerFactory() {
                    @Override
                    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                        // Write data to packet then send to client side
                        CultivatedPlayer cp = storage.GetCultivatedPlayer();
                        buf.writeIdentifier(cp.getCultivationTypeId());
                        buf.writeInt(cp.getMajorRealmIndex());
                        buf.writeInt(cp.getMinorRealmIndex());
                        buf.writeDouble(cp.getCurrentProgress());
                        buf.writeBoolean(cp.isBottlenecked());
                        // Also write the progress so client side can calculate the progress bar
                        buf.writeDouble(storage.GetMaxProgress());
                    }

                    @Override
                    public Text getDisplayName() {
                        return new LiteralText("Cultivation Info");
                    }

                    @Override
                    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                        return new CultivationInfoScreenHandler(syncId, player);
                    }
                };
                player.openHandledScreen(factory);
            });
        });
    }
}
