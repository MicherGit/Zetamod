package zeta.zetamod.api.model;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;

public class EntityRenderDispatcherGetText extends EntityRenderDispatcher {
    public EntityRenderDispatcherGetText(TextureManager textureManager, ItemRenderer itemRenderer, TextRenderer textRenderer, GameOptions gameOptions, EntityModelLoader modelLoader) {
        super(textureManager, itemRenderer, textRenderer, gameOptions, modelLoader);
    }
    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }
}
