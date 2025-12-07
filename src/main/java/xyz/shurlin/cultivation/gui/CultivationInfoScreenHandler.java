package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CultivationInfoScreenHandler extends ScreenHandler {
    private CultivationType cultivationType;
    private int majorRealmIndex;
    private int minorRealmIndex;
    private double currentProgress;
    private double maxProgress;
    private boolean isBottlenecked;

    // Technique UI Data
    private UUID activeTechniqueId;
    private final List<TechniqueSummary> learnedTechniques = new ArrayList<>();

    public CultivationInfoScreenHandler(int syncId, PlayerEntity player) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
    }

    public CultivationInfoScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
        Identifier typeId = buf.readIdentifier();
        this.cultivationType = CultivationRegistry.INSTANCE.get(typeId);

        this.majorRealmIndex = buf.readInt();
        this.minorRealmIndex = buf.readInt();
        this.currentProgress = buf.readDouble();
        this.isBottlenecked = buf.readBoolean();
        this.maxProgress = buf.readDouble();

        // Read Active Tech
        if (buf.readBoolean()) {
            this.activeTechniqueId = buf.readUuid();
        }

        // Read Learned List
        NbtCompound wrapper = buf.readNbt();
        if (wrapper != null && wrapper.contains("techs")) {
            NbtList list = wrapper.getList("techs", 10);
            for (int i = 0; i < list.size(); i++) {
                NbtCompound tag = list.getCompound(i);
                UUID id = tag.getUuid("id");
                String name = tag.getString("name");
                String colorName = tag.getString("gradeColor");
                Formatting color = Formatting.byName(colorName);
                if (color == null) color = Formatting.WHITE;

                learnedTechniques.add(new TechniqueSummary(id, name, color));
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public String getTypeName() {
        return "Shurlin Path";
    }

    public String getRealmName() {
        if (cultivationType == null) return "Unknown";
        CultivationRealm realm = cultivationType.getRealm(majorRealmIndex);
        if (realm == null) return "None";
        return realm.getName().getString();
    }

    public float getProgressPercentage() {
        if (maxProgress <= 0) return 0;
        return (float) (currentProgress / maxProgress);
    }

    public List<TechniqueSummary> getLearnedTechniques() {
        return learnedTechniques;
    }

    public UUID getActiveTechniqueId() {
        return activeTechniqueId;
    }

    // Helper class for UI
    public static class TechniqueSummary {
        public final UUID id;
        public final String name;
        public final Formatting color;

        public TechniqueSummary(UUID id, String name, Formatting color) {
            this.id = id;
            this.name = name;
            this.color = color;
        }
    }
}
