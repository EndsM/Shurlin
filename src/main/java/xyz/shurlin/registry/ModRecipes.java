package xyz.shurlin.registry;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.recipe.*;

public class ModRecipes {
    // Recipe Types
    public static final RecipeType<BreakerRecipe> BREAKING_TYPE = createType("breaking");
    public static final RecipeType<CollectorRecipe> COLLECTING_TYPE = createType("collecting");
    public static final RecipeType<ConcentratorRecipe> CONCENTRATING_TYPE = createType("concentrating");
    public static final RecipeType<ExtractorRecipe> EXTRACTING_TYPE = createType("extracting");
    public static final RecipeType<HMPRecipe> ALCHEMY_TYPE = createType("alchemy");

    // Serializers
    public static final WorkerRecipeSerializer<BreakerRecipe> BREAKING_SERIALIZER = new WorkerRecipeSerializer<>(BreakerRecipe::new);
    public static final WorkerRecipeSerializer<CollectorRecipe> COLLECTING_SERIALIZER = new WorkerRecipeSerializer<>(CollectorRecipe::new);
    public static final ConcentratorRecipe.ConcentratorRecipeSerializer CONCENTRATING_SERIALIZER = new ConcentratorRecipe.ConcentratorRecipeSerializer(ConcentratorRecipe::new);
    public static final WorkerRecipeSerializer<ExtractorRecipe> EXTRACTING_SERIALIZER = new WorkerRecipeSerializer<>(ExtractorRecipe::new);
    public static final WorkerRecipeSerializer<HMPRecipe> ALCHEMY_SERIALIZER = new WorkerRecipeSerializer<>(HMPRecipe::new);


    private static <T extends Recipe<?>> RecipeType<T> createType(String name) {
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        };
    }


    private static void registerType(String id, RecipeType<?> type) {
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Shurlin.MODID, id), type);
    }

    private static void registerSerializer(String id, RecipeSerializer<?> serializer) {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Shurlin.MODID, id), serializer);
    }

    public static void Register() {
        // Register Types
        registerType("breaking", BREAKING_TYPE);
        registerType("collecting", COLLECTING_TYPE);
        registerType("concentrating", CONCENTRATING_TYPE);
        registerType("extracting", EXTRACTING_TYPE);
        registerType("alchemy", ALCHEMY_TYPE);

        // Register Serializers
        registerSerializer("breaking", BREAKING_SERIALIZER);
        registerSerializer("collecting", COLLECTING_SERIALIZER);
        registerSerializer("concentrating", CONCENTRATING_SERIALIZER);
        registerSerializer("extracting", EXTRACTING_SERIALIZER);
        registerSerializer("alchemy", ALCHEMY_SERIALIZER);
    }
}
