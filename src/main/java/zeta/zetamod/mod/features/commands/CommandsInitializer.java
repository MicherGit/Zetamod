package zeta.zetamod.mod.features.commands;


import com.mojang.brigadier.arguments.StringArgumentType;
import dray.draydenspace.farlandsexplore.technicalblocks.TechnicalBlocks;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.SharedConstants;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.managers.ConfigManager;
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
import static zeta.zetamod.mod.ZetaMod.MOD_DEV;
import static zeta.zetamod.mod.ZetaMod.MOD_DEV_V;
import static zeta.zetamod.mod.managers.GeneralManager.getConfig;

import static zeta.zetamod.api.util.Util.*;
@SuppressWarnings("ALL")
public class CommandsInitializer {
    public static String version1 =
            "ZetaMod version " + ZetaMod.MOD_VERSION +
                    SPACE + "build" + SPACE + MOD_DEV_V + SPACE + ZetaMod.PHASE +
                    "\n" +
                    "Technical Blocks version " + TechnicalBlocks.tb_version + "\n" +
                    "Running on Minecraft " +
                    SharedConstants.getGameVersion().getName() +
                    "\nUsing Java version "+System.getProperty("java.version");
    public static String version2 =
            "ZetaMod version " + ZetaMod.MOD_VERSION +
                    SPACE + "build" + SPACE + MOD_DEV_V + SPACE + ZetaMod.PHASE +
                    "\n" +
                    "Technical Blocks version " + TechnicalBlocks.tb_version + "\n" +
                    "Running on Minecraft " +
                    SharedConstants.getGameVersion().getName() +
                    "\nUsing Java version "+System.getProperty("java.version");
    public void initCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            /**
             * /zetamod
             */
            dispatcher.register(literal("zetamod").then(literal("version").executes(
                    context -> {
                        if(!MOD_DEV) {
                            context.getSource().sendFeedback(new LiteralText(
                                            version1)
                                    ,true);
                        } else {
                            context.getSource().sendFeedback(new LiteralText(
                                            version2)
                                    ,true);
                        }

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
