package mixins.zeta.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.datafixers.DataFixUtils;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.*;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zeta.zetamod.mod.Concern;
import zeta.zetamod.mod.ZetaModMain;
import zeta.zetamod.mod.managers.ConfigManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(DebugHud.class)
public class Debug {

    @Shadow
    private final MinecraftClient client;
    @Nullable
    private ChunkPos pos;
    @Nullable
    private WorldChunk chunk;

    public Debug(MinecraftClient client, @Nullable WorldChunk chunk) {
        this.client = client;
        this.chunk = chunk;
    }

    @Shadow
    public void resetChunk() {

    }
    @Nullable
    private ServerWorld getServerWorld() {
        IntegratedServer integratedServer = this.client.getServer();
        return integratedServer != null ? integratedServer.getWorld(this.client.world.getRegistryKey()) : null;
    }

    @Nullable
    private String getServerWorldDebugString() {
        ServerWorld serverWorld = this.getServerWorld();
        return serverWorld != null ? serverWorld.asString() : null;
    }
    @Shadow
    private World getWorld() {
        return (World) DataFixUtils.orElse(Optional.ofNullable(this.client.getServer()).flatMap((integratedServer) -> {
            return Optional.ofNullable(integratedServer.getWorld(this.client.world.getRegistryKey()));
        }), this.client.world);
    }
    @Shadow
    @Nullable
    private WorldChunk getChunk() {
        if (this.chunkFuture == null) {
            ServerWorld serverWorld = this.getServerWorld();
            if (serverWorld != null) {
                this.chunkFuture = serverWorld.getChunkManager().getChunkFutureSyncOnMainThread(this.pos.x, this.pos.z, ChunkStatus.FULL, false).thenApply((either) -> {
                    return (WorldChunk)either.map((chunk) -> {
                        return (WorldChunk)chunk;
                    }, (unloaded) -> {
                        return null;
                    });
                });
            }

            if (this.chunkFuture == null) {
                this.chunkFuture = CompletableFuture.completedFuture(this.getClientChunk());
            }
        }

        return (WorldChunk)this.chunkFuture.getNow((WorldChunk) null);
    }
    @Shadow
    @Nullable
    private CompletableFuture<WorldChunk> chunkFuture;

