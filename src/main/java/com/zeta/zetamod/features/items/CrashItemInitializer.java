package com.zeta.zetamod.features.items;

import com.zeta.zetamod.features.ZetaModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

public class CrashItemInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(ZetaModInitializer.MOD_ID, "crashitem"), new CrashItem(new FabricItemSettings().group(ItemGroup.MISC)));
        ZetaModInitializer.log(Level.INFO,"Intialized debug item!");
    }
}