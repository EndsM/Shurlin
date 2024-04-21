package xyz.shurlin.block.coral;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.shurlin.item.Items;
import xyz.shurlin.registry.ModItems;

public class LeaveCoralBlockBlock extends CoralBlockBlock {
    private final Block deadCoralBlock;

    public LeaveCoralBlockBlock(Block deadCoralBlock, Settings settings) {
        super(deadCoralBlock, settings);
        this.deadCoralBlock = deadCoralBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem().equals(ModItems.SHURLIN_INGOT)) {
            world.setBlockState(pos, this.deadCoralBlock.getDefaultState(), 2);
            player.inventory.insertStack(new ItemStack(ModItems.PLANT_ESSENCE_PARTICLE, 1));
        }
        return ActionResult.SUCCESS;
    }
}
