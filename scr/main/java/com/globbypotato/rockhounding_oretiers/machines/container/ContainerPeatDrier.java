package com.globbypotato.rockhounding_oretiers.machines.container;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPeatDrier extends ContainerBase<TileEntityPeatDrier> {
	public ContainerPeatDrier(InventoryPlayer playerInventory, TileEntityPeatDrier tile){
		super(playerInventory,tile);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = this.tile.getInput();
		IItemHandler output = this.tile.getOutput();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 56, 38));//input
		this.addSlotToContainer(new SlotItemHandler(output, 0, 104, 38));//output
	}
}