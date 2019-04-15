package com.globbypotato.rockhounding_oretiers.compat.jei.drier;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class DrierRecipeWrapper extends RHRecipeWrapper<DrierRecipes> {
	
	public DrierRecipeWrapper(@Nonnull DrierRecipes recipe) {
		super(recipe);
	}

	public static ArrayList<DrierRecipeWrapper> getRecipes() {
		ArrayList<DrierRecipeWrapper> recipes = new ArrayList<>();
		for (DrierRecipes recipe : MachineRecipes.drierRecipe) {
			if(isValidRecipe(recipe)){
				recipes.add(new DrierRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	private static boolean isValidRecipe(DrierRecipes recipe){
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
	public ArrayList<ItemStack> getOutputs(){
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