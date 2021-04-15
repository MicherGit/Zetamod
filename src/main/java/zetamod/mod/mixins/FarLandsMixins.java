package zetamod.mod.mixins;

import zetamod.mod.features.GeneralManager;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
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

		return GeneralManager.maintainPrecisionManageable(d);
	}
}
