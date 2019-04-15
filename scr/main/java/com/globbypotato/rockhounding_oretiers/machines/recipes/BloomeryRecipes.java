package com.globbypotato.rockhounding_oretiers.machines.recipes;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class BloomeryRecipes {

	private ItemStack input, output;
	private FluidStack molten;
	private String oredict;
	private boolean type;
	
	public BloomeryRecipes(@Nullable ItemStack inputStack, String oredict, boolean type, FluidStack fluidStack, ItemStack outputStack){
		this.input = inputStack;
		this.molten = fluidStack;
		this.output = outputStack;
		this.oredict = oredict;
		this.type = type;
	}

	public BloomeryRecipes(ItemStack inputStack, FluidStack fluidStack, ItemStack outputStack){
		this(inputStack, "", false, fluidStack, outputStack);
	}

	public BloomeryRecipes(String oredict, FluidStack fluidStack, ItemStack outputStack){
		this(ItemStack.EMPTY, oredict, true, fluidStack, outputStack);
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public String getOredict(){
		return this.oredict;
	}

	public boolean getType(){
		return this.type;
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