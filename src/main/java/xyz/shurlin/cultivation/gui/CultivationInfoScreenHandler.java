package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.CultivationType;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

public class CultivationInfoScreenHandler extends ScreenHandler {
    // Client side cache for display
    private CultivationType cultivationType;
    private int majorRealmIndex;
    private int minorRealmIndex;
    private double currentProgress;
    private double maxProgress;
    private boolean isBottlenecked;

    // Server side constructor
    public CultivationInfoScreenHandler(int syncId, PlayerEntity player) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
    }

    // Client side constructor
    public CultivationInfoScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
        Identifier typeId = buf.readIdentifier();
        this.cultivationType = CultivationRegistry.INSTANCE.get(typeId);

        this.majorRealmIndex = buf.readInt();
        this.minorRealmIndex = buf.readInt();
        this.currentProgress = buf.readDouble();
        this.isBottlenecked = buf.readBoolean();
        this.maxProgress = buf.readDouble();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        // always be able to open at this point
        return true;
    }

    // Getters for screen
    public String getTypeName() {
        // TODO: Add getName() in CultivationType for name
        return "Shurlin Path";
    }

    public String getRealmName() {
        if (cultivationType == null) return "Unknown";
        CultivationRealm realm = cultivationType.getRealm(majorRealmIndex);
        if (realm == null) return "None";
        return realm.getName().getString();
    }


    public String getStatusText() {
        if (isBottlenecked) return "Bottleneck Reached!";
        return "Stage: " + (minorRealmIndex + 1);
    }

    public float getProgressPercentage() {
        if (maxProgress <= 0) return 0;
        return (float) (currentProgress / maxProgress);
    }
}
