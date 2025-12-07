package xyz.shurlin.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import xyz.shurlin.registry.ModRecipes;
import xyz.shurlin.util.ShurlinLevel;

public class CollectorRecipe extends AbstractWorkerRecipe {
    public CollectorRecipe(Identifier id, String group, Ingredient input, ItemStack output, int workTime, ShurlinLevel shurlinLevel) {
        super(ModRecipes.COLLECTING_TYPE, id, group, input, output, workTime, shurlinLevel);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COLLECTING_SERIALIZER;
    }
}
