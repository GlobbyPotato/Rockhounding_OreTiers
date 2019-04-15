package com.globbypotato.rockhounding_oretiers.compat.jei.refiner;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RefinerRecipeWrapper extends RHRecipeWrapper<RefinerRecipes> {
	
	public RefinerRecipeWrapper(@Nonnull RefinerRecipes recipe) {
		super(recipe);
	}

	public static ArrayList<RefinerRecipeWrapper> getRecipes() {
		ArrayList<RefinerRecipeWrapper> recipes = new ArrayList<>();
		for (RefinerRecipes recipe : MachineRecipes.refinerRecipe) {
			if(isValidRecipe(recipe)){
				recipes.add(new RefinerRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	private static boolean isValidRecipe(RefinerRecipes recipe){
		return ((!recipe.getType() && !recipe.getInput().isEmpty()) || (recipe.getType() && OreDictionary.getOres(recipe.getOredict()).size() > 0))
			&& recipe.getOutput() != null;
	}

	@Nonnull
	public ArrayList<ItemStack> getInputs(){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		if(getRecipe().getType()){
			inputs.addAll(OreDictionary.getOres(getRecipe().getOredict()));
		}else{
			inputs.add(getRecipe().getInput());
		}
		return inputs;
	}

	@Nonnull
	public ArrayList<ItemStack> getOutputs() {
		ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
		outputs.add(getRecipe().getOutput());
		return outputs;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutputs(ItemStack.class, getOutputs());
	}

}