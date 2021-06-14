package mixins.zeta.accessors;

import net.minecraft.util.math.noise.PerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PerlinNoiseSampler.class)
public interface PerlinNoiseSamplerAccessor {
    @Invoker("sample")
    double sample(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double fadeLocalX);
}
