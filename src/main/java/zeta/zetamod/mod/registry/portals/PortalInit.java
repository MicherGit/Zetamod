package zeta.zetamod.mod.registry.portals;

import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.features.biomes.ConcerningBiome;
import zeta.zetamod.mod.registry.items.RegisterItems;
import zeta.zetamod.mod.features.keystone.Keystone;

public class PortalInit implements ModInitializer {
    @Override
    public void onInitialize() {
        CustomPortalApiRegistry.addPortal(Blocks.MOSS_BLOCK,
                new Identifier(ZetaMod.MOD_ID, "lush_nether"),
                0, 255, 8);
        CustomPortalApiRegistry.addPortal(Keystone.KEYSTONE_BLOCK,
                new Identifier(ZetaMod.MOD_ID, "etheral"),
                127, 127, 0);
        CustomPortalApiRegistry.addPortal(ConcerningBiome.GIGACONCERN_BLOCK,
                new Identifier(ZetaMod.MOD_ID, "concerningworld"),
                32, 105, 168
        );
        CustomPortalApiRegistry.addPortal(RegisterItems.VoidBlock_BLOCK,
                new Identifier(ZetaMod.MOD_ID, "void_dimension"),
                0,0,0
        );

    }
    public Identifier ZMI(String name) {
        return new Identifier(ZetaMod.MOD_ID, name);
    }
}
