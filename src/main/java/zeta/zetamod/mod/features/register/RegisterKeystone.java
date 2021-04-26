package zeta.zetamod.mod.features.register;

import zeta.zetamod.mod.ZetaModMain;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.features.items.KeystoneItem;

import java.util.Locale;

public class RegisterKeystone {
    public RegisterKeystone() {}
    public void registerItem() {
        Registry.register(Registry.ITEM, new Identifier(ZetaModMain.MOD_ID,
                "KEYSTONE".toLowerCase(Locale.ROOT)
        ), new KeystoneItem(new FabricItemSettings().group(ItemGroup.MISC)));
    }
}
