package mixins.supercoder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.server.network.ServerPlayNetworkHandler;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
	/**
	 * @author SuperCoder79
	 * @reason more jd ah
	 */
	@Overwrite
	private static boolean validateVehicleMove(double d, double e, double f, float g, float h) {
		return false;
	}
}
