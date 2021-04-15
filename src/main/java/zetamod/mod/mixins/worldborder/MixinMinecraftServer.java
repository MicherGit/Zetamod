package zetamod.mod.mixins.worldborder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
	/**
	 * @author SuperCoder79
	 * @reason SHUT UP SHUT UP SHUT UP!!! JUST STFU WHY WON'T YA, WHY DO I HAVE TO GIVE A FREAKING REASON!?!?! -Zeta
	 */
	@Overwrite
	public int getMaxWorldBorderRadius() {
		return Integer.MAX_VALUE;
	}
}
