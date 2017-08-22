package com.globbypotato.rockhounding_oretiers.machines.container;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBloomery extends ContainerBase<TileEntityBloomery> {

	public ContainerBloomery(InventoryPlayer playerInventory, TileEntityBloomery tile){
		super(playerInventory,tile);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = tile.getInput();
		IItemHandler output = tile.getOutput();
		IItemHandler template = tile.getTemplate();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 28, 17));//input
		this.addSlotToContainer(new SlotItemHandler(input, 1, 28, 65));//fuel
		this.addSlotToContainer(new SlotItemHandler(input, 2, 108, 47));//hammer

		this.addSlotToContainer(new SlotItemHandler(output, 0, 132, 65));//output

		this.addSlotToContainer(new SlotItemHandler(template, 0, 102,  17));//drain
	}

	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player){
		if(slot == 4){
    		this.tile.drainScan = !this.tile.drainScan; 
    		return null;
    	}else{
    		return super.slotClick(slot, dragType, clickTypeIn, player);
    	}
	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection){
		if(super.mergeItemStack(stack, startIndex, 4, reverseDirection)){
			return true;
		}else{
			return super.mergeItemStack(stack, 5, endIndex, reverseDirection);
		}
    }
}