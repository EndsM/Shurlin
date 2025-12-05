package xyz.shurlin.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import xyz.shurlin.Shurlin;
import xyz.shurlin.block.entity.CultivationCrystalBlockEntity;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivatedPlayer;

public class CultivationCrystalBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 4, 4, 12, 12, 12);

    public CultivationCrystalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CultivationCrystalBlockEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            StorageAdapter storage = (StorageAdapter) player;
            CultivatedPlayer cp = storage.GetCultivatedPlayer();

            if (cp.getCultivationTypeId().toString().equals("minecraft:empty")) {
                storage.SetCultivationType(new Identifier(Shurlin.MODID, "shurlin_path"));

                player.sendMessage(new LiteralText("§b[启灵石] §f灵光入体，你已踏入修仙之途！"), false);

                world.removeBlock(pos, false);

                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(new LiteralText("§7[启灵石] 你已身具灵根，此石对你已无用处。"), true);
                return ActionResult.FAIL;
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
}
