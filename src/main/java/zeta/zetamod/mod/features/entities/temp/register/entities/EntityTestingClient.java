package zeta.zetamod.mod.features.entities.temp.register.entities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import zeta.zetamod.api.oldfabric.registry.EntityRendererRegistry;
import zeta.zetamod.mod.features.entities.cubeentity.testing.CubeEntityRenderer;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(EntityTesting.CUBE, (dispatcher, context) -> {
            return new CubeEntityRenderer(dispatcher);
        });
    }

}
