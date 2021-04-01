package com.zeta.zetamod.features;

import com.zeta.zetamod.features.biomes.ObsilandBiome;
import com.zeta.zetamod.features.biomes.ZetaModBiomes;
import com.zeta.zetamod.features.items.ConcernedTater;
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

public class ZetaModInitializer implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "zetamod";
	public static final String MOD_NAME = "ZetaMod";
	public static final Integer MOD_MAJOR = 0;
	public static final Integer MOD_MINOR = 2;
	public static final Integer MOD_BF = 1;
	public static final boolean MOD_DEV = true;
	public static final Integer MOD_DEV_V = 8;
	public static final int V_TYPE = 1;
	public static final String MOD_VERSION = Integer.toString(MOD_MAJOR) + "." + Integer.toString(MOD_MINOR) + "." + Integer.toString(MOD_BF);
	public static final Error error = new Error("something broke!");
	public static final Block CONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final Block HYPERCONCERN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final ConcernedTater CONCERNED_TATER = new ConcernedTater(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Level LV = Level.INFO;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		log(Level.INFO, "Initializing");
		//log(Level.INFO, "Mod version is " + MOD_VERSION + " development version " + MOD_DEV_V.toString());
		log(LV, "Mod version is " + MOD_VERSION);
		log(LV, "Development release is " + MOD_DEV);
		if(V_TYPE == 1) {
			log(LV, "Development build " + MOD_DEV_V);
		} else if (V_TYPE == 2) {
			log(LV, "pre release " + MOD_DEV_V);
		} else if (V_TYPE == 3) {
			log(LV, "release candidate  " + MOD_DEV_V);
		} else {
			throw error;
		}

		System.out.println("Hello Fabric world!");
		log(Level.INFO, "Adding biomes");
		OverworldBiomes.addContinentalBiome(BiomeKeys.LUSH_CAVES, OverworldClimate.TEMPERATE, 2D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.DRIPSTONE_CAVES, OverworldClimate.COOL, 2D);
		log(Level.INFO, "Adding concerning items!");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "concern_block"), CONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "concern_block"), new BlockItem(CONCERN_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "hyperconcern_block"), HYPERCONCERN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hyperconcern_block"), new BlockItem(HYPERCONCERN_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"concerned_tater"), CONCERNED_TATER);
		ZetaModInitializer.log(Level.INFO, "Patching Farlands!");

		System.out.println("Adding a very concern easter egg");
		OverworldBiomes.addContinentalBiome(BiomeKeys.THE_VOID, OverworldClimate.DRY, 1.5E-3D);


		ZetaModBiomes.initializeBiomes();

		log(Level.INFO, "DONE!");
	}

	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}
}
