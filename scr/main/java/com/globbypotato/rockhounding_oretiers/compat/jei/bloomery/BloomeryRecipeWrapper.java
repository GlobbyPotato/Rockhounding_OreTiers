package com.globbypotato.rockhounding_oretiers.compat.jei.bloomery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class BloomeryRecipeWrapper extends RHRecipeWrapper<BloomeryRecipes> {
	
	public BloomeryRecipeWrapper(@Nonnull BloomeryRecipes recipe) {
		super(recipe);
	}

	public static List<BloomeryRecipeWrapper> getRecipes() {
		List<BloomeryRecipeWrapper> recipes = new ArrayList<>();
		for (BloomeryRecipes recipe : MachineRecipes.bloomeryRecipe) {
			if(!recipe.getInput().isEmpty() && !recipe.getOutput().isEmpty() && recipe.getMolten() != null){
				recipes.add(new BloomeryRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	@Nonnull
	public List<ItemStack> getInputs(){
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(getRecipe().getInput());
		return inputs;
	}

	@Nonnull	public List<FluidStack> getMoltens(){
		List<FluidStack> molten = new ArrayList<FluidStack>();
		molten.add(getRecipe().getMolten());
		return molten;
	}

	@Nonnull
	public List<ItemStack> getOutputs(){
		List<ItemStack> outputs = new ArrayList<ItemStack>();
		outputs.add(getRecipe().getOutput());
		return outputs;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutputs(ItemStack.class, getOutputs());
        ingredients.setOutputs(FluidStack.class, getMoltens());
	}

}