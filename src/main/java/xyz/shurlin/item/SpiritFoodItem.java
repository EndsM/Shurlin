package xyz.shurlin.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class SpiritFoodItem extends Item {
    private final double spiritValue;

    public SpiritFoodItem(double spiritValue) {
        super(new Item.Settings().group(ItemGroups.SHURLIN)
                .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).alwaysEdible().build()));
        this.spiritValue = spiritValue;
    }
}
