package me.sargunvohra.mcmods.dataloader;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourcePackSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoader implements ModInitializer {
    public static final ResourcePackSource RESOURCE_PACK_SOURCE = ResourcePackSource
        .nameAndSource("pack.source.dataloader");
    public static final Path DATAPACKS_PATH = FabricLoader.getInstance().getConfigDirectory()
        .toPath().resolve("datapacks");

    @Override
    public void onInitialize() {
        try {
            Path path = DATAPACKS_PATH;
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
