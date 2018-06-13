package com.globbypotato.rockhounding_oretiers.machines.gui;

import java.util.List;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerBloomery;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiBloomery extends GuiBase{
	private final TileEntityBloomery bloomery;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guibloomery.png");

	public GuiBloomery(InventoryPlayer playerInv, TileEntityBloomery tile){
		super(new ContainerBloomery(playerInv, tile));
		TEXTURE = TEXTURE_REF;
		this.bloomery = tile;
		this.containerName =  "container." + this.bloomery.getName();
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

	   //fuel
	   if(GuiUtils.hoveringArea(31, 55, 10, 28, mouseX, mouseY, x, y)){
		   List<String> tooltip = GuiUtils.drawStorage(TextFormatting.GOLD, "ticks", TextFormatting.YELLOW, 0, this.bloomery.getPower(), this.bloomery.getPowerMax(), mouseX, mouseY);
		   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
	   }

	   //activation
	   if(GuiUtils.hoveringArea(101, 36, 18, 18, mouseX, mouseY, x, y)){
		   List<String> tooltip = GuiUtils.drawLabel("Drain fluids for external uses", mouseX, mouseY);
		   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
	   }

	   //bloom tank
	    if(GuiUtils.hoveringArea(75, 36, 26, 66, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawFluidTankInfo(this.bloomery.bloomTank, mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}
    }

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        //power bar
        if (this.bloomery.powerCount > 0){
            int k = GuiUtils.getScaledValue(28, this.bloomery.powerCount, this.bloomery.powerMax);
            this.drawTexturedModalRect(i + 31, j + 55 + (28 - k), 176, 24, 10, k);
        }

        //smelt bar
        if (this.bloomery.getCooktime() > 0){
            int k = GuiUtils.getScaledValue(28, this.bloomery.getCooktime(), this.bloomery.getCooktimeMax());
            this.drawTexturedModalRect(i + 46, j + 39, 176, 12, k, 12);
        }

        //cast bar
        if (this.bloomery.castTime > 0){
            int k = GuiUtils.getScaledValue(28, this.bloomery.castTime, this.bloomery.getCooktimeMax());
            this.drawTexturedModalRect(i + 102, j + 87, 176, 0, k, 12);
        }

        //activations
        if(this.bloomery.drainScan){
            this.drawTexturedModalRect(i + 101, j + 36, 176, 52, 18, 18);
        }

		//tank bar
		if(this.bloomery.bloomTank.getFluid() != null){
			GuiUtils.renderFluidBar(this.bloomery.bloomTank.getFluid(), this.bloomery.bloomTank.getFluidAmount(), this.bloomery.bloomTank.getCapacity(), i + 76, j + 37, 24, 64);
		}

    }

}