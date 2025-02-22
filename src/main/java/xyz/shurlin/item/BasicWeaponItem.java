package xyz.shurlin.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.shurlin.client.options.KeyBindings;
import xyz.shurlin.cultivation.models.enums.SpiritualEquipmentLevels;

import java.util.List;

public class BasicWeaponItem extends Item {
    protected SpiritualEquipmentLevels level;
    boolean withSpirit = false;

    public BasicWeaponItem(Settings settings, SpiritualEquipmentLevels level) {
        super(settings.group(ItemGroups.SHURLIN));
        this.level = level;
    }

    public SpiritualEquipmentLevels getLevel() {
        return level;
    }

    public boolean isWithSpirit() {
        return withSpirit;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.shurlin.weapon.level"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected)
            if (KeyBindings.infuse_spirit.wasPressed()) {
                this.withSpirit = !this.withSpirit;
                if (entity instanceof PlayerEntity)
                    ((PlayerEntity) entity).swingHand(Hand.MAIN_HAND);
            }
    }
}
