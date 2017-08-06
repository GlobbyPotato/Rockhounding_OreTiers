package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoalRefiner extends GuiBase{
	private final InventoryPlayer playerInventory;
	private final TileEntityCoalRefiner coalRefiner;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guicoalrefiner.png");

	public GuiCoalRefiner(InventoryPlayer playerInv, TileEntityCoalRefiner tile){
		super(tile,new ContainerCoalRefiner(playerInv, tile));
		this.playerInventory = playerInv;
		TEXTURE = TEXTURE_REF;
		this.coalRefiner = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
		this.containerName = "container.coalRefiner";
	}
    
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if(this.coalRefiner.cookTime > 0){
	        int l = this.getBarScaled(28, this.coalRefiner.cookTime, this.coalRefiner.getCookTime());
	        drawTexturedModalRect(i + 74, j + 20, 176, 0, l, 12);
        }

        if(this.coalRefiner.isHeated()){
            drawTexturedModalRect(i + 75, j + 49, 176, 15, 25, 17);
        }
    }

}