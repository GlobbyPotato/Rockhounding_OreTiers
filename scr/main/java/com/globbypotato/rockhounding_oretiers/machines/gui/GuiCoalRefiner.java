package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;
import com.globbypotato.rockhounding_oretiers.utils.Translator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoalRefiner extends GuiBase{
	private final InventoryPlayer playerInventory;
	private final TileEntityCoalRefiner coalRefiner;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;
	public static final ResourceLocation TEXTURE =  new ResourceLocation(Reference.MODID + ":textures/gui/guicoalrefiner.png");

	public GuiCoalRefiner(InventoryPlayer playerInv, TileEntityCoalRefiner tile){
		super(tile,new ContainerCoalRefiner(playerInv, tile));
		this.playerInventory = playerInv;
		BASE_TEXTURE = TEXTURE;
		this.coalRefiner = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
	}
    
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String device = Translator.translateToLocal("container.coalRefiner");
		this.fontRendererObj.drawString(device, this.xSize / 2 - this.fontRendererObj.getStringWidth(device) / 2, 4, 4210752);
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