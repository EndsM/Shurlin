package xyz.shurlin.item.spiritual;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.shurlin.cultivation.models.SpiritElement;
import xyz.shurlin.item.BasicItem;

public class SpiritItem extends BasicItem {
    private final SpiritElement spiritElement;
    private final double spiritConstant;

    public SpiritItem(SpiritElement spiritElement, double spiritConstant) {
        this.spiritElement = spiritElement;
        this.spiritConstant = spiritConstant;
    }

    public SpiritElement getSpiritElement() {
        return spiritElement;
    }

    public double getSpiritConstant() {
        return spiritConstant;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }
}
