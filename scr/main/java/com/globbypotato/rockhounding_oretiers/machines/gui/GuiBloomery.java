package com.globbypotato.rockhounding_oretiers.machines.gui;

import java.util.Arrays;
import java.util.List;

import com.globbypotato.rockhounding_core.utils.RenderUtils;
import com.globbypotato.rockhounding_core.utils.Translator;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerBloomery;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class GuiBloomery extends GuiBase{
	private final InventoryPlayer playerInventory;
	private final TileEntityBloomery bloomery;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guibloomery.png");

	public GuiBloomery(InventoryPlayer playerInv, TileEntityBloomery tile){
		super(tile,new ContainerBloomery(playerInv, tile));
		this.playerInventory = playerInv;
		TEXTURE = TEXTURE_REF;
		this.bloomery = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
	}

	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String device = Translator.translateToLocal("container.bloomery");
		this.fontRendererObj.drawString(device, this.xSize / 2 - this.fontRendererObj.getStringWidth(device) / 2, 4, 4210752);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;
	   //bars progression (fuel-redstone)
	   if(mouseX >= 31+x && mouseX <= 40+x && mouseY >= 35+y && mouseY <= 62+y){
		   String[] text = {this.bloomery.powerCount + "/" + this.bloomery.powerMax + " ticks"};
		   List<String> tooltip = Arrays.asList(text);
		   drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
	   }
	   //Activation
	   if(mouseX >= 101+x && mouseX <= 118+x && mouseY >= 16+y && mouseY <= 33+y){
		   List<String> tooltip = Arrays.asList("Drain Fluid");
		   drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
	   }
	   //bloom tank
		if(mouseX>= 75+x && mouseX <= 100+x && mouseY >= 16+y && mouseY <= 81+y){
			int fluidAmount = 0; 
			if(bloomery.bloomTank.getFluid() != null){
				fluidAmount = this.bloomery.bloomTank.getFluidAmount();
				String[] text = {fluidAmount + "/" + this.bloomery.bloomTank.getCapacity() + " mb", this.bloomery.bloomTank.getFluid().getLocalizedName()};
				List<String> tooltip = Arrays.asList(text);
				drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
			}else{
				List<String> tooltip = Arrays.asList("Empty");
				drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
			}
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
		if(bloomery.bloomTank.getFluid() != null){
			FluidStack temp = bloomery.bloomTank.getFluid();
			int capacity = bloomery.bloomTank.getCapacity();
			if(temp.amount > 5){
				RenderUtils.bindBlockTexture();
				RenderUtils.renderGuiTank(temp,capacity, temp.amount, i + 76, j + 17, zLevel, 24, 64);
			}
		}

    }

}