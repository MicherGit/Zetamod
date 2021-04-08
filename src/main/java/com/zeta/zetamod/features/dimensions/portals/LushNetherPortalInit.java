package com.zeta.zetamod.features.dimensions.portals;

import com.zeta.zetamod.main.ZMMain;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class LushNetherPortalInit implements ModInitializer {
    @Override
    public void onInitialize() {
        registerPortalLushNether();

    }

    private void registerPortalLushNether() {
        CustomPortalApiRegistry.addPortal(Blocks.MOSSY_COBBLESTONE, new Identifier(ZMMain.MOD_ID, "lush_nether"), 234, 183, 8);
    }
}
