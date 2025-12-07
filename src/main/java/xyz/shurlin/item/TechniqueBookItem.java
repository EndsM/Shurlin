package xyz.shurlin.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.impl.TechniqueGenerator;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.world.TechniqueManager;

import java.util.List;
import java.util.UUID;

public class TechniqueBookItem extends Item {
    public TechniqueBookItem(Settings settings) {
        super(settings);
    }

    public static ItemStack createRandomScroll(TechniqueGrade grade, World world) {
        ItemStack stack = new ItemStack(xyz.shurlin.registry.ModItems.TECHNIQUE_BOOK);

        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            GeneratedTechnique tech = TechniqueGenerator.createRandom(grade);

            TechniqueManager.getServerInstance(serverWorld).addTechnique(tech);

            // Write data to item stack
            NbtCompound tag = stack.getOrCreateTag();
            tag.putUuid("TechUUID", tech.getId());

            // Put the buffer for stats, to reduce the server calls
            NbtCompound display = new NbtCompound();
            display.putString("Name", tech.getDisplayName().getString());
            display.putString("GradeColor", tech.getGrade().getColor().getName());
            display.putString("Element", tech.getElementId().getPath());
            display.putDouble("Eff", tech.getEfficiency());
            display.putDouble("Cap", tech.getCapacityModifier());
            tag.put("Display", display);
        }
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound tag = stack.getTag();

        if (!world.isClient && tag != null && tag.contains("TechUUID")) {
            UUID id = tag.getUuid("TechUUID");
            GeneratedTechnique tech = TechniqueManager.getServerInstance((ServerWorld) world).getTechnique(id);

            if (tech != null) {
                StorageAdapter storage = (StorageAdapter) user;
                // storage.learnTechnique(tech);

                user.sendMessage(new LiteralText("§a你成功领悟了: " + tech.getDisplayName().getString()), true);
                stack.decrement(1);
                return TypedActionResult.consume(stack);
            } else {
                user.sendMessage(new LiteralText("§c这卷功法似乎因年代久远而字迹模糊（数据丢失）。"), true);
            }
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound tag = stack.getTag();
        if (tag != null && tag.contains("Display")) {
            NbtCompound info = tag.getCompound("Display");

            Formatting color = Formatting.byName(info.getString("GradeColor"));
            if (color == null) color = Formatting.WHITE;

            tooltip.add(new LiteralText(info.getString("Name")).formatted(color));
            tooltip.add(new LiteralText("属性: " + info.getString("Element")).formatted(Formatting.GRAY));
            tooltip.add(new LiteralText(String.format("修炼速度: %.0f%%", info.getDouble("Eff") * 100)).formatted(Formatting.GREEN));
            tooltip.add(new LiteralText(String.format("真气容量: %.0f%%", info.getDouble("Cap") * 100)).formatted(Formatting.BLUE));
        } else {
            tooltip.add(new LiteralText("§7未鉴定的功法").formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
