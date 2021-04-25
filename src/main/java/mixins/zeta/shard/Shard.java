package mixins.zeta.shard;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.*;
import code.zeta.zetamod.mod.managers.GeneralManager;

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
    private int getGradient(int hash) {
        return this.permutations[hash & 255] & 255;
    }
    /**
     * @author
     */
    @Overwrite
    private double sampleDerivative(int i, int j, int k, double d, double e, double f, double[] ds) {
        int l = this.getGradient(i);
        int m = this.getGradient(i + 1);
        int n = this.getGradient(l + j);
        int o = this.getGradient(l + j + 1);
        int p = this.getGradient(m + j);
        int q = this.getGradient(m + j + 1);
        int r = this.getGradient(n + k);
        int s = this.getGradient(p + k);
        int t = this.getGradient(o + k);
        int u = this.getGradient(q + k);
        int v = this.getGradient(n + k + 1);
        int w = this.getGradient(p + k + 1);
        int x = this.getGradient(o + k + 1);
        int y = this.getGradient(q + k + 1);
        int[] is = SimplexNoiseSampler.GRADIENTS[r & 15];
        int[] js = SimplexNoiseSampler.GRADIENTS[s & 15];
        int[] ks = SimplexNoiseSampler.GRADIENTS[t & 15];
        int[] ls = SimplexNoiseSampler.GRADIENTS[u & 15];
        int[] ms = SimplexNoiseSampler.GRADIENTS[v & 15];
        int[] ns = SimplexNoiseSampler.GRADIENTS[w & 15];
        int[] os = SimplexNoiseSampler.GRADIENTS[x & 15];
        int[] ps = SimplexNoiseSampler.GRADIENTS[y & 15];
        double g = SimplexNoiseSampler.dot(is, d, e, f);
        double h = SimplexNoiseSampler.dot(js, d - 1.0D, e, f);
        double z = SimplexNoiseSampler.dot(ks, d, e - 1.0D, f);
        double aa = SimplexNoiseSampler.dot(ls, d - 1.0D, e - 1.0D, f);
        double ab = SimplexNoiseSampler.dot(ms, d, e, f - 1.0D);
        double ac = SimplexNoiseSampler.dot(ns, d - 1.0D, e, f - 1.0D);
        double ad = SimplexNoiseSampler.dot(os, d, e - 1.0D, f - 1.0D);
        double ae = SimplexNoiseSampler.dot(ps, d - 1.0D, e - 1.0D, f - 1.0D);
        double af = MathHelper.perlinFade(d);
        double ag = MathHelper.perlinFade(e);
        double ah = MathHelper.perlinFade(f);
        double ai = MathHelper.lerp3(af, ag, ah, (float)is[0], (float)js[0], (float)ks[0], (float)ls[0], (float)ms[0], (float)ns[0], (float)os[0], (float)ps[0]);
        double aj = MathHelper.lerp3(af, ag, ah, (float)is[1], (float)js[1], (float)ks[1], (float)ls[1], (float)ms[1], (float)ns[1], (float)os[1], (float)ps[1]);
        double ak = MathHelper.lerp3(af, ag, ah, (float)is[2], (float)js[2], (float)ks[2], (float)ls[2], (float)ms[2], (float)ns[2], (float)os[2], (float)ps[2]);
        double al = MathHelper.lerp2(ag, ah, h - g, aa - z, ac - ab, ae - ad);
        double am = MathHelper.lerp2(ah, af, z - g, ad - ab, aa - h, ae - ac);
        double an = MathHelper.lerp2(af, ag, ab - g, ac - h, ad - z, ae - aa);
        double ao = MathHelper.perlinFadeDerivative(d);
        double ap = MathHelper.perlinFadeDerivative(e);
        double aq = MathHelper.perlinFadeDerivative(f);
        double ar = ai + ao * al;
        double as = aj + ap * am;
        double at = ak + aq * an;
        ds[0] += ar;
        ds[1] += as;
        ds[2] += at;
        return MathHelper.lerp3(af, ag, ah, g, h, z, aa, ab, ac, ad, ae);
    }
}
