package com.zeta.zetamod.features.items;

import net.minecraft.client.render.SkyProperties;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.minecraft.entity.effect.StatusEffects.NIGHT_VISION;

public class ConcernedTater extends Item {
    public ConcernedTater(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.setHealth(100.0F);
        playerEntity.clearActiveItem();

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

}
