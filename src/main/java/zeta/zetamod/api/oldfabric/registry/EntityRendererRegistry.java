package zeta.zetamod.api.oldfabric.registry;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.resource.ReloadableResourceManager;
import zeta.zetamod.api.model.EntityRenderer;

import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;

public class EntityRendererRegistry {
    @FunctionalInterface
    public interface Factory {
        EntityRenderer<? extends Entity> create(EntityRenderDispatcher manager, EntityRendererRegistry.Context context);
    }

    public static final class Context {
        private final TextureManager textureManager;
        private final ReloadableResourceManager resourceManager;
        private final ItemRenderer itemRenderer;
        private final Map<EntityType<?>, EntityRenderer<?>> rendererMap;

        private Context(TextureManager textureManager, ReloadableResourceManager resourceManager, ItemRenderer itemRenderer, Map<EntityType<?>, EntityRenderer<?>> rendererMap) {
            this.textureManager = textureManager;
            this.resourceManager = resourceManager;
            this.itemRenderer = itemRenderer;
            this.rendererMap = rendererMap;
        }

        public TextureManager getTextureManager() {
            return textureManager;
        }

        public ReloadableResourceManager getResourceManager() {
            return resourceManager;
        }

        public ItemRenderer getItemRenderer() {
            return itemRenderer;
        }
    }

    public static final EntityRendererRegistry INSTANCE = new EntityRendererRegistry();
    private final Map<EntityRenderDispatcher, Context> renderManagerMap = new WeakHashMap<>();
    private final Map<EntityType<?>, EntityRendererRegistry.Factory> renderSupplierMap = new HashMap<>();

    private EntityRendererRegistry() { }

    public void initialize(EntityRenderDispatcher manager, TextureManager textureManager, ReloadableResourceManager resourceManager, ItemRenderer itemRenderer, Map<EntityType<?>, EntityRenderer<?>> renderers) {
        synchronized (renderSupplierMap) {
            if (renderManagerMap.containsKey(manager)) {
                return;
            }

            Context context = new Context(textureManager, resourceManager, itemRenderer, renderers);
            renderManagerMap.put(manager, context);

            for (EntityType<?> c : renderSupplierMap.keySet()) {
                renderers.put(c, renderSupplierMap.get(c).create(manager, context));
            }
        }
    }

    public void register(EntityType<?> entityType, EntityRendererRegistry.Factory factory) {
        synchronized (renderSupplierMap) {
            // TODO: Revert arr and use 1.17 api
            renderSupplierMap.put(entityType, factory);

            for (EntityRenderDispatcher manager : renderManagerMap.keySet()) {
                renderManagerMap.get(manager).rendererMap.put(entityType, factory.create(manager, renderManagerMap.get(manager)));
            }
        }
    }
}
