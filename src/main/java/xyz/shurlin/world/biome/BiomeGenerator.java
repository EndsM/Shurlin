package xyz.shurlin.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import static xyz.shurlin.structure.StructureKeys.*;

// Clean it a bit, Later will do something to it.
public class BiomeGenerator {
    public static void load() {
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.FOREST), ANCIENT_OAK_TREE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), ANCIENT_BIRCH_TREE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST), ANCIENT_DARK_OAK_TREE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA), ANCIENT_ACACIA_TREE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_MOUNTAINS), ANCIENT_SPRUCE_TREE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), ANCIENT_JUNGLE_TREE);
    }

    private static void handleBiome(Biome biome) {

    }
}
