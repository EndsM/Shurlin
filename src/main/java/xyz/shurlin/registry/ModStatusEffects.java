package xyz.shurlin.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.entity.effect.LovedStatusEffect;

public class ModStatusEffects {
    public static final StatusEffect LOVED = new LovedStatusEffect();

    private static void registerStatusEffect(String id, StatusEffect statusEffect) {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Shurlin.MODID, id), statusEffect);
    }

    public static void Register() {
        registerStatusEffect("loved", LOVED);
    }
}
