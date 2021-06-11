package zeta.zetamod.mod.features.entities.cubeentity.testing;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.MagmaCubeEntityModel;
import net.minecraft.util.Identifier;
import zeta.zetamod.api.model.EntityRenderDispatcherGetText;
import zeta.zetamod.api.model.MobEntityRenderer;
import zeta.zetamod.mod.ZetaMod;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {
    public CubeEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super((EntityRenderDispatcherGetText) entityRenderDispatcher, new CubeEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier(ZetaMod.MOD_ID, "textures/entity/cube/cube.png");
    }
}
