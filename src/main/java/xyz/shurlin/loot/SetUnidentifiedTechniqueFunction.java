package xyz.shurlin.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.item.TechniqueBookItem;
import xyz.shurlin.registry.ModLootFunctions;

public class SetUnidentifiedTechniqueFunction extends ConditionalLootFunction {

    public SetUnidentifiedTechniqueFunction(LootCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        // Higher weight for lower grades
        float roll = Shurlin.random.nextFloat();
        TechniqueGrade grade;

        if (roll < 0.60) grade = TechniqueGrade.YELLOW;
        else if (roll < 0.90) grade = TechniqueGrade.BLACK;
        else if (roll < 0.98) grade = TechniqueGrade.EARTH;
        else grade = TechniqueGrade.HEAVEN;

        NbtCompound tag = stack.getOrCreateTag();
        tag.putString(TechniqueBookItem.KEY_UNIDENTIFIED_GRADE, grade.name());

        return stack;
    }

    @Override
    public LootFunctionType getType() {
        return ModLootFunctions.SET_UNIDENTIFIED_TECHNIQUE;
    }

    public static class Serializer extends ConditionalLootFunction.Serializer<SetUnidentifiedTechniqueFunction> {
        @Override
        public void toJson(JsonObject json, SetUnidentifiedTechniqueFunction object, JsonSerializationContext context) {
            super.toJson(json, object, context);
        }

        @Override
        public SetUnidentifiedTechniqueFunction fromJson(JsonObject json, JsonDeserializationContext context, LootCondition[] conditions) {
            return new SetUnidentifiedTechniqueFunction(conditions);
        }
    }
}
