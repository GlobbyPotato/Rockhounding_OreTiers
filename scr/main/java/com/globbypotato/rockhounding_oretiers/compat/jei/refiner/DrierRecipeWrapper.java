package com.globbypotato.rockhounding_oretiers.compat.jei.drier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class DrierRecipeWrapper extends RHRecipeWrapper<DrierRecipes> {
	
	public DrierRecipeWrapper(@Nonnull DrierRecipes recipe) {
		super(recipe);
	}

	public static List<DrierRecipeWrapper> getRecipes() {
		List<DrierRecipeWrapper> recipes = new ArrayList<>();
		for (DrierRecipes recipe : MachineRecipes.drierRecipe) {
			if(!recipe.getInput().isEmpty() && !recipe.getOutput().isEmpty()){
				recipes.add(new DrierRecipeWrapper(recipe));
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
	}

}