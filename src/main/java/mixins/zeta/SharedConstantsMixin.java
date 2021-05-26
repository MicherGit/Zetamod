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

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
    /*@Mutable
    @Shadow @Final @Deprecated public static String VERSION_NAME;

    @Inject(method = "<clinit>", at = @At("INVOKE"))
    private static void handleConstructor(CallbackInfo ci) {
        //VERSION_NAME = "1.17";
    }*/
}
