package com.globbypotato.rockhounding_oretiers.compat.jei.bloomery;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class BloomeryRecipeHandler implements IRecipeHandler<BloomeryRecipeWrapper> {

	@Nonnull
	@Override
	public Class<BloomeryRecipeWrapper> getRecipeClass() {
		return BloomeryRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RHRecipeUID.BLOOMERY;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(@Nonnull BloomeryRecipeWrapper recipe) {
		return RHRecipeUID.BLOOMERY;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull BloomeryRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull BloomeryRecipeWrapper wrapper) {
		BloomeryRecipes recipe = wrapper.getRecipe();
		if (recipe.getInput() == null) {
			return false;
		}

		if (recipe.getMolten() == null) {
			return false;
		}
		return true;
	}
}