package mixins.zeta.shard;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.*;
import zeta.zetamod.mod.managers.GeneralManager;

@Mixin(PerlinNoiseSampler.class)
public class Shard {
    private static final boolean shardFarlands = GeneralManager.getConfig().shardFarLands.getValue();
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
    //Todo: Update this to latest version
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
        double g, h, l;
        if(shardFarlands) {
            g = d - (float)i;
            h = e - (float)j;
            l = f - (float)k;
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
            if (shardFarlands) {
                p = (float) MathHelper.floor(n / yScale + 1.0000000116860974E-7D) * yScale;
            } else {
                p = (double) MathHelper.floor(n / yScale + 1.0000000116860974E-7D) * yScale;
            }
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
    private int getGradient(int hash) {
        return this.permutations[hash & 255] & 255;
    }
    /**
     * @author Zeta
     * @reason Idk if it will work without it.
     */
    @Overwrite
    private double sampleDerivative(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double[] ds) {
        int i = this.getGradient(sectionX);
        int j = this.getGradient(sectionX + 1);
        int k = this.getGradient(i + sectionY);
        int l = this.getGradient(i + sectionY + 1);
        int m = this.getGradient(j + sectionY);
        int n = this.getGradient(j + sectionY + 1);
        int o = this.getGradient(k + sectionZ);
        int p = this.getGradient(m + sectionZ);
        int q = this.getGradient(l + sectionZ);
        int r = this.getGradient(n + sectionZ);
        int s = this.getGradient(k + sectionZ + 1);
        int t = this.getGradient(m + sectionZ + 1);
        int u = this.getGradient(l + sectionZ + 1);
        int v = this.getGradient(n + sectionZ + 1);
        int[] is = SimplexNoiseSampler.GRADIENTS[o & 15];
        int[] js = SimplexNoiseSampler.GRADIENTS[p & 15];
        int[] ks = SimplexNoiseSampler.GRADIENTS[q & 15];
        int[] ls = SimplexNoiseSampler.GRADIENTS[r & 15];
        int[] ms = SimplexNoiseSampler.GRADIENTS[s & 15];
        int[] ns = SimplexNoiseSampler.GRADIENTS[t & 15];
        int[] os = SimplexNoiseSampler.GRADIENTS[u & 15];
        int[] ps = SimplexNoiseSampler.GRADIENTS[v & 15];
        double d = SimplexNoiseSampler.dot(is, localX, localY, localZ);
        double e = SimplexNoiseSampler.dot(js, localX - 1.0D, localY, localZ);
        double f = SimplexNoiseSampler.dot(ks, localX, localY - 1.0D, localZ);
        double g = SimplexNoiseSampler.dot(ls, localX - 1.0D, localY - 1.0D, localZ);
        double h = SimplexNoiseSampler.dot(ms, localX, localY, localZ - 1.0D);
        double w = SimplexNoiseSampler.dot(ns, localX - 1.0D, localY, localZ - 1.0D);
        double x = SimplexNoiseSampler.dot(os, localX, localY - 1.0D, localZ - 1.0D);
        double y = SimplexNoiseSampler.dot(ps, localX - 1.0D, localY - 1.0D, localZ - 1.0D);
        double z = MathHelper.perlinFade(localX);
        double aa = MathHelper.perlinFade(localY);
        double ab = MathHelper.perlinFade(localZ);
        double ac, ad, ae;
        if (shardFarlands) {
            ac = MathHelper.lerp3(z, aa, ab, (float)is[0], (float)js[0], (float)ks[0], (float)ls[0], (float)ms[0], (float)ns[0], (float)os[0], (float)ps[0]);
            ad = MathHelper.lerp3(z, aa, ab, (float)is[1], (float)js[1], (float)ks[1], (float)ls[1], (float)ms[1], (float)ns[1], (float)os[1], (float)ps[1]);
            ae = MathHelper.lerp3(z, aa, ab, (float)is[2], (float)js[2], (float)ks[2], (float)ls[2], (float)ms[2], (float)ns[2], (float)os[2], (float)ps[2]);
        } else {
            ac = MathHelper.lerp3(z, aa, ab, (double) is[0], (double) js[0], (double) ks[0], (double) ls[0], (double) ms[0], (double) ns[0], (double) os[0], (double) ps[0]);
            ad = MathHelper.lerp3(z, aa, ab, (double) is[1], (double) js[1], (double) ks[1], (double) ls[1], (double) ms[1], (double) ns[1], (double) os[1], (double) ps[1]);
            ae = MathHelper.lerp3(z, aa, ab, (double) is[2], (double) js[2], (double) ks[2], (double) ls[2], (double) ms[2], (double) ns[2], (double) os[2], (double) ps[2]);
        }
        double af = MathHelper.lerp2(aa, ab, e - d, g - f, w - h, y - x);
        double ag = MathHelper.lerp2(ab, z, f - d, x - h, g - e, y - w);
        double ah = MathHelper.lerp2(z, aa, h - d, w - e, x - f, y - g);
        double ai = MathHelper.perlinFadeDerivative(localX);
        double aj = MathHelper.perlinFadeDerivative(localY);
        double ak = MathHelper.perlinFadeDerivative(localZ);
        double al = ac + ai * af;
        double am = ad + aj * ag;
        double an = ae + ak * ah;
        ds[0] += al;
        ds[1] += am;
        ds[2] += an;
        return MathHelper.lerp3(z, aa, ab, d, e, f, g, h, w, x, y);
    }
}
