package com.globbypotato.rockhounding_oretiers.compat.jei.bloomery;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.compat.RHRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.RHRecipeUID;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBloomery;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BloomeryRecipeCategory extends RHRecipeCategory {

	private static final int TANK_SLOT = 0;
	private static final int INPUT_SLOT = 1;
	private static final int OUTPUT_SLOT = 2;
	private static final int CONSUMABLE_SLOT = 3;

	private final static ResourceLocation guiTexture = GuiBloomery.TEXTURE;

	public BloomeryRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 26, 15, 124, 68), "jei.bloomery.name");
	}

	@Nonnull
	@Override
	public String getUid() {
		return RHRecipeUID.BLOOMERY;
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
		BloomeryRecipeWrapper wrapper = (BloomeryRecipeWrapper) recipeWrapper;	

		guiFluidStacks.init(TANK_SLOT, true,  50, 2, 24, 64, 1000, false, null);
		guiFluidStacks.set(TANK_SLOT, wrapper.getRecipe().getMolten());

		guiItemStacks.init(INPUT_SLOT, true, 1, 1);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs());

		guiItemStacks.init(CONSUMABLE_SLOT, true, 81, 31);
		guiItemStacks.set(CONSUMABLE_SLOT, new ItemStack(ModContents.forgeHammer));

		guiItemStacks.init(OUTPUT_SLOT, true, 105, 49);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getRecipe().getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		setRecipe(recipeLayout,recipeWrapper);
	}
}