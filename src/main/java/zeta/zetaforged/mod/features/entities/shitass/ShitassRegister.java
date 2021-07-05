package zeta.zetaforged.mod.features.entities.shitass;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetaforged.mod.ZetaForged;
import zeta.zetaforged.mod.features.entities.shitass.ShitassEntity;

import static zeta.zetaforged.mod.ZetaForged.MOD_ID;

public class ShitassRegister implements ModInitializer {
    public static final EntityType<ShitassEntity> SHITASS = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "shitass"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ShitassEntity::new).dimensions(EntityDimensions.fixed(1, 2)).build()
    );
    public static final SpawnEggItem SHITASS_SPAWN_EGG = new SpawnEggItem(SHITASS, 0x338AE2, 0xE71E0A, new FabricItemSettings().group(ZetaForged.ZETAFORGED_ITEMS));
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(SHITASS, ShitassEntity.createMobAttributes());
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "shitass"), SHITASS_SPAWN_EGG);
    }
}
