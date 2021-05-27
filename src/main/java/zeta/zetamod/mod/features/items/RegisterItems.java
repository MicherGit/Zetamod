package zeta.zetamod.mod.features.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.features.blocks.VoidBlock;
import zeta.zetamod.mod.features.keystone.KeystoneTools;
import zeta.zetamod.mod.features.keystone.RegisterKeystone;

public class RegisterItems {
    public RegisterItems(){}
    public static Block VoidBlock_BLOCK;
    public static BlockItem VoidBlock_ITEM;
    public void registerItems() {
        // Register keystone item
        RegisterKeystone keystone = new RegisterKeystone();
        VoidBlock_BLOCK = Registry.register(Registry.BLOCK, id("void"), new VoidBlock());
        VoidBlock_ITEM = Registry.register(Registry.ITEM, id("void"),
                new BlockItem(VoidBlock_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
                // CODE DEF NOT RIPPED FROM A DUMMY MCREATOR MOD


        Registry.register(Registry.ITEM, keystoneTool("sword"), KeystoneTools.KEYSTONE_BLADE);
        Registry.register(Registry.ITEM, keystoneTool("shovel"), KeystoneTools.KEYSTONE_SHOVEL);
        Registry.register(Registry.ITEM, keystoneTool("pickaxe"), KeystoneTools.KEYSTONE_PICKAXE);
        Registry.register(Registry.ITEM, keystoneTool("axe"), KeystoneTools.KEYSTONE_AXE);
        Registry.register(Registry.ITEM, keystoneTool("tiller"), KeystoneTools.KEYSTONE_TILLER);
    }
    protected static final Identifier id(String s) {
        return new Identifier(ZetaMod.MOD_ID, s);
    }
    protected static final Identifier keystoneTool(String s) {return new Identifier(ZetaMod.MOD_ID, "keystone_"+s);}
}
