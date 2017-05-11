package com.globbypotato.rockhounding_oretiers.machines.renders;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RendererPeatDrier extends TileEntitySpecialRenderer<TileEntityPeatDrier>{
	private static EntityItem peat;

	@Override
	public void renderTileEntityAt(TileEntityPeatDrier te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityPeatDrier drier = (TileEntityPeatDrier)te;
		if(drier != null){
			if(drier.getInput().getStackInSlot(drier.INPUT_SLOT) != null){
				ItemStack inputStack = new ItemStack(drier.getInput().getStackInSlot(drier.INPUT_SLOT).getItem(), 1, drier.getInput().getStackInSlot(drier.INPUT_SLOT).getItemDamage());
				int peatSize = drier.getInput().getStackInSlot(drier.INPUT_SLOT).stackSize;
				World world = Minecraft.getMinecraft().theWorld;
	
				if(inputStack != null){
					peat = new EntityItem(world, 0, 0, 0, inputStack);
					peat.hoverStart = 0;
					GlStateManager.pushMatrix();
					{
						GlStateManager.translate(x, y, z);
						GlStateManager.rotate(90F, 1, 0, 0);
						GlStateManager.scale(0.5, 0.5, 0.5);
						GlStateManager.translate(-0.25, -0.25, -0.3);
						int i = 0; int j = 0; int k = 1;
						float gapX = 0F;
						float gapZ = 0F;
						int slotSize = peatSize/4;
						if(slotSize < 1) slotSize = 1;
						for(int size = 0; size < slotSize; size++){
							if(i < 4){
								i++;
								gapX = 0.5F;
								gapZ = 0.0F;
							}else{
								if(k < 4){
									GlStateManager.translate(-1.5F, 0, 0);
									i = 1;
									k++;
									gapX = 0.0F;
									gapZ = 0.5F;
								}else{
									GlStateManager.translate(0, -1.5F, 0);
									GlStateManager.translate(-1.5F, 0, 0);
									k = 1;
									i = 1;
									gapX = 0.0F;
									gapZ = 0.0F;
								}
							}
							GlStateManager.translate(gapX, gapZ, 0);
							Minecraft.getMinecraft().getRenderManager().doRenderEntity(peat, 0, 0, 0, 0F, 0F, false);
						}
					}
					GlStateManager.popMatrix();
				}
			}
		}
	}
}