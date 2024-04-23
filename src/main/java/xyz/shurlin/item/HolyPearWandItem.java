package xyz.shurlin.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.shurlin.entity.projectile.BeanEntity;
import xyz.shurlin.registry.ModItems;

public class HolyPearWandItem extends BasicItem {
    public HolyPearWandItem() {
        super(new Settings().group(ItemGroups.SHURLIN).maxDamage(2000));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        for (int i = 0; i < 8; i++) {
            BeanEntity beanEntity = new BeanEntity(user, world);
            beanEntity.setProperties(user, user.pitch, user.yaw, 0.5f, 3f, 1.0f);
            world.spawnEntity(beanEntity);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.0f;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem().equals(ModItems.HOLY_PEAR);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
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
