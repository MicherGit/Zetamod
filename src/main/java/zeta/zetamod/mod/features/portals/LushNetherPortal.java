package zeta.zetamod.mod.features.portals;

import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.lwjgl.system.CallbackI;
import zeta.zetamod.mod.ZetaModMain;
import zeta.zetamod.mod.features.items.KeystoneItem;
import zeta.zetamod.mod.features.register.RegisterKeystone;

public class LushNetherPortal implements ModInitializer {
    @Override
    public void onInitialize() {
        CustomPortalApiRegistry.addPortal(Blocks.MOSS_BLOCK,
                new Identifier("zetamod", "lush_nether"),
                0, 255, 8);
        /*
         * TODO: Keystone lights portal
         * Work so far: CustomPortalApiRegistry.addPortal(Blocks.NETHERITE_BLOCK, PortalIgnitionSource.CustomSource(new Identifier("zetamod", "keystone")), new Identifier("my_mod_id", "my_dimension_id"), 51, 52, 49);
         */
    }

}
