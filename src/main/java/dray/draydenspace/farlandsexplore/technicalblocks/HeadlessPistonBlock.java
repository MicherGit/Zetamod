package dray.draydenspace.farlandsexplore.technicalblocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeadlessPistonBlock extends PistonBlock {
	public HeadlessPistonBlock(Settings settings) {
        super(false, settings);
    }
 
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.PISTON.getDefaultState().with(PistonBlock.EXTENDED, true));
    }
}
