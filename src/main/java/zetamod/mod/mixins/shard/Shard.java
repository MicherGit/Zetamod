package zetamod.mod.mixins.shard;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.*;
import zetamod.mod.managers.GeneralManager;

@Mixin(PerlinNoiseSampler.class)
public class Shard {
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
     * @reason
     */
    @Overwrite
    public double sampleDerivative(double d, double e, double f, double[] ds) {
        double g = d + this.originX;
        double h = e + this.originY;
        double i = f + this.originZ;
        int j = MathHelper.floor(g);
        int k = MathHelper.floor(h);
        int l = MathHelper.floor(i);
        double m,n,o;
        if (GeneralManager.getConfig().shardFarLands.getValue()) {
            m = g - (float) j;
            n = h - (float) k;
            o = i - (float) l;
        } else {
            m = g - (double) j;
            n = h - (double) k;
            o = i - (double) l;
        }
        return this.sampleDerivative(j, k, l, m, n, o, ds);
    }

    /**
     * @author
     * @reason
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
        if(GeneralManager.getConfig().shardFarLands.getValue()) {
            g = d - (float) i;
            h = e - (float) j;
            l = f - (float) k;
        } else {

            g = d - (double) i;
            h = e - (double) j;
            l = f - (double) k;
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

        return this.sample(i, j, k, g, h - p, l, h);
    }

    @Shadow
    public double sample(int i, int j, int k, double g, double v, double l, double h) {
        return 0;
    }

    @Shadow
    private double sampleDerivative(int i, int j, int k, double d, double e, double f, double[] ds) {
        return 0;
    }
}
