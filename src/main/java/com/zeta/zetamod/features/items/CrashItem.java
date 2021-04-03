package com.zeta.zetamod.features.items;

import com.zeta.zetamod.features.ZetaModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;


public class CrashItem extends Item  {
    public CrashItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        itemStack.decrement(1);
        throw new Error("You crashed the game! Oh noes!");
    }


}
