package zeta.zetaforged.mod.managers.commands;


import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.SharedConstants;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import zeta.zetaforged.mod.ZetaForged;
import zeta.zetaforged.mod.managers.GeneralManager;
//Static imports
// getString(ctx, "string")
// word()
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
// Import everything
import static zeta.zetaforged.mod.ZetaForged.MOD_DEV;
import static zeta.zetaforged.mod.ZetaForged.MOD_DEV_V;
import static zeta.zetaforged.mod.managers.GeneralManager.getConfig;

import static zeta.zetaforged.api.util.Util.*;
@SuppressWarnings("ALL")
public class CommandsManager {
    public static String version1 =
            "ZetaForged version " + ZetaForged.MOD_VERSION +
                    SPACE + "build" + SPACE + MOD_DEV_V + SPACE + ZetaForged.PHASE +
                    "\n" +
                    //"Technical Blocks version " + TechnicalBlocks.tb_version + "\n" +
                    "Running on Minecraft " +
                    SharedConstants.getGameVersion().getName() +
                    "\nUsing Java version "+System.getProperty("java.version");
    public static String version2 =
            "ZetaForged version " + ZetaForged.MOD_VERSION +
                    SPACE + "build" + SPACE + MOD_DEV_V + SPACE + ZetaForged.PHASE +
                    "\n" +
                    //"Technical Blocks version " + TechnicalBlocks.tb_version + "\n" +
                    "Running on Minecraft " +
                    SharedConstants.getGameVersion().getName() +
                    "\nUsing Java version "+System.getProperty("java.version");

    public static int zfVersion(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(new LiteralText(
                "[ZetaForged] ZetaForged v" + ZetaForged.MOD_VERSION
        ),true);
        context.getSource().sendFeedback(new LiteralText(
                "[Minecraft] Running on version " + SharedConstants.getGameVersion().getName()), true
        );
        context.getSource().sendFeedback(new LiteralText(
                "[Java] Using Java version " + System.getProperty("java.version")), true
        );
        return 1;
    }
    public void initCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            /**
             * /zetaforged
             */
            dispatcher.register(literal("zetaforged").then(literal("version").executes(
                    context -> {
                            return zfVersion(context);
                    }
            ).then(literal("debug").executes(context -> {
                context.getSource().sendFeedback(new LiteralText(version1),true);
                        return 2;
                    })
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
                    .then(literal("toggleOpFireballs").executes(
                            context -> {
                                getConfig().fixFireballs.setValue(!getConfig().fixFireballs.getValue());
                                return 7;
                            }
                    ))
                    .then(
                            literal("doubleCoordinateScale".toLowerCase()).
                                    executes(
                                    context -> {
                                        GeneralManager.getConfig().coordinateScale.setValue(GeneralManager.getConfig().coordinateScale.getValue() * 2);

                                        return 2;
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
