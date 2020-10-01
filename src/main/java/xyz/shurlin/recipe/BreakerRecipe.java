package xyz.shurlin.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import xyz.shurlin.util.ShurlinLevel;
import xyz.shurlin.util.Utils;

public class BreakerRecipe extends AbstractWorkerRecipe {

    BreakerRecipe(Identifier id, String group, Ingredient input, ItemStack output, int workTime, ShurlinLevel shurlinLevel) {
        super(RecipeTypes.BREAKING, id, group, input, output, workTime, shurlinLevel);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.BREAKING;
    }
}
