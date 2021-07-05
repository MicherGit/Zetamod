package zeta.zetaforged.api.mixins.zeta.shard;

import zeta.zetaforged.api.mixins.zeta.accessors.PerlinNoiseSamplerAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.*;
import zeta.zetaforged.mod.managers.GeneralManager;

@Mixin(value = PerlinNoiseSampler.class, priority = 999)
public abstract class Shard {
    private boolean logged = false;
    @Shadow
    public final double originX;
    public final double originY;
    public final double originZ;

    @Shadow
    private final byte[] permutations;

    public Shard(double originX, double originY, double originZ, byte[] permutations) {
        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
        this.permutations = permutations;
    }
    /**
     * @author
     */
    @Overwrite
    @Deprecated
    public double sample(double x, double y, double z, double yScale, double yMax) {
        double d = x + this.originX;
        double e = y + this.originY;
        double f = z + this.originZ;
        int i = MathHelper.floor(d);
        int j = MathHelper.floor(e);
        int k = MathHelper.floor(f);
        double g,h,l;
        if (!GeneralManager.getConfig().shardFarLands.getValue()) {
            g = d - (double)i;
            h = e - (double)j;
            l = f - (double)k;
        } else {
            g = d - (float)i;
            h = e - (float)j;
            l = f - (float)k;
        }

        double p;
        if (yScale != 0.0D) {
            double n;
            if (yMax >= 0.0D && yMax < h) {
                n = yMax;
            } else {
                n = h;
            }

            p = (double)MathHelper.floor(n / yScale + 1.0000000116860974E-7D) * yScale;
        } else {
            p = 0.0D;
        }

        return ((PerlinNoiseSamplerAccessor)this).invokeSample(i, j, k, g, h - p, l, h);
    }

    /**
     * @author
     */
    @Overwrite
    public double sampleDerivative(double x, double y, double z, double[] ds) {
        double d = x + this.originX;
        double e = y + this.originY;
        double f = z + this.originZ;
        int i = MathHelper.floor(d);
        int j = MathHelper.floor(e);
        int k = MathHelper.floor(f);
        double g,h,l;
        if (!GeneralManager.getConfig().shardFarLands.getValue()) {
            g = d - (double) i;
            h = e - (double) j;
            l = f - (double) k;
        } else {
            g = d - (float)i;
            h = e - (float)j;
            l = f - (float)k;
            if (FabricLoader.getInstance().isModLoaded("lithium") && !this.logged) {
                LogManager.getLogger().log(Level.ERROR, "LITHIUM DETECTED! Shattered farlands may not be present!");
            }
        }
        return this.sampleDerivative(i, j, k, g, h, l, ds);
    }
    @Shadow
    @Final
    public abstract double sampleDerivative(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double[] ds);
}