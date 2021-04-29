package mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface MixinEntityAccessor {
    @Accessor("entityId")
    int getEntityId_();

    @Accessor("yaw")
    float getYaw_();

    @Accessor("pitch")
    float getPitch_();

    @Accessor("yaw")
    void setYaw_(float f);

    @Accessor("pitch")
    void setPitch_(float f);
}