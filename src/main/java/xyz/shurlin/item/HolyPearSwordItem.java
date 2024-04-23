package xyz.shurlin.item;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import xyz.shurlin.registry.ModItems;

public class HolyPearSwordItem extends BasicItem {

    public HolyPearSwordItem() {
        super(new Item.Settings().group(ItemGroups.SHURLIN).maxDamage(2000));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, user.getPos());
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).dropSelectedItem(true);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return stack.getItem() == ModItems.HOLY_PEAR;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof PlayerEntity) {
            // This requires place the item in specific slot, which is slot 25
            // Which doesn't make a lot of sense, cause the id of each slot may vary
            if (((PlayerEntity) target).inventory.getStack(25).getItem() == ModItems.LIFE_AMULET)
                if (((PlayerEntity) attacker).inventory.getStack(25).getItem() == ModItems.LIFE_AMULET)
                    return false;
                else attacker.kill();
            else {
                target.kill();
            }
        } else {
            target.kill();
        }
        stack.damage(1, attacker, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 66;
    }
}
