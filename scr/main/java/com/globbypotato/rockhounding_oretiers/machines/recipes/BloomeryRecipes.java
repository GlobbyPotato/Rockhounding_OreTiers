package com.globbypotato.rockhounding_oretiers.machines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class BloomeryRecipes {

	private ItemStack input;
	private FluidStack molten;
	private ItemStack output;

	public BloomeryRecipes(ItemStack inputStack, FluidStack fluidStack, ItemStack outputStack){
		this.input = inputStack;
		this.molten = fluidStack;
		this.output = outputStack;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public FluidStack getMolten(){
		if(this.molten != null) return this.molten.copy();
		return null;
	}

	public ItemStack getOutput() {
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}

}