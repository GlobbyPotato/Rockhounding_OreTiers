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
		IItemHandler input = tile.getInput();
		IItemHandler output = tile.getOutput();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 56, 18));//input
		this.addSlotToContainer(new SlotItemHandler(output, 0, 104, 18));//output
	}
}