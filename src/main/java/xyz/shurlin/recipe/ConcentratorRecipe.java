package xyz.shurlin.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import xyz.shurlin.util.ShurlinLevel;
import xyz.shurlin.util.Utils;

import java.util.Vector;

public class ConcentratorRecipe extends AbstractWorkerRecipe {
    private final ConcentrationIngredientVector concentrationIngredients;

    public ConcentratorRecipe(Identifier id, String group, ConcentrationIngredientVector concentrationIngredients, ItemStack output, int workTime, ShurlinLevel shurlinLevel) {
        super(RecipeTypes.CONCENTRATING, id, group, null, output, workTime, shurlinLevel);
        this.concentrationIngredients = concentrationIngredients;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        // loop through List<ConcentrationIngredient> concentrationIngredients
        // return false the jump out of function if is not match
        for (int i = 0; i < concentrationIngredients.size(); i++) {
            // Take one of them
            ConcentrationIngredient concentrationIngredient = concentrationIngredients.elementAt(i);
            // Then get the itemStack of this ingredient
            ItemStack stack = inv.getStack(i);
            // See if this Item has the acceptable Tag, and if itemStack in inventory bigger than needed count
            boolean isSuitable = concentrationIngredient.item.equals(stack.getItem()) && stack.getCount() >= concentrationIngredient.count;
            if (!isSuitable){
                return false;
            }
        }
        // After iterating through all the ConcentrationIngredient objects, check if the Inventory object is not an instance of ShurlinLevel
        // or if the Utils.canDo method returns true when called with the Inventory object and the shurlinLevel variable
        // If either of these conditions is met, return true; otherwise, return false
        return !(inv instanceof ShurlinLevel) || Utils.canDo(inv, this.shurlinLevel);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.CONCENTRATING;
    }

    public ConcentrationIngredientVector getConcentrationIngredients() {
        return concentrationIngredients;
    }

    public static class ConcentratorRecipeSerializer implements RecipeSerializer<ConcentratorRecipe> {
        private final ConcentratorRecipeSerializer.RecipeFactory<ConcentratorRecipe> recipeFactory;

        public ConcentratorRecipeSerializer(RecipeFactory<ConcentratorRecipe> recipeFactory) {
            this.recipeFactory = recipeFactory;
        }

        @Override
        public ConcentratorRecipe read(Identifier id, JsonObject jsonObject) {
            String group = JsonHelper.getString(jsonObject, "group", "");
            JsonArray jsonArray = JsonHelper.getArray(jsonObject, "ingredients");
            ConcentrationIngredientVector concentrationIngredients = new ConcentrationIngredientVector();
            for (JsonElement jsonElement : jsonArray) {
                concentrationIngredients.add(new ConcentrationIngredient(jsonElement.getAsJsonObject()));
            }
            String result = JsonHelper.getString(jsonObject, "result");
            Identifier result_id = new Identifier(result);
            int count = JsonHelper.getInt(jsonObject, "count", 1);
            ItemStack output = new ItemStack(Registry.ITEM.getOrEmpty(result_id).orElseThrow(() ->
                    new IllegalStateException("Item: " + result + " does not exist")), count);
            int workingTime = JsonHelper.getInt(jsonObject, "workingTime");
            int shurlinLevel = JsonHelper.getInt(jsonObject, "shurlinLevel", 0);
            return this.recipeFactory.create(id, group, concentrationIngredients, output, workingTime, () -> shurlinLevel);
        }

        @Override
        public ConcentratorRecipe read(Identifier id, PacketByteBuf buf) {
            String group = buf.readString(32767);
            ConcentrationIngredientVector concentrationIngredients = ConcentrationIngredientVector.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            int workingTime = buf.readVarInt();
            float shurlinLevel = buf.readFloat();
            return this.recipeFactory.create(id, group, concentrationIngredients, output, workingTime, () -> shurlinLevel);
        }

        @Override
        public void write(PacketByteBuf buf, ConcentratorRecipe recipe) {
            buf.writeString(recipe.group);
            recipe.concentrationIngredients.write(buf);
            buf.writeItemStack(recipe.output);
            buf.writeVarInt(recipe.workTime);
            buf.writeFloat(recipe.shurlinLevel.getShurlinLevel());
        }

        interface RecipeFactory<ConcentratorRecipe> {
            ConcentratorRecipe create(Identifier id, String group, ConcentrationIngredientVector concentrationIngredientVector, ItemStack output, int cookTime, ShurlinLevel shurlinLevel);
        }

    }

    public static class ConcentrationIngredientVector extends Vector<ConcentrationIngredient> {
        private void write(PacketByteBuf buf) {
            buf.writeVarInt(this.size());
            NbtCompound tags = new NbtCompound();
            int index = 0;
            for (ConcentrationIngredient ingredient : this) {
                NbtCompound tag = new NbtCompound();
                String id = ingredient.item.getTranslationKey();
                tag.putString("id", id);
                tag.putInt("count", ingredient.count);
                tags.put("tag" + ++index, tag);
            }
            buf.writeNbt(tags);
        }

        private static ConcentrationIngredientVector fromPacket(PacketByteBuf buf) {
            int size = buf.readVarInt();
            NbtCompound tags = buf.readNbt();
            ConcentrationIngredientVector concentrationIngredients = new ConcentrationIngredientVector();
            for (int i = 1; i <= size; i++) {
                if (tags != null) {
                    NbtCompound tag = tags.getCompound("tag" + i);
                    String id = tag.getString("id");
                    Item item = Registry.ITEM.getOrEmpty(new Identifier(id)).orElseThrow(() -> new IllegalStateException("Item: " + id + " does not exist"));
                int count = tag.getInt("count");
                ConcentrationIngredient concentrationIngredient = new ConcentrationIngredient(item,count);
                concentrationIngredients.add(concentrationIngredient);
                }
            }
            return concentrationIngredients;
        }
    }

    public static class ConcentrationIngredient {
        Item item;
        int count;

        public int getCount() {
            return count;
        }

        public ConcentrationIngredient(Item item, int count) {
            this.item = item;
            this.count = count;
        }

        public ConcentrationIngredient(JsonObject object) {
            Identifier identifier;
            if (object.has("item")) {
                identifier = new Identifier(JsonHelper.getString(object, "item"));
                Item item = Registry.ITEM.getOrEmpty(identifier).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + identifier + "'"));
                this.item = item;
            } else if (object.has("tag")) {
                identifier = new Identifier(JsonHelper.getString(object, "tag"));
                Tag<Item> tag = ServerTagManagerHolder.getTagManager().getItems().getTag(identifier);
                if (tag == null) {
                    throw new JsonSyntaxException("Unknown item tag '" + identifier + "'");
                }
                this.item = tag.getRandom(null);
            }
            count = JsonHelper.getInt(object, "count", 1);
        }
    }
}
