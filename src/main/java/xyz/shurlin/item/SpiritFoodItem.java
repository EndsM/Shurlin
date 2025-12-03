package xyz.shurlin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;

public class SpiritFoodItem extends Item {
    private final double spiritValue;

    public SpiritFoodItem(double spiritValue) {
        super(new Item.Settings().group(ItemGroups.SHURLIN)
                .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).alwaysEdible().build()));
        this.spiritValue = spiritValue;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        // Server-side logic
        if (!world.isClient && user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;

            if (player instanceof StorageAdapter) {
                StorageAdapter cultivation = (StorageAdapter) player;

                // Add the progress
                cultivation.addCultivationProgress(this.spiritValue);

                // Basic Feedback
                player.sendMessage(new LiteralText("§aYou absorbed " + this.spiritValue + " spirit energy."), true);
            }
        }
        return result;
    }
}
