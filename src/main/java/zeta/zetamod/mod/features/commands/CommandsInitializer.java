package zeta.zetamod.mod.features.commands;


import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.SharedConstants;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import zeta.zetamod.mod.ZetaMod;
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
import static zeta.zetamod.mod.managers.GeneralManager.getConfig;
@SuppressWarnings("ALL")
public class CommandsInitializer {
    public void initCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            /**
             * /
             */
            dispatcher.register(literal("zetamod").then(literal("version").executes(
                    context -> {
                        context.getSource().sendFeedback(new LiteralText("ZetaMod version " + ZetaMod.MOD_VERSION + "\nRunning on Minecraft " + SharedConstants.getGameVersion().getName())
                                ,true);
                        return 1;
                    }
            )).then(literal("farlands").executes(
                    context -> {
                        boolean FLC = GeneralManager.getConfig().farLandsEnabled.getValue();
                        context.getSource().sendFeedback(new LiteralText("FarLands " + getEnabledOrDisabled(!FLC)), true);
                        GeneralManager.getConfig().farLandsEnabled.setValue(!FLC);
                        return 1;
                    }).then(literal("shatter").executes(
                            context -> {
                                boolean SLC = GeneralManager.getConfig().shardFarLands.getValue();
                                if (SLC) {
                                    context.getSource().sendFeedback(new LiteralText("Shattered farlands disabled"), true);
                                } else {
                                    context.getSource().sendFeedback(new LiteralText("Shattered farlands enabled, teleport to x = 784444 or x/z 196111 to see them!"), true);
                                        }
                                GeneralManager.getConfig().shardFarLands.setValue(!SLC);
                                return 2;
                            }))
                    .then(literal("get").executes(
                            context -> {
                                context.getSource().sendFeedback(new LiteralText(
                                        "FarLands currently " + getEnabledOrDisabled(GeneralManager.getConfig().farLandsEnabled.getValue())
                                                + "\nShattered FarLands currently " +getEnabledOrDisabled(GeneralManager.getConfig().shardFarLands.getValue())
                                ), true);
                                return 0;
                            })
                    ))
                    .then(
                            literal("setCoordinateScale".toLowerCase()).
                                    executes(
                                    context -> {
                                        double coorindateScale = GeneralManager.getConfig().coordinateScale.getValue();
                                        context.getSource().sendError(Text.of("Still wip!"));
                                        //getConfig().coordinateScale.setValue(StringArgumentType.getString(context, ""))
                                        //return 1;
                                        return -1;
                                    }
                            )
                    )
            );
        });
    }
    public static String getEnabledOrDisabled(boolean bool) {
        if (bool) {
            return "enabled";
        } else if (!bool) {
            return "disabled";
        } else {
            throw new ArithmeticException("Input not a boolean!");
        }
    }
}
