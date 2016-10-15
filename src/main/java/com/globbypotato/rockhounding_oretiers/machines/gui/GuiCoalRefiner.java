package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiCoalRefiner extends GuiContainer{
    private static final ResourceLocation COALREFINER_TEXTURES = new ResourceLocation(Reference.MODID + ":" + "textures/gui/guicoalrefiner.png");
    private final InventoryPlayer playerInventory;
    private final TileEntityCoalRefiner coalRefiner;

    public GuiCoalRefiner(InventoryPlayer playerInv, TileEntityCoalRefiner furnaceInv){
        super(new ContainerCoalRefiner(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.coalRefiner = furnaceInv;
		this.xSize = 176;
		this.ySize = 178;
    }
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		String s = coalRefiner.hasCustomName() ? coalRefiner.getName() : I18n.format(coalRefiner.getName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(COALREFINER_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int l = this.getCookProgressScaled(28);
        drawTexturedModalRect(i + 74, j + 20, 176, 0, l, 12);
        
        if(this.coalRefiner.isHeated()){
            drawTexturedModalRect(i + 75, j + 49, 176, 15, 25, 17);
        }
    }

    private int getCookProgressScaled(int pixels){
        int i = this.coalRefiner.getField(0);
        int j = this.coalRefiner.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

}
