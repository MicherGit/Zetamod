package zeta.zetamod.mod.features.keystone;

import jdk.jpackage.internal.Log;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import zeta.zetamod.mod.ZetaMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.features.keystone.KeystoneItem;

import java.util.Locale;

public class RegisterKeystone implements ModInitializer {
    public static Item KEYSTONE = Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID,
            "KEYSTONE".toLowerCase(Locale.ROOT)
    ), new KeystoneItem(new FabricItemSettings().group(ItemGroup.MISC)));
    public static Block KEYSTONE_BLOCK = new Block(FabricBlockSettings.of(Material.AMETHYST).strength(9.0f));
    @Override
    public void onInitialize() {
        LogManager.getLogger().log(Level.INFO, "Adding keystone items");
        Registry.register(Registry.BLOCK, new Identifier(ZetaMod.MOD_ID, "keystone_block"), KEYSTONE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, "keystone_block"), new BlockItem(KEYSTONE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
    private void log(String message) {
        LogManager.getLogger().log(Level.INFO, message);
    }
}
