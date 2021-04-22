package zetamod.mod.mixins.worldborder;

import me.sargunvohra.mcmods.dataloader.DataLoader;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

	/**
	 * @author sargunv
	 * @param resourcePackManager
	 * @param dataPackSettings
	 * @param safeMode
	 * @param cir
	 */
	@Inject(method = "loadDataPacks", at = @At(value = "HEAD"))
	private static void loadDataPacks(
			ResourcePackManager resourcePackManager, DataPackSettings dataPackSettings, boolean safeMode, CallbackInfoReturnable<DataPackSettings> cir
	) {
		resourcePackManager.providers.add(
				new FileResourcePackProvider(
						DataLoader.DATAPACKS_PATH.toFile(),
						DataLoader.RESOURCE_PACK_SOURCE
				)
		);
	}
}
