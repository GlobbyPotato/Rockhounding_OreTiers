package com.globbypotato.rockhounding_oretiers.compat.jei.bloomery;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class BloomeryRecipeWrapper extends RHRecipeWrapper<BloomeryRecipes> {
	
	public BloomeryRecipeWrapper(@Nonnull BloomeryRecipes recipe) {
		super(recipe);
	}

	public static ArrayList<BloomeryRecipeWrapper> getRecipes() {
		ArrayList<BloomeryRecipeWrapper> recipes = new ArrayList<>();
		for (BloomeryRecipes recipe : MachineRecipes.bloomeryRecipe) {
			if(isValidRecipe(recipe)){
				recipes.add(new BloomeryRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	private static boolean isValidRecipe(BloomeryRecipes recipe){
		return ((!recipe.getType() && !recipe.getInput().isEmpty()) || (recipe.getType() && OreDictionary.getOres(recipe.getOredict()).size() > 0))
			&& recipe.getMolten() != null
			&& recipe.getOutput() != null;
	}

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
	public ArrayList<FluidStack> getMoltens(){
		ArrayList<FluidStack> molten = new ArrayList<FluidStack>();
		molten.add(getRecipe().getMolten());
		return molten;
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
        ingredients.setOutputs(FluidStack.class, getMoltens());
	}

}