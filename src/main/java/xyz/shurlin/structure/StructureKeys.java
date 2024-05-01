package xyz.shurlin.structure;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import xyz.shurlin.Shurlin;

public class StructureKeys {
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_OAK_TREE = register("ancient_oak_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_BIRCH_TREE = register("ancient_birch_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_DARK_OAK_TREE = register("ancient_dark_oak_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_ACACIA_TREE = register("ancient_acacia_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_SPRUCE_TREE = register("ancient_spruce_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_JUNGLE_TREE = register("ancient_jungle_tree");
    public static final RegistryKey<ConfiguredStructureFeature<?, ?>> ANCIENT_PEAR_TREE = register("ancient_pear_tree");

    private static RegistryKey<ConfiguredStructureFeature<?, ?>> register(String registryName) {
        return RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier(Shurlin.MODID, registryName));
    }
}
