package zeta.zet2mod.mod.features.register;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zet2mod.mod.CodenameEliXrMain;
import zeta.zet2mod.mod.features.blocks.VoidBlock;

public class RegisterItems {
    public RegisterItems(){}
    public static Block VoidBlock_BLOCK;
    public static BlockItem VoidBlock_ITEM;
    public void registerItems() {
        // Register keystone item
        RegisterKeystone keystone = new RegisterKeystone();
        keystone.registerItem();
        VoidBlock_BLOCK = Registry.register(Registry.BLOCK, id("void"), new VoidBlock());
        VoidBlock_ITEM = Registry.register(Registry.ITEM, id("void"),
                new BlockItem(VoidBlock_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
                // CODE DEF NOT RIPPED FROM A DUMMY MCREATOR MOD
    }
    protected static final Identifier id(String s) {
        return new Identifier(CodenameEliXrMain.MOD_ID, s);
    }
}
