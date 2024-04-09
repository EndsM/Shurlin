package xyz.shurlin.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import xyz.shurlin.Shurlin;

import java.util.ArrayList;

public class ConnectiveInventory extends SimpleInventory {
    private final PlayerEntity player;
    private static ArrayList<ConnectiveInventory> connectiveInventories;

    protected ConnectiveInventory(int size, PlayerEntity player) {
        super(size);
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void readNbtList(NbtList allTags) {
        for (int i = 0; i < allTags.size(); ++i) {
            NbtList personalTags = allTags.getList(i);
            ConnectiveInventory inventory = connectiveInventories.get(i);
            for (int j = 0; j < personalTags.size(); j++) {
                ItemStack itemStack = ItemStack.fromNbt(personalTags.getCompound(i));
                if (!itemStack.isEmpty()) {
                    inventory.addStack(itemStack);
                }
            }
        }
    }

    public NbtList toNbtList() {
        NbtList allTags = new NbtList();
        for (ConnectiveInventory inventory : connectiveInventories) {
            for (int j = 0; j < inventory.size(); j++) {
                NbtList personalTags = new NbtList();
                ItemStack itemStack = inventory.getStack(j);
                if (!itemStack.isEmpty()) {
                    personalTags.add(itemStack.writeNbt(new NbtCompound()));
                }
                allTags.add(personalTags);
            }

        }
        return allTags;
    }

    public static ConnectiveInventory getConnectiveInventory(int size, PlayerEntity player) {
        if (connectiveInventories != null)
            for (ConnectiveInventory inventory : connectiveInventories) {
                if (inventory.getPlayer() == player)
                    return inventory;
            }
        if (connectiveInventories == null)
            connectiveInventories = new ArrayList<>();
        ConnectiveInventory inventory = new ConnectiveInventory(size, player);
        connectiveInventories.add(inventory);
        Shurlin.LOGGER.debug(connectiveInventories.size());
        return inventory;
    }
}
