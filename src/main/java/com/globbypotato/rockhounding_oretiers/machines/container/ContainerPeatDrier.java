package com.globbypotato.rockhounding_oretiers.machines.container;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerPeatDrier extends Container {
	private TileEntityPeatDrier peatDrier;
    private int cookTime;
    private int totalCookTime;

    public ContainerPeatDrier(InventoryPlayer playerInventory, TileEntityPeatDrier furnaceInventory){
        this.peatDrier = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 18));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 1, 104, 18));

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 96 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k){
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 154));
        }

    }

    @Override
    public void addListener(IContainerListener listener){
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.peatDrier);
    }

    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i){
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.cookTime != this.peatDrier.getField(0)){
                icontainerlistener.sendProgressBarUpdate(this, 0, this.peatDrier.getField(0));
            }
            if (this.totalCookTime != this.peatDrier.getField(1)){
                icontainerlistener.sendProgressBarUpdate(this, 1, this.peatDrier.getField(1));
            }
        }
        this.cookTime = this.peatDrier.getField(0);
        this.totalCookTime = this.peatDrier.getField(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data){
        this.peatDrier.setField(id, data);
    }

	@Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return this.peatDrier.isUseableByPlayer(playerIn);
    }

    @Nullable
    @Override
   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
        return null;
    }


}
