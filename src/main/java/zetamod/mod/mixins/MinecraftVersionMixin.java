package zetamod.mod.mixins;

import net.minecraft.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftVersion.class)
public class MinecraftVersionMixin {
    @Shadow
    private String name;

    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void modifyVersionTest(CallbackInfo ci) {
        this.name = name.concat(" test");
    }



}
