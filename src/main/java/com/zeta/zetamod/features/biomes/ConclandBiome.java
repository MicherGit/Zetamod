package com.zeta.zetamod.features.biomes;

import com.zeta.zetamod.features.ZetaModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.apache.logging.log4j.Level;

public class ConclandBiome {
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> CONCERN_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    ZetaModInitializer.HYPERCONCERN_BLOCK.getDefaultState(),
                    ZetaModInitializer.CONCERN_BLOCK.getDefaultState(),
                    Blocks.GRAVEL.getDefaultState())
            );

    private static final Biome CONCLAND = createCONCLAND();

    private static Biome createCONCLAND() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(CONCERN_SURFACE_BUILDER);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.NONE)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x77adff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static final String MOD_ID = ZetaModInitializer.MOD_ID;
    public static final RegistryKey<Biome> CONCLAND_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "CONCLAND"));

    public static void register() {
        ZetaModInitializer.log(Level.INFO, "Adding biomes");
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MOD_ID, "concern"), CONCERN_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, CONCLAND_KEY.getValue(), CONCLAND);
        boolean addToWorldgenBoolean;
        addToWorldgenBoolean = true;
        if (addToWorldgenBoolean) {
            addToWorldgen();
        }
        ZetaModInitializer.log(Level.INFO,
                "Initialized CONCLAND biome. Thanks to https://misode.github.io/ for the custom dimension creator I used to make the Lush Nether."
        );
    }
    public static void addToWorldgen() {
        OverworldBiomes.addContinentalBiome(CONCLAND_KEY, OverworldClimate.TEMPERATE, 2D);
        OverworldBiomes.addContinentalBiome(CONCLAND_KEY, OverworldClimate.COOL, 2D);
    }
}
