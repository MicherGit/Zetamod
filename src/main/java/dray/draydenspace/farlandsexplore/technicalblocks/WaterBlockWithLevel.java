package dray.draydenspace.farlandsexplore.technicalblocks;

import org.jetbrains.annotations.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterBlockWithLevel extends FluidBlock {
	
	public WaterBlockWithLevel(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 15));
    }
}
class WaterBlockWithLevel1 extends FluidBlock {
	
	public WaterBlockWithLevel1(Settings settings) {
        super(Fluids.WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 1));
    }
}
class WaterBlockWithLevel2 extends FluidBlock {
	
	public WaterBlockWithLevel2(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 2));
    }
}
class WaterBlockWithLevel3 extends FluidBlock {
	
	public WaterBlockWithLevel3(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 3));
    }
}
class WaterBlockWithLevel4 extends FluidBlock {
	
	public WaterBlockWithLevel4(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 4));
    }
}
class WaterBlockWithLevel5 extends FluidBlock {
	
	public WaterBlockWithLevel5(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 5));
    }
}
class WaterBlockWithLevel6 extends FluidBlock {
	
	public WaterBlockWithLevel6(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 6));
    }
}
class WaterBlockWithLevel7 extends FluidBlock {
	
	public WaterBlockWithLevel7(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 7));
    }
}
class WaterBlockWithLevel8 extends FluidBlock {
	
	public WaterBlockWithLevel8(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 8));
    }
}
class WaterBlockWithLevel9 extends FluidBlock {
	
	public WaterBlockWithLevel9(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 9));
    }
}
class WaterBlockWithLevel10 extends FluidBlock {
	
	public WaterBlockWithLevel10(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 10));
    }
}
class WaterBlockWithLevel11 extends FluidBlock {
	
	public WaterBlockWithLevel11(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 11));
    }
}
class WaterBlockWithLevel12 extends FluidBlock {
	
	public WaterBlockWithLevel12(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 12));
    }
}
class WaterBlockWithLevel13 extends FluidBlock {
	
	public WaterBlockWithLevel13(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 13));
    }
}
class WaterBlockWithLevel14 extends FluidBlock {
	
	public WaterBlockWithLevel14(Settings settings) {
        super(Fluids.FLOWING_WATER, settings);
    }
	
	@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    	world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockState)Blocks.WATER.getDefaultState().with(WaterBlockWithLevel.LEVEL, 14));
    }
}