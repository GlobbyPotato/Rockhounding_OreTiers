package com.globbypotato.rockhounding_oretiers.machines.recipes;

import net.minecraft.item.ItemStack;

public class DrierRecipes {

	private ItemStack input, output;
	private int refining;
	boolean oredict;

	public DrierRecipes(ItemStack input, boolean oredict, ItemStack output, int refining){
		this.input = input;
		this.output = output;
		this.refining = refining;
		this.oredict = oredict;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public boolean canOredict(){
		return this.oredict;
	}

	public ItemStack getOutput() {
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}

	public int getRefining(){
		return this.refining;
	}

}