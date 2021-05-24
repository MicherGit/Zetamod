package dray.draydenspace.farlandsexplore.technicalblocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCrop1 extends CropBlock {
	// CropBlock cropBlock = cropBlock.getDefaultState();
	
    public BlockCrop1(Settings settings) {
        super(settings);
    }
 
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WHEAT.getDefaultState().with(this.getAgeProperty(), 1));
    }
}