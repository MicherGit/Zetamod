package zeta.zetamod.mod.features.entities.temp.register.entities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.features.entities.cubeentity.testing.CubeEntity;

public class EntityTesting implements ModInitializer {
    public static final EntityType<CubeEntity> CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ZetaMod.MOD_ID, "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    @Override
    public void onInitialize() {

        FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());
    }
}
