package zeta.zetamod.mod.features.entities.shitass;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import zeta.zetamod.mod.ZetaMod;

public class ShitassEntityRenderer extends MobEntityRenderer<ShitassEntity, ShitassEntityModel> {


    public ShitassEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ShitassEntityModel(), 0.5);
    }

    @Override
    public Identifier getTexture(ShitassEntity entity) {
        return new Identifier(ZetaMod.MOD_ID, "textures/entity/shitass/shitass.png");
    }
}
