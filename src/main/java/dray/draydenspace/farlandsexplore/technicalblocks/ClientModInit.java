package dray.draydenspace.farlandsexplore.technicalblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ClientModInit implements ClientModInitializer {
	public static final EndPortalBlockEntity blockEntity = new EndPortalBlockEntity(BlockPos.ORIGIN, Blocks.END_PORTAL.getDefaultState());
	public DynamicItemRenderer dynamicItemRenderer = (ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) -> {};
	
	@Override
	public void onInitializeClient() {
		  BuiltinItemRendererRegistry.INSTANCE.register(TechnicalBlocks.ItemEndPortal, dynamicItemRenderer);
	}
}
