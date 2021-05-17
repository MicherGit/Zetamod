package zeta.zetamod.mod.features.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import zeta.zetamod.mod.managers.GeneralManager;
//Static imports
// getString(ctx, "string")
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
// word()
import static com.mojang.brigadier.arguments.StringArgumentType.word;
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
import static net.minecraft.server.command.CommandManager.argument;
// Import everything
import static net.minecraft.server.command.CommandManager.*;

public class CommandsInitializer {
    public void initCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("farlands").executes(context -> {
                GeneralManager.getConfig().farLandsEnabled.setValue(!(GeneralManager.getConfig().farLandsEnabled.getValue()));
                return 1;
            }));
            dispatcher.register(literal("break").executes(context -> {
                GeneralManager.getConfig().shardFarLands.setValue(!(GeneralManager.getConfig().shardFarLands.getValue()));
                return 1;
            }));
        });
    }
}
