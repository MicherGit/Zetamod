package zeta.zetaforged.mod.features.entities.shitass;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetaforged.mod.ZetaForged;
import zeta.zetaforged.mod.features.entities.shitass.ShitassEntity;

public class ShitassRegister implements ModInitializer {
    public static final EntityType<ShitassEntity> SHITASS = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ZetaForged.MOD_ID, "shitass"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ShitassEntity::new).dimensions(EntityDimensions.fixed(1, 2)).build()
    );
    @Override
    public void onInitialize() {

        FabricDefaultAttributeRegistry.register(SHITASS, ShitassEntity.createMobAttributes());
    }
}
