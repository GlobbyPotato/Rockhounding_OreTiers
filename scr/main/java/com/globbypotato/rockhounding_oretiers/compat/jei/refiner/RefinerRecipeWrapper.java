package com.globbypotato.rockhounding_oretiers.compat.jei.refiner;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class RefinerRecipeWrapper extends RHRecipeWrapper<RefinerRecipes> {
	
	public RefinerRecipeWrapper(@Nonnull RefinerRecipes recipe) {
		super(recipe);
	}

	public static List<RefinerRecipeWrapper> getRecipes() {
		List<RefinerRecipeWrapper> recipes = new ArrayList<>();
		for (RefinerRecipes recipe : MachineRecipes.refinerRecipe) {
			recipes.add(new RefinerRecipeWrapper(recipe));
		}
		return recipes;
	}

	@Override
	public List<ItemStack> getInputs(){
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(getRecipe().getInput());
		return inputs;
	}

	@Override
	public List<ItemStack> getOutputs() {
		List<ItemStack> outputs = new ArrayList<ItemStack>();
		outputs.add(getRecipe().getOutput());
		return outputs;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {}

}