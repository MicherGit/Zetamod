package zetamod.mod.mixins.worldborder;

import net.minecraft.class_6350;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.util.math.MathHelper;

@Mixin(class_6350.AquiferSampler.class)
public class MixinAquiferSampler {
	@Shadow @Final private int startX;

	@Shadow @Final private int startY;

	@Shadow @Final private int startZ;

	@Shadow @Final private int sizeX;

	@Shadow @Final private int sizeZ;

	@Shadow @Final private int[] waterLevels;

	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	private int index(int x, int y, int z) {
		int i = x - this.startX;
		int j = y - this.startY;
		int k = z - this.startZ;
		int dx = (j * this.sizeZ + k) * this.sizeX + i;
		return MathHelper.clamp(dx, 0, this.waterLevels.length - 1);
	}
}
