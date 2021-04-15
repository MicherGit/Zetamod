package zetamod.mod.mixins.idkkindasustomebruh.alittlesomething.called.a.hehe;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GoatEntity.class)
public class EasterEggClass {
    @Inject(method = "<init>", at = @At("RETURN"))
    private static void hopesAndDreams(EntityType<GoatEntity> entityType, World world, CallbackInfo ci) {
        if ("Asriel".equals(entityType.getName().asString())) {
        }
    }
}
