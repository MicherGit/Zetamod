package zeta.zetamod.mod.features.portals;

import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.features.keystone.RegisterKeystone;

public class PortalInit implements ModInitializer {
    @Override
    public void onInitialize() {
        CustomPortalApiRegistry.addPortal(Blocks.MOSS_BLOCK,
                new Identifier("zetamod", "lush_nether"),
                0, 255, 8);
        CustomPortalApiRegistry.addPortal(RegisterKeystone.KEYSTONE_BLOCK,
                new Identifier(ZetaMod.MOD_ID, "etheral"),
                127, 127, 0);

    }
}
