package xyz.shurlin.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import xyz.shurlin.registry.ModBlocks;

import java.util.Random;

public class MysteriousStonePillarFeature extends Feature<DefaultFeatureConfig> {
    public MysteriousStonePillarFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(StructureWorldAccess serverWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        pos = pos.north(random.nextInt(16)).east(random.nextInt(16));
        pos = serverWorldAccess.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);


        BlockState stoneState = ModBlocks.MYSTERIOUS_STONE.getDefaultState();
        BlockState crystalState = ModBlocks.CULTIVATION_CRYSTAL.getDefaultState();

        serverWorldAccess.setBlockState(pos.north(), stoneState, 3);
        serverWorldAccess.setBlockState(pos.south(), stoneState, 3);
        serverWorldAccess.setBlockState(pos.west(), stoneState, 3);
        serverWorldAccess.setBlockState(pos.east(), stoneState, 3);

        // Add a variation space
        int height = 6 + random.nextInt(4);

        for (int i = 0; i < height; i++) {
            serverWorldAccess.setBlockState(pos.up(i), stoneState, 3);
        }
        // The place the crystal at the top
        serverWorldAccess.setBlockState(pos.up(height), crystalState, 3);

        return true;
    }
}
