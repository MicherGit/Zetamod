package zeta.zetamod.mod.managers;

import me.zeroeightsix.fiber.JanksonSettings;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Node;
import net.fabricmc.loader.api.FabricLoader;
import net.java.games.input.Event;
import net.minecraft.SharedConstants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zeta.zetamod.mod.ZetaMod;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager  {
    /*
     * Copied from https://github.com/geniiii/FarLands/blob/master/src/main/java/site/geni/farlands/config/Config.java
     */
    public static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(),
             "zetamod." +
                    "json5");

    final private ConfigNode root = new ConfigNode();

    private final Node general = root.fork("general");
    private Node world = root.fork("world");
    private Node gameplay = root.fork("Gameplay");
    private Event configValue;

    // Still @Overwrite-able!

    public static boolean farlandsDefaultValue() {
        return false;
    }
    public ConfigValue<Boolean> fixFireballs = ConfigValue.builder(Boolean.class)
            .withName("capFireballs")
            .withComment("If set to true, removes the patch done to fix https://bugs.mojang.com/browse/MC-220698" +
                    "and makes bedrock farms work again")
            .withDefaultValue(true)
            .withParent(gameplay)
            .build();
    public ConfigValue<Boolean> farLandsEnabled = ConfigValue.builder(Boolean.class)
            .withName("farLandsEnabled")
            .withComment("Whether or not the Far Lands should generate.")
            .withDefaultValue(farlandsDefaultValue())
            .withParent(general)
            .build();
    private boolean expandWB = true;
    public ConfigValue<Boolean> worldBorderExpanded = ConfigValue.builder(Boolean.class)
            .withName("worldBorderExpanded")
            .withComment("Should the worldborder be expanded? (Note, currently does nothing" +
                    ")")
            .withDefaultValue(expandWB)
            .withParent(general)
            .build();

    public ConfigValue<Boolean> shardFarLands = ConfigValue.builder(Boolean.class)
            .withName(
                    "shardFarLands"
            )
            .withComment("Should the shard farlands spawn?")
            .withDefaultValue(false)
            .withParent(general)
            .build();
    private Node hashNode = root.fork("Hash Key");
    public ConfigValue<String> hash = ConfigValue.builder(String.class)
            .withName("hash")
            .withComment("private hash given out to testers")
            .withDefaultValue("")
            .withParent(hashNode)
            .build();
    public ConfigValue<Integer> fileHash = ConfigValue.builder(Integer.class)
            .withComment("hash of config file used for testing")
            .withName("fileHash")
            .withDefaultValue(CONFIG_FILE.hashCode())
            .withParent(hashNode)
            .build();
    public ConfigValue<Integer> modVersion = ConfigValue.builder(Integer.class)
            .withComment("Current mod version (do not change this!!)")
            .withName("modVersion")
            .withDefaultValue(ZetaMod.MOD_DEV_V)
            .withParent(hashNode)
            .build();
    public ConfigValue<Double> coordinateScale = ConfigValue.builder(Double.class)
            .withName("coordinateScale")
            .withComment("The world's coordinate scale.")
            .withDefaultValue(684.412D)
            .withParent(world)
            .build();
    public ConfigValue<Double> coordinateScaleMultiplier = ConfigValue.builder(Double.class)
            .withName("coordinateScaleMulti")
            .withComment("Coordinate scale multiplyer so you don't have to mess with the one above.")
            .withDefaultValue(1.0D)
            .withParent(world)
            .build();
    public ConfigValue<Double> heightScale = ConfigValue.builder(Double.class)
            .withName("heightScale")
            .withComment("The world's height scale.")
            .withDefaultValue(684.412D)
            .withParent(world)
            .build();
    public ConfigValue<Double> heightScaleMultiplier = ConfigValue.builder(Double.class)
            .withName("heightScaleMulti")
            .withComment("Height scale multiplyer so you don't have to mess with the one above.")
            .withDefaultValue(1.0D)
            .withParent(world)
            .build();
    public ConfigValue<Boolean> cursed = ConfigValue.builder(Boolean.class)
            .withName("curse")
            .withComment("Make the mod even more cursed than it is. (Note, also does nothing)")
            .withDefaultValue(false)
            .withParent(general)
            .build();
    public static ConfigManager getConfig() {
        return GeneralManager.CONFIG;
    }

    public ConfigManager() throws FiberException, IOException {
        File currentConfigFile = new File(FabricLoader.getInstance().getConfigDirectory(),
                "zetamod.json5.generated");
        currentConfigFile.createNewFile();
        save(currentConfigFile);
        File configFile = new File(FabricLoader.getInstance().getConfigDirectory(),
                "zetamodupdate");
        //if(CONFIG_FILE.exists()) {
        //    if(currentConfigFile.hashCode() != CONFIG_FILE.hashCode()) {

        //    } else {
        //        LogManager.getLogger().log(Level.INFO, "Will not delete config file!");
        //    }
        //}
        if(!configFile.exists()) {
            copy(CONFIG_FILE, configFile);
        }
        if(configFile.hashCode() != currentConfigFile.hashCode()) {
            logger.error("CONFIG FOUND WITH DIFFERENT HASH, DELETING!");
            CONFIG_FILE.delete();
        }
        currentConfigFile.delete();
    }
    public static void copy(File from, File to) throws IOException {
        Path s = Paths.get(from.getAbsolutePath());
        Path t = Paths.get(to.getAbsolutePath());
        Files.copy(s, t);
    }

    private Logger logger = LogManager.getLogger();


    public void save(File configFile) {
        try {
            new JanksonSettings().serialize(this.root, Files.newOutputStream(configFile.toPath()), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigManager load(File configFile) {
        if (!configFile.exists()) {
            this.save(configFile);
        }

        try {
            new JanksonSettings().deserialize(this.root, Files.newInputStream(configFile.toPath()));
        } catch (IOException | FiberException e) {
            e.printStackTrace();
        }
        return this;
    }
}
