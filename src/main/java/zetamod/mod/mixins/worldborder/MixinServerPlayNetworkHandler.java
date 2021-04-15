package zetamod.mod.mixins.worldborder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
	/**
	 * @author SuperCoder79
	 * @reason SHUT UP SHUT UP SHUT UP!!! JUST STFU WHY WON'T YA, WHY DO I HAVE TO GIVE A FREAKING REASON!?!?! -Zeta
	 */
	@Overwrite
	private static boolean validateVehicleMove(double d, double e, double f, float g, float h) {
		return false;
	}

	/*
	 * Original
	 *     private static boolean validateVehicleMove(double d, double e, double f, float g, float h) {
	 *         return Double.isNaN(d) || Double.isNaN(e) || Double.isNaN(f) || !Floats.isFinite(h) || !Floats.isFinite(g);
	 *     }
	 */
}
