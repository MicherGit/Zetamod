package com.zeta.zetamod.mixins;

import net.minecraft.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftVersion.class)
public class VersionMixin {
    @Shadow
    private final String name;

    public VersionMixin(String name) {
        this.name = name + " + Zeta's Patches";
    }
}
