package zeta.zet2mod.mod.managers;

import me.zeroeightsix.fiber.JanksonSettings;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Node;
import net.fabricmc.loader.api.FabricLoader;
import net.java.games.input.Event;
import net.minecraft.SharedConstants;
import net.minecraft.network.packet.s2c.play.SynchronizeTagsS2CPacket;
import zeta.zet2mod.mod.CodenameEliXrMain;
import zeta.zet2mod.mod.client.Zet2ModClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager  {
    /*
     * Copied from https://github.com/geniiii/FarLands/blob/master/src/main/java/site/geni/farlands/config/Config.java
     */
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(),
             "elixr." +
                    "json5");

    final private ConfigNode root = new ConfigNode();

    private final Node general = root.fork("general");
    private Event configValue;

    // Still @Overwrite-able!

    public static boolean farlandsDefaultValue() {
        return false;
    }

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
    public ConfigValue<Boolean> enableOneSeventeenBoolean = ConfigValue.builder(Boolean.class)
            .withName(String.valueOf("enableOneSeventeenBoolean"))
            .withComment(("Change title screen to say 1.17 instead of ") + String.valueOf(SharedConstants.getGameVersion().getName()))
            .withDefaultValue(false)
            .withParent(hashNode)
            .build();
    private Node world = root.fork("world");
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
