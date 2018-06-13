package com.globbypotato.rockhounding_oretiers.compat.jei.drier;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiPeatDrier;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public class DrierRecipeCategory extends RHRecipeCategory {

	private static final int INPUT_SLOT = 1;
	private static final int OUTPUT_SLOT = 2;

	private final static ResourceLocation guiTexture = GuiPeatDrier.TEXTURE_REF;

	public DrierRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 35, 35, 105, 60), "jei.drier.name");
	}

	@Nonnull
	@Override
	public String getUid() {
		return RHRecipeUID.DRIER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		DrierRecipeWrapper wrapper = (DrierRecipeWrapper) recipeWrapper;	

		guiItemStacks.init(INPUT_SLOT, true, 20, 2);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs());

		guiItemStacks.init(OUTPUT_SLOT, false, 68, 2);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs());
	}

}