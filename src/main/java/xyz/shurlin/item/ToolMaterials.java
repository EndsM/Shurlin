package xyz.shurlin.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import xyz.shurlin.registry.ModItems;

import java.util.function.Supplier;

public enum ToolMaterials implements ToolMaterial {
    PLANT_IRON(3, 350, 7.0f, 3.0f, 15, () -> Ingredient.ofItems(ModItems.PLANT_IRON_INGOT)),
    PLANT_GOLD(2, 48, 13.0f, 1.0f, 25, () -> Ingredient.ofItems(ModItems.PLANT_GOLD_INGOT)),
    PLANT_JADE(4, 1800, 10.0F, 4.0F, 13, () -> Ingredient.ofItems(ModItems.PLANT_JADE));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    ToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantibility;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
