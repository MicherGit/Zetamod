package zeta.zetaforged.mod.features.entities.shitass;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

@Environment(EnvType.CLIENT)
public class ShitassEntityRenderRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ShitassRegister.SHITASS, new EntityRendererFactory<ShitassEntity>() {
            @Override
            public EntityRenderer<ShitassEntity> create(Context ctx) {
                return new ShitassEntityRenderer(ctx);
            }
        });
    }

}
