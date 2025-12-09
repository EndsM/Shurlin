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
    // Root key to prevent conflicts with other mods
    public static final String ROOT_KEY = "ShurlinData";

    // Sub-keys used inside the ShurlinData compound
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
        // Use getSubTag to read without creating if it doesn't exist
        NbtCompound data = stack.getSubTag(ROOT_KEY);

        if (data == null) return BookState.EMPTY;
        if (data.contains(KEY_TECH_UUID)) return BookState.IDENTIFIED;
        if (data.contains(KEY_UNIDENTIFIED_GRADE)) return BookState.UNIDENTIFIED;
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
        NbtCompound data = stack.getSubTag(ROOT_KEY);
        if (data == null) return;

        UUID id = data.getUuid(KEY_TECH_UUID);
        GeneratedTechnique tech = TechniqueManager.getServerInstance((ServerWorld) world).getTechnique(id);

        if (tech != null) {
            StorageAdapter storage = (StorageAdapter) user;
            if (!storage.GetCultivatedPlayer().hasLearned(tech.getId())) {
                storage.LearnTechnique(tech.getId());
                user.sendMessage(new TranslatableText("message.shurlin.book.learned", tech.getDisplayName()), true);
                stack.decrement(1);
            } else {
                user.sendMessage(new TranslatableText("message.shurlin.book.already_known").formatted(Formatting.RED), true);
            }
        } else {
            user.sendMessage(new TranslatableText("message.shurlin.book.lost_data").formatted(Formatting.RED), true);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        BookState state = getState(stack);
        // Safe to get sub tag or null
        NbtCompound data = stack.getSubTag(ROOT_KEY);

        switch (state) {
            case IDENTIFIED:
                if (data != null && data.contains(KEY_DISPLAY)) {
                    NbtCompound info = data.getCompound(KEY_DISPLAY);
                    Formatting color = Formatting.byName(info.getString("GradeColor"));
                    if (color == null) color = Formatting.WHITE;

                    tooltip.add(new LiteralText(info.getString("Name")).formatted(color));

                    String elementKey = "spirit_element.shurlin." + info.getString("Element");
                    tooltip.add(new TranslatableText("tooltip.shurlin.book.element", new TranslatableText(elementKey)).formatted(Formatting.GRAY));

                    String effStr = String.format("%.0f", info.getDouble("Eff") * 100);
                    tooltip.add(new TranslatableText("tooltip.shurlin.book.efficiency", effStr).formatted(Formatting.GREEN));

                    String capStr = String.format("%.0f", info.getDouble("Cap") * 100);
                    tooltip.add(new TranslatableText("tooltip.shurlin.book.capacity", capStr).formatted(Formatting.BLUE));
                }
                break;
            case UNIDENTIFIED:
                if (data != null) {
                    String gradeName = data.getString(KEY_UNIDENTIFIED_GRADE);
                    TechniqueGrade grade;
                    try {
                        grade = TechniqueGrade.valueOf(gradeName);
                    } catch (IllegalArgumentException e) {
                        grade = TechniqueGrade.YELLOW;
                    }
                    tooltip.add(new LiteralText("???").formatted(Formatting.OBFUSCATED, grade.getColor()));
                    tooltip.add(new TranslatableText("tooltip.shurlin.book.grade", grade.getNameText()).formatted(Formatting.GRAY));
                    tooltip.add(new TranslatableText("tooltip.shurlin.book.unidentified_hint").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                }
                break;
            case EMPTY:
                tooltip.add(new TranslatableText("tooltip.shurlin.book.empty").formatted(Formatting.GRAY));
                tooltip.add(new TranslatableText("tooltip.shurlin.book.empty_hint").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                break;
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
