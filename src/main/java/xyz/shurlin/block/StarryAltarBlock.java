package xyz.shurlin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.impl.TechniqueGenerator;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.world.TechniqueManager;
import xyz.shurlin.item.TechniqueBookItem;
import xyz.shurlin.registry.ModItems;

public class StarryAltarBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 10, 13);

    public StarryAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack stack = player.getStackInHand(hand);

            // Check for Unidentified Book
            if (stack.getItem() == ModItems.TECHNIQUE_BOOK) {
                if (TechniqueBookItem.getState(stack) == TechniqueBookItem.BookState.UNIDENTIFIED) {
                    identifyBook((ServerWorld) world, stack, player);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    private void identifyBook(ServerWorld world, ItemStack stack, PlayerEntity player) {
        NbtCompound tag = stack.getTag();
        if (tag == null) return;

        // Get Grade
        String gradeName = tag.getString(TechniqueBookItem.KEY_UNIDENTIFIED_GRADE);
        TechniqueGrade grade;
        try {
            grade = TechniqueGrade.valueOf(gradeName);
        } catch (IllegalArgumentException e) {
            grade = TechniqueGrade.YELLOW;
        }

        // Generate actual technique
        GeneratedTechnique tech = TechniqueGenerator.createRandom(grade);
        TechniqueManager.getServerInstance(world).addTechnique(tech);

        // Update Item NBT
        tag.remove(TechniqueBookItem.KEY_UNIDENTIFIED_GRADE);
        tag.putUuid(TechniqueBookItem.KEY_TECH_UUID, tech.getId());

        // Add Cache Display
        NbtCompound display = new NbtCompound();
        display.putString("Name", tech.getDisplayName().getString());
        display.putString("GradeColor", tech.getGrade().getColor().getName());
        display.putString("Element", tech.getElementId().getPath());
        display.putDouble("Eff", tech.getEfficiency());
        display.putDouble("Cap", tech.getCapacityModifier());
        tag.put(TechniqueBookItem.KEY_DISPLAY, display);

        // Feedback
        world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.sendMessage(new LiteralText("§dThe secrets of the book are revealed!"), true);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
