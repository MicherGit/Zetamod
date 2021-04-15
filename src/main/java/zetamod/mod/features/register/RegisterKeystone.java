package zetamod.mod.features.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zetamod.mod.features.items.KeystoneItem;
import zetamod.mod.ZetaModMain;

public class RegisterKeystone {
    public RegisterKeystone() {}
    public void registerItem() {
        Registry.register(Registry.ITEM, new Identifier(ZetaModMain.MOD_ID,
                "KEYSTONE"
        ), new KeystoneItem(new FabricItemSettings().group(ItemGroup.MISC)));
    }
}
