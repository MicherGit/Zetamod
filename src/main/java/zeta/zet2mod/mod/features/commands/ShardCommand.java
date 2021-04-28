package zeta.zet2mod.mod.features.commands;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import zeta.zet2mod.mod.managers.GeneralManager;

public class ShardCommand implements ClientCommandPlugin {
    /**
     * Implementations can use the {@code dispatcher} to register their commands.
     *
     * @param dispatcher a client-side command dispatcher
     */
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal(
                "breaknoiseandterrain"
                ).executes(
                source -> {
                    GeneralManager.getConfig().shardFarLands.setValue(!GeneralManager.getConfig().shardFarLands.getValue());
                    return 1;
                })
        );
    }
}
