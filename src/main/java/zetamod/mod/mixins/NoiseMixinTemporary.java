package zetamod.mod.mixins;

import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zetamod.mod.main.ConfigManager;

@Mixin(NoiseSamplingConfig.class)
public class NoiseMixinTemporary {
    @Shadow
    private final double xzScale;
    private final double yScale;

    public NoiseMixinTemporary(double xzScale, double yScale) {
        this.xzScale =
                ConfigManager.getConfig().coordinateScale.getValue();
        this.yScale =
                ConfigManager.getConfig().heightScale.getValue();
    }
}
