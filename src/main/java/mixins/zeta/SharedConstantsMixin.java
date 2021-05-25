package mixins.zeta;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zeta.zetamod.mod.managers.ConfigManager;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
    @Redirect(method = "<clinit>", at = @At(target = "Lnet/minecraft/SharedConstants;VERSION_NAME:Ljava/lang/String;", value = "INVOKE"))
    private static String fixVersion(String version) {
        return ConfigManager.getConfig().fakeVersion.getValue();
    }
}
