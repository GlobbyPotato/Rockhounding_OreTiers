package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerPeatDrier;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPeatDrier extends GuiBase{
	private final TileEntityPeatDrier peatDrier;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guipeatdrier.png");

	public GuiPeatDrier(InventoryPlayer playerInv, TileEntityPeatDrier tile){
		super(new ContainerPeatDrier(playerInv, tile));
		TEXTURE = TEXTURE_REF;
		this.peatDrier = tile;
		this.containerName =  "container." + this.peatDrier.getName();
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        if(this.peatDrier.getCooktime() > 0){
	        int l = GuiUtils.getScaledValue(28, this.peatDrier.getCooktime(), this.peatDrier.getCooktimeMax());
	        drawTexturedModalRect(i + 74, j + 40, 176, 0, l, 12);
        }
    }

}