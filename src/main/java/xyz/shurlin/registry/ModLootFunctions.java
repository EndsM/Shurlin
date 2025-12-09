package xyz.shurlin.registry;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.loot.SetUnidentifiedTechniqueFunction;

public class ModLootFunctions {
    public static LootFunctionType SET_UNIDENTIFIED_TECHNIQUE;

    public static void Register() {
        // Register the function type
        SET_UNIDENTIFIED_TECHNIQUE = Registry.register(Registry.LOOT_FUNCTION_TYPE,
                new Identifier(Shurlin.MODID, "set_unidentified_technique"),
                new LootFunctionType(new SetUnidentifiedTechniqueFunction.Serializer()));

        // Hook into Loot Tables
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            // Mineshafts, Stronghold Libraries, Dungeons, Bastions
            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id) ||
                    LootTables.SIMPLE_DUNGEON_CHEST.equals(id) ||
                    LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id) ||
                    LootTables.BASTION_TREASURE_CHEST.equals(id)) {

                LootPool pool = FabricLootPoolBuilder.builder()
                        .rolls(UniformLootTableRange.between(0.0f, 1.0f)) // Chance to appear
                        .with(ItemEntry.builder(ModItems.TECHNIQUE_BOOK)
                                .weight(1)
                                .apply(() -> new SetUnidentifiedTechniqueFunction(new LootCondition[0]))
                        ).build();

                supplier.withPool(pool);
            }
        });

    }
}
