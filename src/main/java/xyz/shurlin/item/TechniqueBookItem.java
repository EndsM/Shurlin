package xyz.shurlin.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.GeneratedTechnique;
import xyz.shurlin.cultivation.world.TechniqueManager;

import java.util.List;
import java.util.UUID;

public class TechniqueBookItem extends Item {
    public static final String KEY_TECH_UUID = "TechUUID";
    public static final String KEY_UNIDENTIFIED_GRADE = "UnidentifiedGrade";
    public static final String KEY_DISPLAY = "Display";

    public enum BookState {
        EMPTY,
        UNIDENTIFIED,
        IDENTIFIED
    }

    public TechniqueBookItem(Settings settings) {
        super(settings);
    }

    public static BookState getState(ItemStack stack) {
        NbtCompound tag = stack.getTag();
        if (tag == null) return BookState.EMPTY;
        if (tag.contains(KEY_TECH_UUID)) return BookState.IDENTIFIED;
        if (tag.contains(KEY_UNIDENTIFIED_GRADE)) return BookState.UNIDENTIFIED;
        return BookState.EMPTY;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BookState state = getState(stack);

        if (!world.isClient) {
            switch (state) {
                case IDENTIFIED:
                    attemptLearnTechnique(world, user, stack);
                    return TypedActionResult.consume(stack);
                case UNIDENTIFIED:
                    user.sendMessage(new TranslatableText("message.shurlin.book.unidentified").formatted(Formatting.YELLOW), true);
                    return TypedActionResult.fail(stack);
                case EMPTY:
                    user.sendMessage(new TranslatableText("message.shurlin.book.empty").formatted(Formatting.GRAY), true);
                    return TypedActionResult.fail(stack);
            }
        }
        return TypedActionResult.pass(stack);
    }

    private void attemptLearnTechnique(World world, PlayerEntity user, ItemStack stack) {
        NbtCompound tag = stack.getTag();
        if (tag == null) return;

        UUID id = tag.getUuid(KEY_TECH_UUID);
        GeneratedTechnique tech = TechniqueManager.getServerInstance((ServerWorld) world).getTechnique(id);

        if (tech != null) {
            StorageAdapter storage = (StorageAdapter) user;
            if (!storage.GetCultivatedPlayer().hasLearned(tech.getId())) {
                storage.LearnTechnique(tech.getId());
                user.sendMessage(new LiteralText("§aLearned: " + tech.getDisplayName().getString()), true);
                stack.decrement(1);
            } else {
                user.sendMessage(new LiteralText("§cYou already know this technique."), true);
            }
        } else {
            user.sendMessage(new LiteralText("§cThe technique data is lost in the void."), true);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        BookState state = getState(stack);
        NbtCompound tag = stack.getTag();

        switch (state) {
            case IDENTIFIED:
                if (tag != null && tag.contains(KEY_DISPLAY)) {
                    NbtCompound info = tag.getCompound(KEY_DISPLAY);
                    Formatting color = Formatting.byName(info.getString("GradeColor"));
                    if (color == null) color = Formatting.WHITE;

                    tooltip.add(new LiteralText(info.getString("Name")).formatted(color));
                    tooltip.add(new LiteralText("Element: " + info.getString("Element")).formatted(Formatting.GRAY));
                    tooltip.add(new LiteralText(String.format("Efficiency: %.0f%%", info.getDouble("Eff") * 100)).formatted(Formatting.GREEN));
                    tooltip.add(new LiteralText(String.format("Capacity: %.0f%%", info.getDouble("Cap") * 100)).formatted(Formatting.BLUE));
                }
                break;
            case UNIDENTIFIED:
                String gradeName = tag.getString(KEY_UNIDENTIFIED_GRADE);
                TechniqueGrade grade = TechniqueGrade.valueOf(gradeName);
                tooltip.add(new LiteralText("???").formatted(Formatting.OBFUSCATED, grade.getColor()));
                tooltip.add(new LiteralText("Grade: " + grade.getName()).formatted(Formatting.GRAY));
                tooltip.add(new TranslatableText("tooltip.shurlin.book.unidentified_hint").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                break;
            case EMPTY:
                tooltip.add(new TranslatableText("tooltip.shurlin.book.empty").formatted(Formatting.GRAY));
                tooltip.add(new TranslatableText("tooltip.shurlin.book.empty_hint").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                break;
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
