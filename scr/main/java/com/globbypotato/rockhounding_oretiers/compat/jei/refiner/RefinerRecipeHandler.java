package com.globbypotato.rockhounding_oretiers.compat.jei.refiner;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class RefinerRecipeHandler implements IRecipeHandler<RefinerRecipeWrapper> {

	@Nonnull
	@Override
	public Class<RefinerRecipeWrapper> getRecipeClass() {
		return RefinerRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RHRecipeUID.REFINER;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(@Nonnull RefinerRecipeWrapper recipe) {
		return RHRecipeUID.REFINER;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull RefinerRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull RefinerRecipeWrapper wrapper) {
		RefinerRecipes recipe = wrapper.getRecipe();
		if (recipe.getInput() == null || recipe.getOutput() == null) {
			return false;
		}
		return true;
	}
}