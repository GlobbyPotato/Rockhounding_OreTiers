package com.globbypotato.rockhounding_oretiers.machines.renders;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RendererCoalRefiner extends TileEntitySpecialRenderer<TileEntityCoalRefiner>{
	private static EntityItem peat;

	@Override
	public void renderTileEntityAt(TileEntityCoalRefiner te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityCoalRefiner refiner = (TileEntityCoalRefiner)te;
		if(refiner != null){
			if(refiner.getInput().getStackInSlot(refiner.INPUT_SLOT) != null){
				ItemStack inputStack = new ItemStack(refiner.getInput().getStackInSlot(refiner.INPUT_SLOT).getItem(), 1, refiner.getInput().getStackInSlot(refiner.INPUT_SLOT).getItemDamage());
				int peatSize = refiner.getInput().getStackInSlot(refiner.INPUT_SLOT).stackSize;
				World world = Minecraft.getMinecraft().theWorld;
				int metadata = refiner.getBlockMetadata();
				if(inputStack != null){
					peat = new EntityItem(world, 0, 0, 0, inputStack);
					peat.hoverStart = 0;
					GlStateManager.pushMatrix();
					{
						GlStateManager.translate(x, y, z);
						GlStateManager.rotate(90F, 1, 0, 0);
						
						if(metadata == 2){
							GlStateManager.translate(0.50, 0.20, -0.13);
						}else if(metadata == 3){
							GlStateManager.translate(0.50, -0.15, -0.13);
						}else if(metadata == 4){
							GlStateManager.translate(0.70, 0.04, -0.13);
						}else if(metadata == 5){
							GlStateManager.translate(0.30, 0.04, -0.13);
						}
						Minecraft.getMinecraft().getRenderManager().doRenderEntity(peat, 0, 0, 0, 0F, 0F, false);
					}
					GlStateManager.popMatrix();
				}
			}
		}
	}
}