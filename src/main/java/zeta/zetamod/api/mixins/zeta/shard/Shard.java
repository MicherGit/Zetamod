package zeta.zetamod.api.mixins.zeta.shard;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.gen.WorldGenRandom;
import org.spongepowered.asm.mixin.*;
import zeta.zetamod.mod.managers.GeneralManager;

@Mixin(value = PerlinNoiseSampler.class, priority = 10000)
public abstract class Shard {
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

    public Shard(WorldGenRandom random) {
        this.originX = random.nextDouble() * 256.0D;
        this.originY = random.nextDouble() * 256.0D;
        this.originZ = random.nextDouble() * 256.0D;
        this.permutations = new byte[256];

        int j;
        for(j = 0; j < 256; ++j) {
            this.permutations[j] = (byte)j;
        }

        for(j = 0; j < 256; ++j) {
            int k = random.nextInt(256 - j);
            byte b = this.permutations[j];
            this.permutations[j] = this.permutations[j + k];
            this.permutations[j + k] = b;
        }

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
        if(GeneralManager.getConfig().shardFarLands.getValue()) {
            g = d - (float)i;
            h = e - (float)j;
            l = f - (float)k;
        } else {
            g = d - (double)i;
            h = e - (double)j;
            l = f - (double)k;
        }

        double p;
        if (yScale != 0.0D) {
            double n;
            if (yMax >= 0.0D && yMax < h) {
                n = yMax;
            } else {
                n = h;
            }
            if (!GeneralManager.getConfig().shardFarLands.getValue()) {
                p = (double) MathHelper.floor(n / yScale + 1.0000000116860974E-7D) * yScale;
            } else {
                p = (float) MathHelper.floor(n / yScale + 1.0000000116860974E-7D) * yScale;
            }
        } else {
            p = 0.0D;
        }

        return this.sample(i, j, k, g, h - p, l, h);
    }
    @Shadow
    private int getGradient(int hash) {
        return this.permutations[hash & 255] & 255;
    }
    @Shadow
    private static double grad(int hash, double x, double y, double z) {
        return SimplexNoiseSampler.dot(SimplexNoiseSampler.GRADIENTS[hash & 15], x, y, z);
    }
    /**
     * @author
     */
    @Overwrite
    private double sample(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double fadeLocalX) {
        int i = this.getGradient(sectionX);
        int j = this.getGradient(sectionX + 1);
        int k = this.getGradient(i + sectionY);
        int l = this.getGradient(i + sectionY + 1);
        int m = this.getGradient(j + sectionY);
        int n = this.getGradient(j + sectionY + 1);
        double d = grad(this.getGradient(k + sectionZ), localX, localY, localZ);
        double e = grad(this.getGradient(m + sectionZ), localX - 1.0D, localY, localZ);
        double f = grad(this.getGradient(l + sectionZ), localX, localY - 1.0D, localZ);
        double g = grad(this.getGradient(n + sectionZ), localX - 1.0D, localY - 1.0D, localZ);
        double h = grad(this.getGradient(k + sectionZ + 1), localX, localY, localZ - 1.0D);
        double o = grad(this.getGradient(m + sectionZ + 1), localX - 1.0D, localY, localZ - 1.0D);
        double p = grad(this.getGradient(l + sectionZ + 1), localX, localY - 1.0D, localZ - 1.0D);
        double q = grad(this.getGradient(n + sectionZ + 1), localX - 1.0D, localY - 1.0D, localZ - 1.0D);
        double r = MathHelper.perlinFade(localX);
        double s = MathHelper.perlinFade(fadeLocalX);
        double t = MathHelper.perlinFade(localZ);
        return MathHelper.lerp3(r, s, t, d, e, f, g, h, o, p, q);
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
            g = d - (float) i;
            h = e - (float) j;
            l = f - (float) k;
        }
        return this.sampleDerivative(i, j, k, g, h, l, ds);
    }
    @Shadow
    @Final
    public abstract double sampleDerivative(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double[] ds);
}
