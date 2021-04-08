package com.zeta.zetamod.mixins;

import net.minecraft.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftVersion.class)
public class MinecraftVersionMixin {
    @Shadow
    private final String name;

    public MinecraftVersionMixin(String name) {
        this.name = name;
        minecraftversionname = this.name;
    }

    public String minecraftversionname;
}
