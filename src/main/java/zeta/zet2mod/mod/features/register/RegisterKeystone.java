package zeta.zetamod.mod.features.register;

import net.minecraft.item.Item;
import zeta.zetamod.mod.ZetaModMain;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.features.items.KeystoneItem;

import java.util.Locale;

public class RegisterKeystone {
    public static Item KEYSTONE;
    public RegisterKeystone() {}
    public void registerItem() {
        KEYSTONE = Registry.register(Registry.ITEM, new Identifier(ZetaModMain.MOD_ID,
                "KEYSTONE".toLowerCase(Locale.ROOT)
        ), new KeystoneItem(new FabricItemSettings().group(ItemGroup.MISC)));
    }
}
