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

public class StarryAltarFeature extends Feature<DefaultFeatureConfig> {
    public StarryAltarFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        // Randomize position within the chunk
        BlockPos centerPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos.add(random.nextInt(16), 0, random.nextInt(16)));

        BlockState stoneState = ModBlocks.MYSTERIOUS_STONE.getDefaultState();
        BlockState altarState = ModBlocks.STARRY_ALTAR.getDefaultState();

        // Check if the spot is valid
        if (world.isAir(centerPos.down())) {
            return false;
        }

        // Generate a 3x3 Base of Mysterious Stone
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos basePos = centerPos.add(x, -1, z);

                world.setBlockState(centerPos.add(x, 0, z), stoneState, 3);

                if (x != 0 || z != 0) {
                    world.setBlockState(centerPos.add(x, 1, z), net.minecraft.block.Blocks.AIR.getDefaultState(), 3);
                }
            }
        }

        // Place the Starry Altar in the center on top of the base
        world.setBlockState(centerPos.up(), altarState, 3);

        return true;
    }
}
