package xyz.shurlin.block.worker.entity;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.PropertyDelegate;
import org.jetbrains.annotations.Nullable;
import xyz.shurlin.block.entity.BasicBlockEntity;
import xyz.shurlin.recipe.AbstractWorkerRecipe;
import xyz.shurlin.util.ShurlinLevel;

public abstract class AbstractWorkerBlockEntity extends BasicBlockEntity implements ShurlinLevel {
    final PropertyDelegate propertyDelegate;
    int workTime;
    int workTimeTotal;
    int level;
    RecipeType<? extends AbstractWorkerRecipe> recipeType;

    AbstractWorkerBlockEntity(BlockEntityType<?> blockEntityType, String containerName, int level, RecipeType<? extends AbstractWorkerRecipe> recipeType) {
        super(blockEntityType, containerName);
        this.level = level;
        this.propertyDelegate = getPropertyDelegate();
        this.recipeType = recipeType;
    }

    @Override
    public float getShurlinLevel() {
        return level * 10.0f;
    }

    protected abstract PropertyDelegate getPropertyDelegate();

    @Override
    public void readNbt(BlockState state, NbtCompound tag) {
        super.readNbt(state, tag);
        this.workTime = tag.getInt("WorkTime");
        this.workTimeTotal = tag.getInt("WorkTimeTotal");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("WorkTime", (short) this.workTime);
        tag.putInt("WorkTimeTotal", (short) this.workTimeTotal);
        return tag;
    }

    void craftRecipe(@Nullable Recipe<?> recipe) {
        if (recipe != null && this.canAcceptRecipeOutput(recipe)) {
            ItemStack itemStack = this.inventory.get(0);
            ItemStack itemStack2 = recipe.getOutput();
            ItemStack itemStack3 = this.inventory.get(getOutputSlot());
            if (itemStack3.isEmpty()) {
                this.inventory.set(getOutputSlot(), itemStack2.copy());
            } else if (itemStack3.getItem() == itemStack2.getItem()) {
                itemStack3.increment(1);
            }

            itemStack.decrement(1);
        }
    }

    boolean isWorking() {
        return this.workTime > 0;
    }

    boolean canAcceptRecipeOutput(@Nullable Recipe<?> recipe) {
        if (!(this.inventory.get(0)).isEmpty() && recipe != null) {
            ItemStack itemStack = recipe.getOutput();
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack2 = this.inventory.get(getOutputSlot());
                if (itemStack2.isEmpty()) {
                    return true;
                } else if (!itemStack2.isItemEqualIgnoreDamage(itemStack)) {
                    return false;
                } else if (itemStack2.getCount() < this.getMaxCountPerStack() && itemStack2.getCount() < itemStack2.getMaxCount()) {
                    return true;
                } else {
                    return itemStack2.getCount() < itemStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

    public int getWorkTimeTotal() {
        assert this.world != null;
        return this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world).map(AbstractWorkerRecipe::getWorkTime).orElse(200);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
        ItemStack itemStack = this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(itemStack) && ItemStack.areNbtEqual(stack, itemStack);
        if (slot == 0 && !bl) {
            this.workTimeTotal = this.getWorkTimeTotal();
            this.workTime = 0;
            this.markDirty();
        }
    }

    protected int getOutputSlot() {
        return 1;
    }

    public int getWorkTime() {
        return workTime;
    }

    public int getLevel() {
        return level;
    }

    public RecipeType<? extends AbstractWorkerRecipe> getRecipeType() {
        return recipeType;
    }
}
