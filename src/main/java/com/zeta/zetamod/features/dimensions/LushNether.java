package com.zeta.zetamod.features.dimensions;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class LushNether {
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
            Registry.DIMENSION_OPTIONS,
            new Identifier("zetamod", "lush_nether")
    );

    private static RegistryKey<World> WORLD_KEY = RegistryKey.of(
            Registry.DIMENSION,
            DIMENSION_KEY.getValue()
    );

    private static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
            Registry.DIMENSION_TYPE_KEY,
            new Identifier("zetamod", "lush_nether_type")
    );
    public void onInitialize() {
        Registry.register(Registry.CHUNK_GENERATOR, new Identifier("fabric_dimension", "void"), VoidChunkGenerator.CODEC);

        WORLD_KEY = RegistryKey.of(Registry.DIMENSION, new Identifier("fabric_dimension", "void"));

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            ServerWorld overworld = server.getWorld(World.OVERWORLD);
            ServerWorld world = server.getWorld(WORLD_KEY);

            if (world == null) throw new AssertionError("Test world doesn't exist.");

            Entity entity = COW.create(overworld);

            if (!entity.world.getRegistryKey().equals(World.OVERWORLD)) throw new AssertionError("Entity starting world isn't the overworld");

            TeleportTarget target = new TeleportTarget(Vec3d.ZERO, new Vec3d(1, 1, 1), 45f, 60f);

            Entity teleported = FabricDimensions.teleport(entity, world, target);

            if (teleported == null) throw new AssertionError("Entity didn't teleport");

            if (!teleported.world.getRegistryKey().equals(WORLD_KEY)) throw new AssertionError("Target world not reached.");

            if (!teleported.getPos().equals(target.position)) throw new AssertionError("Target Position not reached.");
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
                dispatcher.register(literal("fabric_dimension_test").executes(FabricDimensionTest.this::swapTargeted))
        );
    }

    private int swapTargeted(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorld serverWorld = player.getServerWorld();
        ServerWorld modWorld = getModWorld(context);

        if (serverWorld != modWorld) {
            TeleportTarget target = new TeleportTarget(new Vec3d(0.5, 101, 0.5), Vec3d.ZERO, 0, 0);
            FabricDimensions.teleport(player, modWorld, target);

            if (player.world != modWorld) {
                throw new CommandException(new LiteralText("Teleportation failed!"));
            }

            modWorld.setBlockState(new BlockPos(0, 100, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
            modWorld.setBlockState(new BlockPos(0, 101, 0), Blocks.TORCH.getDefaultState());
        } else {
            TeleportTarget target = new TeleportTarget(new Vec3d(0, 100, 0), Vec3d.ZERO,
                    (float) Math.random() * 360 - 180, (float) Math.random() * 360 - 180);
            FabricDimensions.teleport(player, getWorld(context, World.OVERWORLD), target);
        }

        return 1;
    }

    private ServerWorld getModWorld(CommandContext<ServerCommandSource> context) {
        return getWorld(context, WORLD_KEY);
    }

    private ServerWorld getWorld(CommandContext<ServerCommandSource> context, RegistryKey<World> dimensionRegistryKey) {
        return context.getSource().getMinecraftServer().getWorld(dimensionRegistryKey);
    }
}
