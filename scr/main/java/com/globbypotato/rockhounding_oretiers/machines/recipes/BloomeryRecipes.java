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
		return this.input.copy();
	}

	public FluidStack getMolten(){
		return this.molten;
	}

	public ItemStack getOutput() {
		return this.output.copy();
	}

}