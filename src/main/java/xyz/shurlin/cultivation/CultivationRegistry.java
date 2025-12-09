package xyz.shurlin.cultivation;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.models.CultivationTechnique;
import xyz.shurlin.cultivation.models.CultivationType;
import xyz.shurlin.cultivation.models.SpiritElement;

public class CultivationRegistry {
    // Register the type of cultivation path
    public static final RegistryKey<Registry<CultivationType>> KEY = RegistryKey.ofRegistry(new Identifier(Shurlin.MODID, "cultivation_types"));
    public static final SimpleRegistry<CultivationType> INSTANCE = FabricRegistryBuilder.createSimple(CultivationType.class, new Identifier(Shurlin.MODID, "cultivation_types")).buildAndRegister();

    // Register the Example Cultivation Technique
    // Maybe create a randomizer and technique generator, which makes every save could have a different set of techniques
    public static final SimpleRegistry<CultivationTechnique> TECHNIQUE_REGISTRY = FabricRegistryBuilder.createSimple(CultivationTechnique.class, new Identifier(Shurlin.MODID, "cultivation_techniques")).buildAndRegister();

    // Spirit Elements
    public static final SimpleRegistry<SpiritElement> ELEMENT_REGISTRY = FabricRegistryBuilder.createSimple(SpiritElement.class, new Identifier(Shurlin.MODID, "spirit_elements")).buildAndRegister();

    public static void init() {
    }

    public static CultivationTechnique getTechnique(Identifier id) {
        return TECHNIQUE_REGISTRY.get(id);
    }

    public static void registerTechnique(Identifier id, CultivationTechnique technique) {
        Registry.register(TECHNIQUE_REGISTRY, id, technique);
    }

    // Helper for Elements
    public static SpiritElement getElement(Identifier id) {
        return ELEMENT_REGISTRY.get(id);
    }
}
