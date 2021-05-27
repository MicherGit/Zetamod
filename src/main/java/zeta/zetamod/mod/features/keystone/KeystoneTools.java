package zeta.zetamod.mod.features.keystone;

import net.minecraft.item.*;
import zeta.zetamod.mod.features.keystone.KeystoneToolMaterial;
import zeta.zetamod.mod.features.items.tools.wideners.AxeItem;
import zeta.zetamod.mod.features.items.tools.wideners.PickaxeItem;
import zeta.zetamod.mod.features.items.tools.wideners.TillerItem;

public class KeystoneTools {
    public KeystoneTools() {

    }
    public static ToolItem KEYSTONE_SHOVEL = new ShovelItem(new KeystoneToolMaterial(), 1.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    public static ToolItem KEYSTONE_BLADE = new SwordItem(new KeystoneToolMaterial(), 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    public static ToolItem KEYSTONE_PICKAXE = new PickaxeItem(new KeystoneToolMaterial(), 1, -2.8F, new Item.Settings().group(ItemGroup.TOOLS));
    public static ToolItem KEYSTONE_AXE = new AxeItem(new KeystoneToolMaterial(), 7.0F, -3.2F, new Item.Settings().group(ItemGroup.TOOLS));
    public static ToolItem KEYSTONE_TILLER = new TillerItem(new KeystoneToolMaterial(), 7, -3.2F, new Item.Settings().group(ItemGroup.TOOLS));
}
