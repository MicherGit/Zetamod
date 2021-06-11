package zeta.zetamod.mod.registry.items;

import zeta.zetamod.mod.ZetaMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import zeta.zetamod.mod.features.items.CrashItem;

public class CrashItemInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID,
                "crashitem"
        ), new CrashItem(new FabricItemSettings().group(ZetaMod.ZETAMOD_ITEMS)));
        ZetaMod.log(Level.INFO,"Intialized debug item!");
    }
}