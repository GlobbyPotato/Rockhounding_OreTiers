package com.globbypotato.rockhounding_oretiers.machines.recipes;

import net.minecraft.item.ItemStack;

public class RefinerRecipes {

	private ItemStack input, output;
	private int refining;
	private String oredict;
	private boolean type;

	public RefinerRecipes(ItemStack input, String oredict, boolean type, ItemStack output, int refining){
		this.input = input;
		this.output = output;
		this.refining = refining;
		this.oredict = oredict;
		this.type = type;
	}

	public RefinerRecipes(ItemStack input, ItemStack output, int refining){
		this(input, "", false, output, refining);
	}

	public RefinerRecipes(String oredict, ItemStack output, int refining){
		this(ItemStack.EMPTY, oredict, true, output, refining);
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

	public ItemStack getOutput() {
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}
	
	public int getRefining(){
		return this.refining;
	}
}