package zeta.zet2mod.mod.features.portals;

import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class LushNetherPortal implements ModInitializer {
    @Override
    public void onInitialize() {
        CustomPortalApiRegistry.addPortal(Blocks.MOSS_BLOCK,
                new Identifier("zet2mod", "lush_nether"),
                0, 255, 8);
        /*
         * TODO: Keystone lights portal
         * Work so far: CustomPortalApiRegistry.addPortal(Blocks.NETHERITE_BLOCK, PortalIgnitionSource.CustomSource(new Identifier("zet2mod", "keystone")), new Identifier("my_mod_id", "my_dimension_id"), 51, 52, 49);
         */
    }

}
