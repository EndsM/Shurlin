package xyz.shurlin.entity.effect;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class LovedStatusEffect extends StatusEffect {
    public LovedStatusEffect() {
        super(StatusEffectType.BENEFICIAL, 13458603);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setHealth(entity.getMaxHealth());

        // This is just copied from original effect
        if (entity.isDead()) {
            entity.world.sendEntityStatus(entity, (byte) 35);
            entity.setPose(EntityPose.STANDING);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
