package xyz.shurlin.command;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import xyz.shurlin.cultivation.CultivatedPlayerAccessor;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    public static void load() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("shurlin")
                    .then(literal("check_realm"))
                    .executes(c -> {
                        c.getSource().sendFeedback(
                                ((CultivatedPlayerAccessor) c.getSource().getPlayer()).getRealm().getDescribeText(),
                                false);
                        return Command.SINGLE_SUCCESS;
                    }));
        });
    }
}
