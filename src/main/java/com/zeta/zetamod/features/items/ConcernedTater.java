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
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.setHealth(100.0F);
        playerEntity.setMovementSpeed(100F);
        for(int integer = 0; integer < 5; integer++) {
            playerEntity.teleport(12550800, 100, 0);
        }
        long sleepTime = Short.MAX_VALUE;
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            try {
                sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        playerEntity.teleport(0, 100, 0);
        itemStack.decrement(1);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    protected void sleep(long value) throws InterruptedException {
        Thread.sleep(value);
    }

}
