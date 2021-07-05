package zeta.zetaforged.mod.features.blocks;

import net.minecraft.item.ItemPlacementContext;
import net.minecraft.block.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;


public class VoidBlock extends Block {
    public VoidBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(0.0F));
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return true;
    }


}
