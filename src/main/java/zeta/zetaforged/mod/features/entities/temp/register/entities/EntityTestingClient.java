package zeta.zetaforged.mod.features.entities.temp.register.entities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import zeta.zetaforged.mod.features.entities.shitass.ShitassEntity;
import zeta.zetaforged.mod.features.entities.shitass.ShitassEntityRenderer;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(EntityTesting.SHITASS, new EntityRendererFactory<ShitassEntity>() {
            @Override
            public EntityRenderer<ShitassEntity> create(Context ctx) {
                return new ShitassEntityRenderer(ctx);
            }
        });
    }

}
