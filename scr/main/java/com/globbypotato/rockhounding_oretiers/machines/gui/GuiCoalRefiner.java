package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoalRefiner extends GuiBase{
	private final TileEntityCoalRefiner coalRefiner;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guicoalrefiner.png");

	public GuiCoalRefiner(InventoryPlayer playerInv, TileEntityCoalRefiner tile){
		super(new ContainerCoalRefiner(playerInv, tile));
		TEXTURE = TEXTURE_REF;
		this.coalRefiner = tile;
		this.containerName =  "container." + this.coalRefiner.getName();
	}
    
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if(this.coalRefiner.getCooktime() > 0){
	        int l = GuiUtils.getScaledValue(28, this.coalRefiner.getCooktime(), this.coalRefiner.getCooktimeMax());
	        drawTexturedModalRect(i + 74, j + 40, 176, 0, l, 12);
        }

        if(this.coalRefiner.isHeated()){
            drawTexturedModalRect(i + 75, j + 69, 176, 15, 25, 17);
        }
    }

}