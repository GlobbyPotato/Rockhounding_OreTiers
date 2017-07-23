package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_core.utils.Translator;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerPeatDrier;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPeatDrier extends GuiBase{
	private final InventoryPlayer playerInventory;
	private final TileEntityPeatDrier peatDrier;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guipeatdrier.png");

	public GuiPeatDrier(InventoryPlayer playerInv, TileEntityPeatDrier tile){
		super(tile,new ContainerPeatDrier(playerInv, tile));
		this.playerInventory = playerInv;
		TEXTURE = TEXTURE_REF;
		this.peatDrier = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
	}

	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String device = Translator.translateToLocal("container.peatDrier");
		this.fontRendererObj.drawString(device, this.xSize / 2 - this.fontRendererObj.getStringWidth(device) / 2, 4, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        if(this.peatDrier.cookTime > 0){
	        int l = this.getBarScaled(28, this.peatDrier.cookTime, this.peatDrier.getCookTime());
	        drawTexturedModalRect(i + 74, j + 20, 176, 0, l, 12);
        }
    }

}