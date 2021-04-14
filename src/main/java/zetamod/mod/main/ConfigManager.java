package zetamod.mod.main;

import me.zeroeightsix.fiber.JanksonSettings;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Node;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager  {
    /*
     * Copied from https://github.com/geniiii/FarLands/blob/master/src/main/java/site/geni/farlands/config/Config.java
     */
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "ZetaMod.json5");

    final private ConfigNode root = new ConfigNode();

    private Node general = root.fork("general");

    public ConfigValue<Boolean> farLandsEnabled = ConfigValue.builder(Boolean.class)
            .withName("farLandsEnabled")
            .withComment("Whether or not the Far Lands should generate.")
            .withDefaultValue(true)
            .withParent(general)
            .build();
    public ConfigManager() throws FiberException {}


    public void save() {
        try {
            new JanksonSettings().serialize(this.root, Files.newOutputStream(ConfigManager.CONFIG_FILE.toPath()), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigManager load() {
        if (!CONFIG_FILE.exists()) {
            this.save();
        }

        try {
            new JanksonSettings().deserialize(this.root, Files.newInputStream(ConfigManager.CONFIG_FILE.toPath()));
        } catch (IOException | FiberException e) {
            e.printStackTrace();
        }
        return this;
    }
}
