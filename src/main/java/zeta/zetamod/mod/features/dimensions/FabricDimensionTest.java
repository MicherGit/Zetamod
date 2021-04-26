package zeta.zetamod.mod.features.dimensions;

import static net.minecraft.entity.EntityType.COW;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class FabricDimensionTest implements ModInitializer {
	// The dimension options refer to the JSON-file in the dimension subfolder of the datapack,
	// which will always share it's ID with the world that is created from it
	private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
			Registry.DIMENSION_KEY,
			new Identifier("zetamod", "lushnether")
	);

	private static RegistryKey<World> WORLD_KEY = RegistryKey.of(
			Registry.WORLD_KEY,
			DIMENSION_KEY.getValue()
	);

	private static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
			Registry.DIMENSION_TYPE_KEY,
			new Identifier("zetamod", "lushnether_type")
	);

	@Override
	public void onInitialize() {
		Registry.register(Registry.CHUNK_GENERATOR, new Identifier("zetamod", "lushnether"), LushNetherChunkGenerator.CODEC);

		WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier("zetamod", "lushnether"));

		CustomPortalApiRegistry.addPortal(Blocks.GOLD_BLOCK, new Identifier("zetamod", "lush_nether"), 234, 183, 8);
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