package xyz.shurlin.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;

import java.util.List;

public class SpiritFoodItem extends Item {
    private final double spiritValue;

    public SpiritFoodItem(Settings settings, double spiritValue) {
        super(settings);
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("tooltip.shurlin.spirit_energy", this.spiritValue).formatted(Formatting.GREEN));
    }
}
