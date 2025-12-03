package xyz.shurlin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpiritFoodItem extends Item {
    private final double spiritValue;

    public SpiritFoodItem(double spiritValue) {
        super(new Item.Settings().group(ItemGroups.SHURLIN)
                .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).alwaysEdible().build()));
        this.spiritValue = spiritValue;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return super.finishUsing(stack, world, user);
    }
}
