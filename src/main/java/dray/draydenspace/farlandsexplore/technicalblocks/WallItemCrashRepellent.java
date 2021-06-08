package dray.draydenspace.farlandsexplore.technicalblocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class WallItemCrashRepellent extends BlockItem {
	private final Block block;
	public WallItemCrashRepellent(Block block, Item.Settings settings) {
	      super(block, settings);
	      this.block = block;
	}
	
	@Override @Nullable
	public String getTranslationKey() {
	      if(block == Blocks.BLACK_WALL_BANNER)
	      {
	    	  return "Black Wall Banner";
	      }
	      else if(block == Blocks.WHITE_WALL_BANNER)
	      {
	    	  return "White Wall Banner";
	      }
	      else if(block == Blocks.RED_WALL_BANNER)
	      {
	    	  return "Red Wall Banner";
	      }
	      else if(block == Blocks.BLUE_WALL_BANNER)
	      {
	    	  return "Blue Wall Banner";
	      }
	      else if(block == Blocks.CYAN_WALL_BANNER)
	      {
	    	  return "Cyan Wall Banner";
	      }
	      else if(block == Blocks.GREEN_WALL_BANNER)
	      {
	    	  return "Green Wall Banner";
	      }
	      else if(block == Blocks.GRAY_WALL_BANNER)
	      {
	    	  return "Gray Wall Banner";
	      }
	      else if(block == Blocks.PURPLE_WALL_BANNER)
	      {
	    	  return "Purple Wall Banner";
	      }
	      else if(block == Blocks.ORANGE_WALL_BANNER)
	      {
	    	  return "Orange Wall Banner";
	      }
	      else if(block == Blocks.YELLOW_WALL_BANNER)
	      {
	    	  return "Yellow Wall Banner";
	      }
	      else if(block == Blocks.MAGENTA_WALL_BANNER)
	      {
	    	  return "Magenta Wall Banner";
	      }
	      else if(block == Blocks.PINK_WALL_BANNER)
	      {
	    	  return "Pink Wall Banner";
	      }
	      else if(block == Blocks.LIGHT_BLUE_WALL_BANNER)
	      {
	    	  return "Light Blue Wall Banner";
	      }
	      else if(block == Blocks.LIGHT_GRAY_WALL_BANNER)
	      {
	    	  return "Light Gray Wall Banner";
	      }
	      else if(block == Blocks.LIME_WALL_BANNER)
	      {
	    	  return "Lime Wall Banner";
	      }
	      else if(block == Blocks.BROWN_WALL_BANNER)
	      {
	    	  return "Brown Wall Banner";
	      }
	      else if(block == Blocks.DARK_OAK_WALL_SIGN)
	      {
	    	  return "Dark Oak Wall Sign";
	      }
	      else
	      {
	    	  return null;
	      }
	}
}
