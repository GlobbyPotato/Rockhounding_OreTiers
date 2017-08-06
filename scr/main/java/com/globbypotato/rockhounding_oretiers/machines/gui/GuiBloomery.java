package com.globbypotato.rockhounding_oretiers.machines.gui;

import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerBloomery;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

public class GuiBloomery extends GuiBase{
	private final InventoryPlayer playerInventory;
	private final TileEntityBloomery bloomery;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guibloomery.png");
	private FluidTank bloomTank;

	public GuiBloomery(InventoryPlayer playerInv, TileEntityBloomery tile){
		super(tile,new ContainerBloomery(playerInv, tile));
		this.playerInventory = playerInv;
		TEXTURE = TEXTURE_REF;
		this.bloomery = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
		this.bloomTank = this.bloomery.bloomTank;
		this.containerName = "container.bloomery";
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

	   //fuel
	   if(mouseX >= 31+x && mouseX <= 40+x && mouseY >= 35+y && mouseY <= 62+y){
			drawPowerInfo("ticks", this.bloomery.getPower(), this.bloomery.getPowerMax(), mouseX, mouseY);
	   }

	   //Activation
	   if(mouseX >= 101+x && mouseX <= 118+x && mouseY >= 16+y && mouseY <= 33+y){
			drawButtonLabel("Activation", mouseX, mouseY);
	   }

	   //bloom tank
		if(mouseX>= 75+x && mouseX <= 100+x && mouseY >= 16+y && mouseY <= 81+y){
			drawTankInfo(this.bloomTank, mouseX, mouseY);
		}
    }

	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        //power bar
        if (this.bloomery.powerCount > 0){
            int k = this.getBarScaled(28, this.bloomery.powerCount, this.bloomery.powerMax);
            this.drawTexturedModalRect(i + 31, j + 35 + (28 - k), 176, 24, 10, k);
        }

        //smelt bar
        if (this.bloomery.cookTime > 0){
            int k = this.getBarScaled(28, this.bloomery.cookTime, ModConfig.bloomingSpeed);
            this.drawTexturedModalRect(i + 46, j + 19, 176, 12, k, 12);
        }

        //cast bar
        if (this.bloomery.castTime > 0){
            int k = this.getBarScaled(28, this.bloomery.castTime, ModConfig.bloomingSpeed);
            this.drawTexturedModalRect(i + 102, j + 67, 176, 0, k, 12);
        }

        //activations
        if(this.bloomery.drainScan){
            this.drawTexturedModalRect(i + 101, j + 16, 176, 52, 18, 18);
        }

		//tank bar
		if(this.bloomTank.getFluid() != null){
			renderFluidBar(this.bloomTank.getFluid(), this.bloomTank.getCapacity(), i + 76, j + 17, 24, 64);
		}

    }

}