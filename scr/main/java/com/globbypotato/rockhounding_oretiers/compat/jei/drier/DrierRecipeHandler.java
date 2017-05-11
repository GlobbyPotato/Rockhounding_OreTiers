package com.globbypotato.rockhounding_oretiers.compat.jei.drier;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class DrierRecipeHandler implements IRecipeHandler<DrierRecipeWrapper> {

	@Nonnull
	@Override
	public Class<DrierRecipeWrapper> getRecipeClass() {
		return DrierRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RHRecipeUID.DRIER;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(@Nonnull DrierRecipeWrapper recipe) {
		return RHRecipeUID.DRIER;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull DrierRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull DrierRecipeWrapper wrapper) {
		DrierRecipes recipe = wrapper.getRecipe();
		if (recipe.getInput() == null || recipe.getOutput() == null) {
			return false;
		}
		return true;
	}
}