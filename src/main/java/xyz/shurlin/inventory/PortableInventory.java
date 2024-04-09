package xyz.shurlin.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class PortableInventory extends SimpleInventory {
    private final ItemStack itemStack;

    public PortableInventory(int size, ItemStack itemStack) {
        super(size);
        this.itemStack = itemStack;
    }

    public void readNbtList(NbtList tags) {
        int j;
        for (j = 0; j < this.size(); ++j) {
            this.setStack(j, ItemStack.EMPTY);
        }
        for (j = 0; j < tags.size(); ++j) {
            NbtCompound compoundTag = tags.getCompound(j);
            int k = compoundTag.getByte("Slot") & 255;
            if (k < this.size()) {
                this.setStack(k, ItemStack.fromNbt(compoundTag));
            }
        }

    }

    public NbtList toNbtList() {
        NbtList listTag = new NbtList();
        for (int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (!itemStack.isEmpty()) {
                NbtCompound compoundTag = new NbtCompound();
                compoundTag.putByte("Slot", (byte) i);
                itemStack.writeNbt(compoundTag);
                listTag.add(compoundTag);
            }
        }
        return listTag;
    }

    @Override
    public void onClose(PlayerEntity player) {
        NbtCompound compoundTag = new NbtCompound();
        compoundTag.put("inventory", this.toNbtList());
        itemStack.setTag(compoundTag);
    }
}
