package zeta.zetaforged.mod.features.entities.shitass;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import zeta.zetaforged.mod.ZetaForged;

public class ShitassEntityRenderer extends MobEntityRenderer<ShitassEntity, ShitassEntityModel> {


    public ShitassEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ShitassEntityModel(), 0.5);
    }

    @Override
    public Identifier getTexture(ShitassEntity entity) {
        return new Identifier(ZetaForged.MOD_ID, "textures/entity/shitass/shitass.png");
    }
}
