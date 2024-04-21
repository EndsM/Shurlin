package xyz.shurlin.item.Spiritual;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.shurlin.cultivation.SpiritPropertyType;
import xyz.shurlin.item.BasicItem;

public class SpiritItem extends BasicItem {
    private final SpiritPropertyType spiritPropertyType;
    private final double spiritConstant;

    public SpiritItem(SpiritPropertyType spiritPropertyType, double spiritConstant) {
        this.spiritPropertyType = spiritPropertyType;
        this.spiritConstant = spiritConstant;
    }

    public SpiritPropertyType getSpiritPropertyType() {
        return spiritPropertyType;
    }

    public double getSpiritConstant() {
        return spiritConstant;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }
}
