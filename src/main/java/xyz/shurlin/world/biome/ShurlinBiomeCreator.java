package xyz.shurlin.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import xyz.shurlin.world.gen.feature.ShurlinBiomeFeatures;
import xyz.shurlin.world.gen.feature.ShurlinConfiguredStructureFeatures;
import xyz.shurlin.world.gen.surfacebuilder.ShurlinConfiguredSurfaceBuilder;

public class ShurlinBiomeCreator {

    private static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static Biome createPearForest() {
        SpawnSettings.Builder spawnSettingBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettingBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettingBuilder);
        GenerationSettings.Builder surfaceBuilder = new GenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(surfaceBuilder);
        surfaceBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        surfaceBuilder.structureFeature(ShurlinConfiguredStructureFeatures.ANCIENT_PEAR_TREE);
        DefaultBiomeFeatures.addLandCarvers(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultLakes(surfaceBuilder);
        DefaultBiomeFeatures.addDungeons(surfaceBuilder);
        DefaultBiomeFeatures.addForestFlowers(surfaceBuilder);
        DefaultBiomeFeatures.addMineables(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultOres(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultDisks(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultFlowers(surfaceBuilder);
        DefaultBiomeFeatures.addForestGrass(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(surfaceBuilder);
        surfaceBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_WATER);

        ShurlinBiomeFeatures.addPearTrees(surfaceBuilder);
        ShurlinBiomeFeatures.addSmallBud(surfaceBuilder);
        ShurlinBiomeFeatures.addPlatycodonGrandiflorus(surfaceBuilder);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST).depth(0.1f).scale(0.2f).temperature(0.6F).downfall(0.3F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.6F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettingBuilder.build()).generationSettings(surfaceBuilder.build()).build();
    }

    public static Biome createParasolAncientForest() {
        SpawnSettings.Builder spawnSettingBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettingBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettingBuilder);
        GenerationSettings.Builder surfaceBuilder = new GenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(surfaceBuilder);
        surfaceBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultLakes(surfaceBuilder);
        DefaultBiomeFeatures.addDungeons(surfaceBuilder);
        DefaultBiomeFeatures.addForestFlowers(surfaceBuilder);
        DefaultBiomeFeatures.addMineables(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultOres(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultDisks(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultFlowers(surfaceBuilder);
        DefaultBiomeFeatures.addForestGrass(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(surfaceBuilder);
        DefaultBiomeFeatures.addSprings(surfaceBuilder);

        ShurlinBiomeFeatures.addMysteriousStonePillar(surfaceBuilder);
        ShurlinBiomeFeatures.addPlantObsidianHeap(surfaceBuilder);
        ShurlinBiomeFeatures.addPhoenixTrees(surfaceBuilder);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST).depth(0.1f).scale(0.2f).temperature(0.7F).downfall(0.1F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.6F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettingBuilder.build()).generationSettings(surfaceBuilder.build()).build();
    }

    public static Biome createFireLand() {
        SpawnSettings.Builder spawnSettingBuilder = new SpawnSettings.Builder();
        spawnSettingBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 4, 4));

        GenerationSettings.Builder generationSettingBuilder = new GenerationSettings.Builder()
                .surfaceBuilder(ShurlinConfiguredSurfaceBuilder.FIRE_LAND);
        DefaultBiomeFeatures.addLandCarvers(generationSettingBuilder);
        ShurlinBiomeFeatures.addHotSprings(generationSettingBuilder);


        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS).depth(0.2f).scale(0.2f).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(2.0F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettingBuilder.build()).generationSettings(generationSettingBuilder.build()).build();
    }
}