    /**
     * @author
     */
    @Overwrite
    public List<String> getLeftText() {
        IntegratedServer integratedServer = this.client.getServer();
        ClientConnection clientConnection = this.client.getNetworkHandler().getConnection();
        float f = clientConnection.getAveragePacketsSent();
        float g = clientConnection.getAveragePacketsReceived();
        String string2;
        if (integratedServer != null) {
            string2 = String.format("Integrated server @ %.0f ms ticks, %.0f tx, %.0f rx", integratedServer.getTickTime(), f, g);
        } else {
            string2 = String.format("\"%s\" server, %.0f tx, %.0f rx", this.client.player.getServerBrand(), f, g);
        }

        BlockPos blockPos = this.client.getCameraEntity().getBlockPos();
        if (!Concern.stageOne) {
            System.out.println("Injecting shit part 2");} else {
            System.out.println("Injecting shit part 1"); Concern.stageOne = !Concern.stageOne;
        }
        if (this.client.hasReducedDebugInfo()) {
            if (ConfigManager.getConfig().enableOneSeventeenBoolean.getValue()) {
                return Lists.newArrayList(new String[]{"Minecraft + (zetamod easter egg lel gg) " + ZetaModMain.MOD_VERSION + " " + SharedConstants.getGameVersion().getName() + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ")", this.client.fpsDebugString, string2, this.client.worldRenderer.getChunksDebugString(), this.client.worldRenderer.getEntitiesDebugString(), "P: " + this.client.particleManager.getDebugString() + ". T: " + this.client.world.getRegularEntityCount(), this.client.world.asString(), "", String.format("Chunk-relative: %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15)});
            } else {
                return Lists.newArrayList(new String[]{"Minecraft 1.17" + " (release/" + ClientBrandRetriever.getClientModName() + ")", this.client.fpsDebugString, string2, this.client.worldRenderer.getChunksDebugString(), this.client.worldRenderer.getEntitiesDebugString(), "P: " + this.client.particleManager.getDebugString() + ". T: " + this.client.world.getRegularEntityCount(), this.client.world.asString(), "", String.format("Chunk-relative: %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15)});
            }
        } else {
            Entity entity = this.client.getCameraEntity();
            Direction direction = entity.getHorizontalFacing();
            String string7;
            switch(direction) {
                case NORTH:
                    string7 = "Towards negative Z";
                    break;
                case SOUTH:
                    string7 = "Towards positive Z";
                    break;
                case WEST:
                    string7 = "Towards negative X";
                    break;
                case EAST:
                    string7 = "Towards positive X";
                    break;
                default:
                    string7 = "Invalid";
            }

            ChunkPos chunkPos = new ChunkPos(blockPos);
            if (!Objects.equals(this.pos, chunkPos)) {
                this.pos = chunkPos;
                this.resetChunk();
            }
            // OVER HERE!!!
            World world = this.getWorld();
            LongSet longSet = world instanceof ServerWorld ? ((ServerWorld)world).getForcedChunks() : LongSets.EMPTY_SET;
            List<String> list;
            if (!ConfigManager.getConfig().enableOneSeventeenBoolean.getValue()) {
                list = Lists.newArrayList(
                        new String[]{
                                "Minecraft "
                                        +
                                        SharedConstants.getGameVersion().
                                                getName() +
                                        " + ZetaMod Beta " + Concern.concern +
                                        " (" + this.client.getGameVersion() + "/ZetaMod " + ZetaModMain.PHASE
                                        + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/" + this.client.getVersionType()) + ")", this.client.fpsDebugString, string2, this.client.worldRenderer.getChunksDebugString(), this.client.worldRenderer.getEntitiesDebugString(), "P: " + this.client.particleManager.getDebugString() + ". T: " + this.client.world.getRegularEntityCount(), this.client.world.asString()});
            } else {
                list = Lists.newArrayList(new String[]{"Minecraft " + "1.17" + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/" + this.client.getVersionType()) + ")", this.client.fpsDebugString, string2, this.client.worldRenderer.getChunksDebugString(), this.client.worldRenderer.getEntitiesDebugString(), "P: " + this.client.particleManager.getDebugString() + ". T: " + this.client.world.getRegularEntityCount(), this.client.world.asString()});
            }
            String string8 = this.getServerWorldDebugString();
            if (string8 != null) {
                list.add(string8);
            }

            list.add(this.client.world.getRegistryKey().getValue() + " FC: " + ((LongSet)longSet).size());
            list.add("");
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.client.getCameraEntity().getX(), this.client.getCameraEntity().getY(), this.client.getCameraEntity().getZ()));
            list.add(String.format("Block: %d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
            list.add(String.format("Chunk: %d %d %d in %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15, ChunkSectionPos.getSectionCoord(blockPos.getX()), ChunkSectionPos.getSectionCoord(blockPos.getY()), ChunkSectionPos.getSectionCoord(blockPos.getZ())));
            list.add(String.format(Locale.ROOT, "Facing: %s (%s) (%.1f / %.1f)", direction, string7, MathHelper.wrapDegrees(entity.yaw), MathHelper.wrapDegrees(entity.pitch)));
            WorldChunk worldChunk = this.getClientChunk();
            if (worldChunk.isEmpty()) {
                list.add("Waiting for chunk...");
            } else {
                int i = this.client.world.getChunkManager().getLightingProvider().getLight(blockPos, 0);
                int j = this.client.world.getLightLevel(LightType.SKY, blockPos);
                int k = this.client.world.getLightLevel(LightType.BLOCK, blockPos);
                list.add("Client Light: " + i + " (" + j + " sky, " + k + " block)");
                WorldChunk worldChunk2 = this.getChunk();
                StringBuilder stringBuilder = new StringBuilder("CH");
                Heightmap.Type[] var21 = Heightmap.Type.values();
                int var22 = var21.length;

                int var23;
                Heightmap.Type type2;
                for(var23 = 0; var23 < var22; ++var23) {
                    type2 = var21[var23];
                    if (type2.shouldSendToClient()) {
                        stringBuilder.append(" ").append((String)HEIGHT_MAP_TYPES.get(type2)).append(": ").append(worldChunk.sampleHeightmap(type2, blockPos.getX(), blockPos.getZ()));
                    }
                }

                list.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                stringBuilder.append("SH");
                var21 = Heightmap.Type.values();
                var22 = var21.length;

                for(var23 = 0; var23 < var22; ++var23) {
                    type2 = var21[var23];
                    if (type2.isStoredServerSide()) {
                        stringBuilder.append(" ").append((String)HEIGHT_MAP_TYPES.get(type2)).append(": ");
                        if (worldChunk2 != null) {
                            stringBuilder.append(worldChunk2.sampleHeightmap(type2, blockPos.getX(), blockPos.getZ()));
                        } else {
                            stringBuilder.append("??");
                        }
                    }
                }

                list.add(stringBuilder.toString());
                if (blockPos.getY() >= this.client.world.getBottomY() && blockPos.getY() < this.client.world.getTopY()) {
                    list.add("Biome: " + this.client.world.getRegistryManager().get(Registry.BIOME_KEY).getId(this.client.world.getBiome(blockPos)));
                    long l = 0L;
                    float h = 0.0F;
                    if (worldChunk2 != null) {
                        h = world.getMoonSize();
                        l = worldChunk2.getInhabitedTime();
                    }

                    LocalDifficulty localDifficulty = new LocalDifficulty(world.getDifficulty(), world.getTimeOfDay(), l, h);
                    list.add(String.format(Locale.ROOT, "Local Difficulty: %.2f // %.2f (Day %d)", localDifficulty.getLocalDifficulty(), localDifficulty.getClampedLocalDifficulty(), this.client.world.getTimeOfDay() / 24000L));
                }
            }

            ServerWorld serverWorld = this.getServerWorld();
            if (serverWorld != null) {
                SpawnHelper.Info info = serverWorld.getChunkManager().getSpawnInfo();
                if (info != null) {
                    Object2IntMap<SpawnGroup> object2IntMap = info.getGroupToCount();
                    int m = info.getSpawningChunkCount();
                    list.add("SC: " + m + ", " + (String) Stream.of(SpawnGroup.values()).map((spawnGroup) -> {
                        return Character.toUpperCase(spawnGroup.getName().charAt(0)) + ": " + object2IntMap.getInt(spawnGroup);
                    }).collect(Collectors.joining(", ")));
                } else {
                    list.add("SC: N/A");
                }
            }

            ShaderEffect shaderEffect = this.client.gameRenderer.getShader();
            if (shaderEffect != null) {
                list.add("Shader: " + shaderEffect.getName());
            }

            list.add(this.client.getSoundManager().getDebugString() + String.format(" (Mood %d%%)", Math.round(this.client.player.getMoodPercentage() * 100.0F)));
            return list;
        }
    }
    @Shadow
    private WorldChunk getClientChunk() {
        if (this.chunk == null) {
            this.chunk = this.client.world.getChunk(this.pos.x, this.pos.z);
        }

        return this.chunk;
    }
    @Shadow
    private static final Map<Heightmap.Type, String> HEIGHT_MAP_TYPES = (Map) Util.make(new EnumMap(Heightmap.Type.class), (enumMap) -> {
        enumMap.put(Heightmap.Type.WORLD_SURFACE_WG, "SW");
        enumMap.put(Heightmap.Type.WORLD_SURFACE, "S");
        enumMap.put(Heightmap.Type.OCEAN_FLOOR_WG, "OW");
        enumMap.put(Heightmap.Type.OCEAN_FLOOR, "O");
        enumMap.put(Heightmap.Type.MOTION_BLOCKING, "M");
        enumMap.put(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, "ML");
    });
}
