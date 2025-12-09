package xyz.shurlin;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;
import xyz.shurlin.registry.*;
import xyz.shurlin.registry.features.ModFeatures;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;
import xyz.shurlin.util.ServerReceiver;
import xyz.shurlin.world.biome.BiomeGenerator;
import xyz.shurlin.world.biome.Biomes;
import xyz.shurlin.world.dimension.DimensionTypes;
import xyz.shurlin.world.dimension.Dimensions;
import xyz.shurlin.world.gen.chunk.ChunkGeneratorTypes;

import java.util.Random;

public class Shurlin implements ModInitializer {
    // Consider call it Shurlin Astray if going to continue
    public static final String MODID = "shurlin";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static Random random = new Random();

    @Override
    public void onInitialize() {
        ModFeatures.RegisterAndHandle();

        ModBlocks.Register();
        ModItems.Register();
        ModBlockEntities.Register();
        ModEntityTypes.Register();
        ModRecipes.Register();
        ModStatusEffects.Register();
        ModScreenHandlerTypes.Register();

        // Register the cultivation path
        CultivationRegistry.init();
        ModCultivations.Register();

        // TODO: Move all old "load" method to my preference method
        ServerReceiver.load();
        ChunkGeneratorTypes.load();
        Dimensions.load();
        DimensionTypes.load();
        Biomes.load();
        BiomeGenerator.load();

        // Register Copy Event for Death/Dimension Change
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            StorageAdapter oldStorage = (StorageAdapter) oldPlayer;
            StorageAdapter newStorage = (StorageAdapter) newPlayer;
            CultivatedPlayer oldCP = oldStorage.GetCultivatedPlayer();
            CultivatedPlayer newCP = newStorage.GetCultivatedPlayer();

            // Always copy static data (Roots, Techniques, Type)
            newCP.setCultivationTypeId(oldCP.getCultivationTypeId());
            newCP.getSpiritRoots().putAll(oldCP.getSpiritRoots());
            newCP.getLearnedTechniques().addAll(oldCP.getLearnedTechniques());
            newCP.setActiveTechniqueId(oldCP.getActiveTechniqueId());

            if (alive) {
                // If alive (e.g. return from End), copy everything exactly
                newCP.setMajorRealmIndex(oldCP.getMajorRealmIndex());
                newCP.setMinorRealmIndex(oldCP.getMinorRealmIndex());
                newCP.setCurrentProgress(oldCP.getCurrentProgress());
                newCP.setBottlenecked(oldCP.isBottlenecked());
                newCP.setCurrentQi(oldCP.getCurrentQi());
            } else {
                // If died, only clear progress at current minor realm
                // Keep the Realm
                newCP.setMajorRealmIndex(oldCP.getMajorRealmIndex());
                newCP.setMinorRealmIndex(oldCP.getMinorRealmIndex());

                // Reset progress and bottleneck status
                newCP.setCurrentProgress(0);
                newCP.setBottlenecked(false);
                // Qi is reset to 0 by default on new instance
            }

            // Ensure stats (Health bonus) are applied to the new player entity
            newStorage.refreshStats();
        });

    }
}
