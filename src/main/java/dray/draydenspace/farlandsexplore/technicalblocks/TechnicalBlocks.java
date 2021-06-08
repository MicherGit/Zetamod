package dray.draydenspace.farlandsexplore.technicalblocks;

import java.util.Random;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.GlfwUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import zeta.zetamod.mod.ZetaMod;

public class TechnicalBlocks implements ClientModInitializer {
	public StrongholdFeature.Start strongholdStart;
	public static final String MOD_ID = "technicalblocks";
	public MinecraftClient mc = MinecraftClient.getInstance();
	// public ServerWorld theWorld = (ServerWorld)(mc.world.getChunkManager().getWorld());
	public ClientPlayerEntity thePlayer = mc.player;
	public static final BlockCrop1 WheatCrops1 = new BlockCrop1(FabricBlockSettings.of(Material.PLANT).strength(-1.0f));
	public static final WaterBlockWithLevel1 WaterLevel1 = new WaterBlockWithLevel1(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel2 WaterLevel2 = new WaterBlockWithLevel2(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel3 WaterLevel3 = new WaterBlockWithLevel3(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel4 WaterLevel4 = new WaterBlockWithLevel4(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel5 WaterLevel5 = new WaterBlockWithLevel5(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel6 WaterLevel6 = new WaterBlockWithLevel6(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel7 WaterLevel7 = new WaterBlockWithLevel7(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel8 WaterLevel8 = new WaterBlockWithLevel8(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel9 WaterLevel9 = new WaterBlockWithLevel9(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel10 WaterLevel10 = new WaterBlockWithLevel10(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel11 WaterLevel11 = new WaterBlockWithLevel11(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel12 WaterLevel12 = new WaterBlockWithLevel12(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel13 WaterLevel13 = new WaterBlockWithLevel13(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final WaterBlockWithLevel14 WaterLevel14 = new WaterBlockWithLevel14(FabricBlockSettings.of(Material.WATER).strength(-1.0F));
	public static final HeadlessPistonBlock BlockHeadlessPiston = new HeadlessPistonBlock(FabricBlockSettings.of(Material.PISTON).strength(-1.0F)); 
	// strongholdStart.init(theWorld.getRegistryManager(), theWorld.getChunkManager().getChunkGenerator(), theWorld.getStructureManager(), thePlayer.getChunkPos(), theWorld.getBiome(thePlayer.getBlockPos()), DefaultFeatureConfig.INSTANCE, theWorld);
	// template : public static final Item Item = Registry.register(Registry.ITEM, new Identifier("technicalblocks", "item"), new BlockItem(Blocks.item, new Item.Settings().group(OTHER_GROUP)));
	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "technical_blocks")).icon(() -> new ItemStack(Blocks.BARRIER)).build();
	public static final ItemGroup WALL_BLOCKS = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "wall_banner_blocks")).icon(() -> new ItemStack(Blocks.BARRIER, 2)).build();
	public static final Item ItemAir = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "air"), new BlockItem(Blocks.AIR, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemCaveAir = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cave_air"), new BlockItem(Blocks.CAVE_AIR, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemVoidAir = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "void_air"), new BlockItem(Blocks.VOID_AIR, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemWater1 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water1"), new BlockItem(WaterLevel1, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater2 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water2"), new BlockItem(WaterLevel2, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater3 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water3"), new BlockItem(WaterLevel3, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater4 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water4"), new BlockItem(WaterLevel4, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater5 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water5"), new BlockItem(WaterLevel5, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater6 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water6"), new BlockItem(WaterLevel6, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater7 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water7"), new BlockItem(WaterLevel7, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater8 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water8"), new BlockItem(WaterLevel8, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater9 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water9"), new BlockItem(WaterLevel9, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater10 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water10"), new BlockItem(WaterLevel10, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater11 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water11"), new BlockItem(WaterLevel11, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater12 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water12"), new BlockItem(WaterLevel12, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater13 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water13"), new BlockItem(WaterLevel13, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater14 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water14"), new BlockItem(WaterLevel14, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemWater = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water"), new BlockItem(Blocks.WATER, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemLava = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lava"), new BlockItem(Blocks.LAVA, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemHeadlessPiston = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "headless_piston_item"), new BlockItem(BlockHeadlessPiston, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemPistonExtension = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "piston_extension"), new BlockItem(Blocks.PISTON_HEAD, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemMovingPiston = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "block_36"), new BlockItem(Blocks.MOVING_PISTON, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemFire = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire"), new BlockItem(Blocks.FIRE, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemWheat0 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "crops"), new BlockItem(Blocks.WHEAT, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemWheat1 = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wheat_1_item"), new BlockItem(WheatCrops1, new FabricItemSettings().group(OTHER_GROUP)));
	public static final Item ItemNetherPortal = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "portal"), new BlockItem(Blocks.NETHER_PORTAL, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemEndPortal = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "end_portal"), new BlockItem(Blocks.END_PORTAL, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemBubbleColumn = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bubble_column"), new BlockItem(Blocks.BUBBLE_COLUMN, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemEndGateway = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "end_gateway"), new BlockItem(Blocks.END_GATEWAY, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemFrostedIce = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "frosted_ice"), new BlockItem(Blocks.FROSTED_ICE, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemWaterCauldron = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water_cauldron"), new BlockItem(Blocks.WATER_CAULDRON, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemLavaCauldron = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lava_cauldron"), new BlockItem(Blocks.LAVA_CAULDRON, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemPowderSnowCauldron = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "powder_snow_cauldron"), new BlockItem(Blocks.POWDER_SNOW_CAULDRON, new Item.Settings().group(OTHER_GROUP)));
	public static final Item ItemBlackWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blackwb"), new WallItemCrashRepellent(Blocks.BLACK_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemRedWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "redwb"), new WallItemCrashRepellent(Blocks.RED_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemBlueWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bluewb"), new WallItemCrashRepellent(Blocks.BLUE_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemCyanWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cyanwb"), new WallItemCrashRepellent(Blocks.CYAN_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemYellowWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yellowwb"), new WallItemCrashRepellent(Blocks.YELLOW_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemGrayWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "graywb"), new WallItemCrashRepellent(Blocks.GRAY_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemLightGrayWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lgraywb"), new WallItemCrashRepellent(Blocks.LIGHT_GRAY_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemPinkWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pinkwb"), new WallItemCrashRepellent(Blocks.PINK_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemGreenWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "greenwb"), new WallItemCrashRepellent(Blocks.GREEN_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemLimeWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "limewb"), new WallItemCrashRepellent(Blocks.LIME_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemMagentaWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "magentawb"), new WallItemCrashRepellent(Blocks.MAGENTA_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemOrangeWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orangewb"), new WallItemCrashRepellent(Blocks.ORANGE_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemLightBlueWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lbluewb"), new WallItemCrashRepellent(Blocks.LIGHT_BLUE_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemPurpleWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "purplewb"), new WallItemCrashRepellent(Blocks.PURPLE_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemWhiteWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "whitewb"), new WallItemCrashRepellent(Blocks.WHITE_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemBrownWallBanner = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brownwb"), new WallItemCrashRepellent(Blocks.BROWN_WALL_BANNER, new Item.Settings().group(WALL_BLOCKS)));
	public static final Item ItemDarkOakWallSign = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dowall_sign"), new WallItemCrashRepellent(Blocks.DARK_OAK_WALL_SIGN, new Item.Settings().group(WALL_BLOCKS)));
	public static final SpawnEggItem DragonSpawnEgg = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dragonspawnegg"), new SpawnEggItem(EntityType.ENDER_DRAGON, 1842204, 14711290, new Item.Settings().group(OTHER_GROUP)));
	public static final GeneralizedSpawnEggItem LightningSpawnEgg = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightningspawnegg"), new GeneralizedSpawnEggItem(EntityType.LIGHTNING_BOLT, 16777215, 16777215, new Item.Settings().group(OTHER_GROUP)));;

	public static String tb_version = ZetaMod.technicalblocks_version;

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().log(Level.INFO, "Initializing TechnicalBlocks Blocks version " + tb_version + " by Draydenspace_FS");
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3e76e4, ItemWater);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3e76e4, ItemWaterCauldron);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "wheatstage1"), WheatCrops1);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "headless_piston"), BlockHeadlessPiston);
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(CommandManager.literal("spawnstronghold").executes(context -> {
 
				ServerPlayerEntity player = context.getSource().getPlayer();
				ServerWorld world = (ServerWorld) player.getEntityWorld();
				BlockPos pos = player.getBlockPos();
				ChunkPos chunk = new ChunkPos(pos);
 
				StructureAccessor accessor = world.getStructureAccessor();
				ChunkGenerator generator = world.getChunkManager().getChunkGenerator();
 
				StructureFeature<DefaultFeatureConfig> feat = StructureFeature.STRONGHOLD;
 
				BlockBox box = BlockBox.create(pos.add(-100, -100, -100), pos.add(100, 100, 100));
 
				StructureStart<?> structureStart = feat.getStructureStartFactory().create(feat, chunk, 1, 0);
				structureStart.init(world.getRegistryManager(), generator, world.getStructureManager(), chunk,
						world.getBiome(pos), null, null);
 
				structureStart.generateStructure(world, accessor, generator, new Random(), box, chunk);
 
				return 1;
			}));
		});
	    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
	    	dispatcher.register(CommandManager.literal("crashclient").executes(context -> {
	    	    GlfwUtil.makeJvmCrash();
	            return 1;
	        }));
	    });
	}
}