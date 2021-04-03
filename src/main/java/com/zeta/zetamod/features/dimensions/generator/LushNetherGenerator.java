package com.zeta.zetamod.features.dimensions.generator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.zeta.zetamod.features.biomes.BiomesInitializer;
import com.zeta.zetamod.features.dimensions.LushNether;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.data.report.BiomeListProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.surfacebuilder.NetherSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class LushNetherGenerator extends ChunkGenerator{
    // Just an example of adding a custom boolean
    protected final boolean customBool;

    public static final Codec<LushNetherGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source")
                            .forGetter((generator) -> generator.biomeSource),
                    Codec.BOOL.fieldOf("custom_bool")
                            .forGetter((generator) -> generator.customBool)
            )
                    .apply(instance, instance.stable(LushNetherGenerator::new))
    );

    public LushNetherGenerator(BiomeSource biomeSource, boolean customBool) {
        super(biomeSource, new StructuresConfig(false));
        this.customBool = customBool;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this;
    }

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
        //NetherSurfaceBuilder.NETHER.generate(region.getRandom(), chunk, region.getBiome(new BlockPos(0,0,0)), 0, 0, 256, 684.412, Blocks.STONE, Blocks.WATER, 64, region.getSeed(), NetherSurfaceBuilder.NETHER.getCodec());
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, StructureAccessor accessor, Chunk chunk) {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType, HeightLimitView heightLimitView) {
        return 0;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView heightLimitView) {
        return new VerticalBlockSample(0, new BlockState[0]);
    }

}
