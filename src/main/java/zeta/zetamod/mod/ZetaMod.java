package zeta.zetamod.mod;

import dray.draydenspace.farlandsexplore.technicalblocks.TechnicalBlocks;
import me.zeroeightsix.fiber.exception.FiberException;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.item.ItemStack;
import zeta.zetamod.api.API;
import zeta.zetamod.mod.features.biomes.BiomesInitializer;
import zeta.zetamod.mod.features.commands.CommandsInitializer;
import zeta.zetamod.mod.features.errors.compute.ComputeErrorFunction;
import zeta.zetamod.mod.features.errors.compute.JavaVersionTooOldException;
import zeta.zetamod.mod.features.items.ConcernedTater;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import zeta.zetamod.mod.features.items.RegisterItems;
import zeta.zetamod.mod.managers.ConfigManager;

import java.io.IOException;

public class ZetaMod implements ModInitializer {

    public static final String PHASE = "prerelease";
    private static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "zetamod";
	public static final String MOD_NAME = "ZetaMod";
	public static final Integer MOD_MAJOR = 1;
	public static final Integer MOD_MINOR = 0;
	public static final Integer MOD_BF = 0;
	public static final String MOD_VERSION = MOD_MAJOR + "." + MOD_MINOR + "." + MOD_BF;
	public static boolean MOD_DEV = true;
	public static final int MOD_DEV_V =
			6180
			//+ "."
			//+ "2"
			;
	public static final byte V_TYPE = 0;
	public static String MOD_VERSION_D = MOD_MAJOR + "." + MOD_MINOR + "." + MOD_BF
			//+
			//"_01"
			;
	public static String technicalblocks_version = "1.0.0";
	public static final Error error = ComputeErrorFunction.computeHandler();
	//public static final Block VOID = new Block(FabricBlockSettings.of(Material.METAL).strength(0.0f));

	public static final Block CONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final Block HYPERCONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final ConcernedTater CONCERNED_TATER = new ConcernedTater(new FabricItemSettings().group(zeta.zetamod.mod.ZetaMod.ZETAMOD_ITEMS));
	//public static final Item EXAMPLE_ITEM = Registry.register(Registry.ITEM,new Identifier("mymodid","example_item"), new Item(new FabricItemSettings().group(zeta.zetamod.mod.ZetaMod.ZETAMOD_ITEMS)));

	public static final Level LV = Level.INFO;
	public static double concerning_weight = Math.pow(2, -4);
	//public API api = new API(MOD_VERSION_D);

	public static final ItemGroup ZETAMOD_ITEMS = FabricItemGroupBuilder.create(
			new Identifier(MOD_ID,"zetamod_group")).icon(
			() -> new ItemStack(CONCERN_BLOCK)).
			build();

	@Override
	public void onInitialize() {
		log2("Initializing ZetaMod");
		//log2("Loading on ")
		//TrinaryHash.checkHash();
		log(Level.INFO, "Initializing config");
		log(Level.INFO, "Loading on minecraft version " + SharedConstants.getGameVersion().getName());
		try {
			ConfigManager configManager = new ConfigManager();
		} catch (FiberException e) {
			log2("Loading failed!");
			e.printStackTrace();
		}
		log(Level.INFO, "Mod version " + MOD_VERSION_D);
		CommandsInitializer commandsInitializer = new CommandsInitializer();
		commandsInitializer.initCommands();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		if (!System.getProperty("java.version").contains("16")) {
			String error = "Java version too old!";
			JavaVersionTooOldException exception = new JavaVersionTooOldException(error);
			exception.printStackTrace();
			throw new Error(exception);
		}
		if (SharedConstants.getGameVersion().getName().equals("21w20a")) {
			LogManager.getLogger().log(Level.
							//WARN
					INFO
					, "Candle recipes enabled.");
		}
		log2("Running on java version " + System.getProperty("java.version"));
		log(Level.INFO,"Credit to SuperCoder79 for letting me use the worldborder expansion code");
		if(MOD_DEV) {
			MOD_VERSION_D = MOD_VERSION_D + "." + MOD_DEV_V;
			log(Level.INFO, "Mod build is " + MOD_VERSION_D + " development version " + MOD_DEV_V);
			log(LV, "Milestone build" + " " +
					12
					+ " beta!"
			);

		} else {
			log2("Mod build is " + MOD_VERSION_D + ' ' + PHASE + " build " + MOD_DEV_V);
			log2("Milestone 12 beta!");
		}
		//log(LV,"API version " + api.APIVersionGet());
		log(Level.INFO, "Loading!");
		log(Level.INFO, "Adding biomes");
		OverworldBiomes.addContinentalBiome(BiomeKeys.LUSH_CAVES, OverworldClimate.TEMPERATE, 0.25D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.DRIPSTONE_CAVES, OverworldClimate.COOL, 0.25D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.NETHER_WASTES, OverworldClimate.DRY, 0.0625D);

		log(Level.INFO, "Adding concerning items!");

		//Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "void"), VOID);
		//Registry.register(Registry.ITEM, new Identifier(MOD_ID, "void"), new BlockItem(VOID, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "concern_block"), CONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "concern_block"), new BlockItem(CONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "hyperconcern_block"), HYPERCONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hyperconcern_block"), new BlockItem(HYPERCONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"concerned_tater"), CONCERNED_TATER);
		//ZetaMod.log(Level.INFO, "Patching Farlands!");

		//log2("Adding a very concern easter egg");
		OverworldBiomes.addContinentalBiome(BiomeKeys.THE_VOID, OverworldClimate.DRY,
				0.5D);


		BiomesInitializer.initializeBiomes();
		log(Level.INFO, "Registering extra items...");
		RegisterItems items = new RegisterItems();
		items.registerItems();
		//log(Level.INFO, "DONE!");
	}
	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}
	public static void log2(String message) {
		LOGGER.log(Level.INFO, "["+MOD_NAME+"] " + message);
	}
}
