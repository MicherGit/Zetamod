package zeta.zetamod.mod.features.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class CrashItem extends Item  {
    public CrashItem(Settings settings) {
        super(settings);
    }

    /**
     * @author ZetaTheEliatrope
     * @reason Why not?
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        itemStack.decrement(1);
        throw new Error("You crashed the game! Oh noes!");
    }


}
