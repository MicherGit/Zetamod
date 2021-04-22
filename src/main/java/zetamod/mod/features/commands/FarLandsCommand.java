package zetamod.mod.features.commands;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import zetamod.mod.managers.GeneralManager;

public class FarLandsCommand implements ClientCommandPlugin {
    /**
     * Implementations can use the {@code dispatcher} to register their commands.
     *
     * @param dispatcher a client-side command dispatcher
     */
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("farlands").executes(
                source -> {
                    GeneralManager.getConfig().farLandsEnabled.setValue(!GeneralManager.getConfig().farLandsEnabled.getValue());
                    return 1;
                })
        );
    }
}
