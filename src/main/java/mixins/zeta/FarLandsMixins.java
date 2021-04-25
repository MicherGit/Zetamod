package mixins.zeta;

import code.zeta.zetamod.mod.managers.GeneralManager;
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

		return GeneralManager.FarLandsManager.
				maintainPrecisionManageable(d);
	}
}
