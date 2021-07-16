package zeta.zetaforged.mod;

import me.zeroeightsix.fiber.exception.FiberException;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.item.ItemStack;
import zeta.zetaforged.api.API;
import zeta.zetaforged.mod.features.biomes.BiomesInitializer;
import zeta.zetaforged.mod.managers.commands.CommandsManager;
import zeta.zetaforged.mod.features.errors.compute.ComputeErrorFunction;
import zeta.zetaforged.mod.features.items.ConcernedTater;
import net.fabricmc.api.ModInitializer;
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
import zeta.zetaforged.mod.registry.items.RegisterItems;
import zeta.zetaforged.mod.managers.ConfigManager;

public class ZetaForged implements ModInitializer {

    public static final String PHASE = "earlyaccess";
    private static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "zetaforged";
	public static final String MOD_NAME = "ZetaForged";
	public static final Integer MOD_MAJOR = 1;
	public static final Integer MOD_MINOR = 1;
	public static final Integer MOD_BF
			= 0;
	//public static final String MOD_VERSION = MOD_MAJOR + "." + MOD_MINOR + "." + MOD_BF;
	//public static final String MOD_VERSION = "1.0.6";
	public static String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().toString()
			//+ " snapshot 21w28b"
			;
	public static boolean MOD_DEV = true;
	public static final int MOD_DEV_V = 1000;
	public static String getModVersion() {
		return MOD_VERSION;
	}
	public static final byte V_TYPE = 0;
	@Deprecated
	public static String MOD_VERSION_D = MOD_MAJOR + "." + MOD_MINOR + "." + MOD_BF
			//+
			//"_01"
			;
	public static final Error error = ComputeErrorFunction.computeHandler();
	//public static final Block VOID = new Block(FabricBlockSettings.of(Material.METAL).strength(0.0f));

	public static final Block CONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final Block HYPERCONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final ConcernedTater CONCERNED_TATER = new ConcernedTater(new FabricItemSettings().group(ZetaForged.ZETAFORGED_ITEMS));
	//public static final Item EXAMPLE_ITEM = Registry.register(Registry.ITEM,new Identifier("mymodid","example_item"), new Item(new FabricItemSettings().group(zeta.zetaforged.mod.ZetaForged.ZETAFORGED_ITEMS)));

	public static final Level LV = Level.INFO;
	public static double concerning_weight = Math.pow(2, -4);
	public API api = new API(MOD_VERSION_D);

	public static final ItemGroup ZETAFORGED_ITEMS = FabricItemGroupBuilder.create(
			new Identifier(MOD_ID,"zetamod_group")).icon(
			() -> new ItemStack(CONCERN_BLOCK)).
			build();
	public static final Block TATER_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0f));
	public static void loadConfig() {
		try {
			ConfigManager configManager = new ConfigManager();
		} catch (FiberException fiberException) {
			for (int i = 0; i <= 4; i++) {
				try {
					ConfigManager configManager = new ConfigManager();
				} catch (FiberException e) {
					try {
						ConfigManager configManager = new ConfigManager();
					} catch (FiberException crash) {
						log2("Loading failed!");
						crash.printStackTrace();
					}
				}
			}
		}
	}
	public static final Block GIGACONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(8.0f));
	public static final int MILESTONE_VERSION = 14;
	@Override
	public void onInitialize() {
		log2("Initializing ZetaForged");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "gigaconcern_block"), GIGACONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gigaconcern_block"), new BlockItem(GIGACONCERN_BLOCK, new Item.Settings().group(ZetaForged.ZETAFORGED_ITEMS)));

		//TrinaryHash.checkHash();
		log(Level.INFO, "Initializing config");
		log(Level.INFO, "Loading on minecraft version " + SharedConstants.getGameVersion().getName());

		log(Level.INFO, "Mod version " + MOD_VERSION);
		CommandsManager commandsInitializer = new CommandsManager();
		commandsInitializer.initCommands();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		loadConfig();
		/*
		Do not uncomment!
		 */
		//if (!System.getProperty("java.version").contains("16")) {
		//	String error = "Java version too old or too new!";
		//	JavaVersionTooOldException exception = new JavaVersionTooOldException(error);
		//	exception.printStackTrace();
		//	throw new Error(exception);
		//}
		log2("Running on java version " + System.getProperty("java.version"));
		log(Level.INFO,"Credit to SuperCoder79 for letting me use the worldborder expansion code");
		if(MOD_DEV) {
			MOD_VERSION_D = MOD_VERSION_D + "." + MOD_DEV_V;
			log(Level.INFO, "Mod build is " + MOD_VERSION + " development version " + MOD_DEV_V);
			log(LV, "Milestone build" + " " +
					MILESTONE_VERSION
					//+ " beta!"
					+ " early access"
			);

		} else {
			log2("Mod build is " + MOD_VERSION + ' ' + PHASE + " build " + MOD_DEV_V);
			log2("Milestone " + MILESTONE_VERSION +
					//" beta" +
					"!");
		}
		log2("I really need to remember to do this stuff (Adding easter eggs)");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "tater_block"), TATER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tater_block"), new BlockItem(TATER_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		log(LV,"API version " + api.APIVersionGet());
		log(Level.INFO, "Loading!");
		log(Level.INFO, "Adding biomes");
		log(Level.INFO, "Adding concerning items!");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "concern_block"), CONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "concern_block"), new BlockItem(CONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "hyperconcern_block"), HYPERCONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hyperconcern_block"), new BlockItem(HYPERCONCERN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"concerned_tater"), CONCERNED_TATER);
		//ZetaForged.log(Level.INFO, "Patching Farlands!");

		//log2("Adding a very concern easter egg");

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
