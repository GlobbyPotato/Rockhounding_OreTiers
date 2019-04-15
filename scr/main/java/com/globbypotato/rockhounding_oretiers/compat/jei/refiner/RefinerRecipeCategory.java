package com.globbypotato.rockhounding_oretiers.compat.jei.refiner;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiCoalRefiner;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public class RefinerRecipeCategory extends RHRecipeCategory {

	private static final int INPUT_SLOT = 1;
	private static final int OUTPUT_SLOT = 2;

	private final static ResourceLocation guiTexture = GuiCoalRefiner.TEXTURE_REF;

	public RefinerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 35, 35, 105, 60), "jei.refiner.name");
	}

	@Nonnull
	@Override
	public String getUid() {
		return RHRecipeUID.REFINER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		RefinerRecipeWrapper wrapper = (RefinerRecipeWrapper) recipeWrapper;	

		guiItemStacks.init(INPUT_SLOT, true, 20, 2);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs());

		guiItemStacks.init(OUTPUT_SLOT, false, 68, 2);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs());
	}
}