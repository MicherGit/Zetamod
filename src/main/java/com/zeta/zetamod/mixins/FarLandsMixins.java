package com.zeta.zetamod.mixins;

import com.zeta.zetamod.features.ZetaModInitializer;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OctavePerlinNoiseSampler.class)
public class FarLandsMixins {
	/**
	 * @author ZetaTheEliatrope
	 * @reason Adds back the farlands.
	 */
	@Overwrite
	public static double maintainPrecision(double d) {

		return d;
	}
}
