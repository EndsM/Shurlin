package xyz.shurlin.cultivation;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.models.CultivationType;

public class CultivationRegistry {
    public static final RegistryKey<Registry<CultivationType>> KEY = RegistryKey.ofRegistry(new Identifier(Shurlin.MODID, "cultivation_types"));

    // Create the registry
    public static final SimpleRegistry<CultivationType> INSTANCE = FabricRegistryBuilder.createSimple(CultivationType.class, new Identifier(Shurlin.MODID, "cultivation_types")).buildAndRegister();

    public static void init() {
    }
}
