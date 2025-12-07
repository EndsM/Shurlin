package xyz.shurlin.util;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import xyz.shurlin.block.HolyPearAltarBlock;
import xyz.shurlin.cultivation.gui.CultivationInfoScreenHandler;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.world.TechniqueManager;

import java.util.UUID;

public class ServerReceiver {
    public static void load() {
        ServerSidePacketRegistryImpl.INSTANCE.register(Utils.PACKET_ID_1, (packetContext, packetByteBuf) -> {
            BlockPos pos = packetByteBuf.readBlockPos();
            Block block = packetContext.getPlayer().world.getBlockState(pos).getBlock();
            if (block instanceof HolyPearAltarBlock) {
                ((HolyPearAltarBlock) block).setOwner(packetContext.getPlayer());
            }
        });

        // Open Cultivation Menu
        ServerSidePacketRegistryImpl.INSTANCE.register(Utils.OPEN_CUL, (packetContext, packetByteBuf) -> {
            PlayerEntity player = packetContext.getPlayer();
            StorageAdapter storage = (StorageAdapter) player;

            packetContext.getTaskQueue().execute(() -> {
                ExtendedScreenHandlerFactory factory = new ExtendedScreenHandlerFactory() {
                    @Override
                    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                        CultivatedPlayer cp = storage.GetCultivatedPlayer();
                        buf.writeIdentifier(cp.getCultivationTypeId());
                        buf.writeInt(cp.getMajorRealmIndex());
                        buf.writeInt(cp.getMinorRealmIndex());
                        buf.writeDouble(cp.getCurrentProgress());
                        buf.writeBoolean(cp.isBottlenecked());
                        buf.writeDouble(storage.GetMaxProgress());

                        // Send Active Technique ID
                        UUID activeId = cp.getActiveTechniqueId();
                        buf.writeBoolean(activeId != null);
                        if (activeId != null) buf.writeUuid(activeId);

                        // Send List of Known Techniques (Summary for UI)
                        // We need to look up details in TechniqueManager
                        TechniqueManager techManager = TechniqueManager.getServerInstance((ServerWorld) player.world);
                        NbtList techList = new NbtList();

                        for (UUID id : cp.getLearnedTechniques()) {
                            GeneratedTechnique tech = techManager.getTechnique(id);
                            if (tech != null) {
                                NbtCompound tag = new NbtCompound();
                                tag.putUuid("id", tech.getId());
                                tag.putString("name", tech.getDisplayName().getString()); // Simplified name sending
                                tag.putString("gradeColor", tech.getGrade().getColor().getName());
                                techList.add(tag);
                            }
                        }
                        buf.writeNbt(new NbtCompound() {{
                            put("techs", techList);
                        }});
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

        // Select Active Technique
        ServerSidePacketRegistryImpl.INSTANCE.register(Utils.SELECT_TECHNIQUE, (context, buf) -> {
            UUID techId = buf.readUuid();
            context.getTaskQueue().execute(() -> {
                StorageAdapter storage = (StorageAdapter) context.getPlayer();
                storage.SetActiveTechnique(techId);
            });
        });
    }
}
