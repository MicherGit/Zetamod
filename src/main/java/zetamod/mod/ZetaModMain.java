package zetamod.mod;

import zetamod.mod.features.biomes.BiomesInitializer;
import zetamod.mod.features.errors.compute.ComputeErrorFunction;
import zetamod.mod.features.items.ConcernedTater;
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
import zetamod.mod.features.register.RegisterItems;

public class ZetaModMain implements ModInitializer {

	private static Logger LOGGER = LogManager.getLogger();
	private static String APIVersionGet(String string) {
		if(string.contains("1.0.0")) {
			return "0.0.2_01";
		} else if (string.contains("0.50.0")) {
			return "0.0.0";
		} else if (string.contains("0.50.1")) {
			return "0.0.1";
		} else {
			return (String)null + ". Current mod version doesn't support api";
		}

	}
	public static final String MOD_ID = "zetamod";
	public static final String MOD_NAME = "ZetaMod";
	public static final Integer MOD_MAJOR = 1;
	public static final Integer MOD_MINOR = 1;
	public static final Integer MOD_BF = 0;
	public static boolean MOD_DEV = false;
	public static int MOD_DEV_V =

			0
			;
	public static final byte V_TYPE = 0;
	public static final String MOD_VERSION = MOD_MAJOR + "." + MOD_MINOR + "." + MOD_BF;
	public static final Error error = ComputeErrorFunction.computeHandler();
	public static final Block VOID = new Block(FabricBlockSettings.of(Material.METAL).strength(0.0f));

	public static final Block CONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final Block HYPERCONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final ConcernedTater CONCERNED_TATER = new ConcernedTater(new FabricItemSettings().group(ItemGroup.MISC));
	//public static final Item EXAMPLE_ITEM = Registry.register(Registry.ITEM,new Identifier("mymodid","example_item"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));

	public static final Level LV = Level.INFO;

	public static double concerning_weight = Math.pow(2, -4);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		log(Level.INFO, "Initializing");
		log(Level.INFO,"Credit to SuperCoder79 for letting me use the worldborder expansion code");
		if(MOD_DEV) {
			log(Level.INFO, "Mod version is " + MOD_VERSION + " development version " + MOD_DEV_V);
			log(LV, "Milestone build" + " " +
					7
			);

		}log(LV,"API version " + APIVersionGet(MOD_VERSION))
		;
		log(Level.INFO, "Loading!");
		log(Level.INFO, "Adding biomes");
		OverworldBiomes.addContinentalBiome(BiomeKeys.LUSH_CAVES, OverworldClimate.TEMPERATE, 2D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.DRIPSTONE_CAVES, OverworldClimate.COOL, 2D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.NETHER_WASTES, OverworldClimate.DRY, 0.25D);

		log(Level.INFO, "Adding concerning items!");

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "void"), VOID);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "void"), new BlockItem(VOID, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "concern_block"), CONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "concern_block"), new BlockItem(CONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "hyperconcern_block"), HYPERCONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hyperconcern_block"), new BlockItem(HYPERCONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"concerned_tater"), CONCERNED_TATER);
		//ZetaModMain.log(Level.INFO, "Patching Farlands!");

		System.out.println("Adding a very concern easter egg");
		OverworldBiomes.addContinentalBiome(BiomeKeys.THE_VOID, OverworldClimate.DRY,
				1.5E-1D);


		BiomesInitializer.initializeBiomes();
		log(Level.INFO, "Registering extra items...");
		RegisterItems items = new RegisterItems();
		items.registerItems();
		log(Level.INFO, "DONE!");
	}
	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}
}
