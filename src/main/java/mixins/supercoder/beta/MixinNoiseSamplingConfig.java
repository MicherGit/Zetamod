package mixins.supercoder.beta;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.gen.chunk.NoiseSamplingConfig;

@Mixin(NoiseSamplingConfig.class)
public class MixinNoiseSamplingConfig {
    @Redirect(method = "<clinit>", at = @At(target =
            "Lnet/minecraft/world/gen/chunk/NoiseSamplingConfig;CODEC_RANGE:Lcom/mojang/serialization/Codec;",
            value = "INVOKE"))
    private static Codec<Double> fixRange(double min, double max) {
        return Codec.doubleRange(Integer.MIN_VALUE, Integer.MAX_VALUE); // changes it to be 0.001 to 10 million instead of 1 thousand
    }
}
