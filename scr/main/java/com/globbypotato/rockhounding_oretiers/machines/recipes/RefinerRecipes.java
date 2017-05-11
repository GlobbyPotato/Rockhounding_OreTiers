package com.globbypotato.rockhounding_oretiers.machines.recipes;

import net.minecraft.item.ItemStack;

public class RefinerRecipes {

	private ItemStack input;
	private ItemStack output;

	public RefinerRecipes(ItemStack input, ItemStack output){
		this.input = input;
		this.output = output;
	}

	public ItemStack getInput(){
		return this.input.copy();
	}

	public ItemStack getOutput() {
		return this.output.copy();
	}
}