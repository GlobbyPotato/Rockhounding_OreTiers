package com.globbypotato.rockhounding_oretiers.compat.jei;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.compat.jei.refiner.RefinerRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.refiner.RefinerRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBloomery;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiPeatDrier;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@JEIPlugin
@SideOnly(Side.CLIENT)
public class RockhoundingPlugin implements IModPlugin{

	public static IJeiHelpers jeiHelpers;

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registry.addRecipeCategories(	
				new RefinerRecipeCategory(guiHelper),
				new DrierRecipeCategory(guiHelper),
				new BloomeryRecipeCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		int rX = 156; int rY = 4; int rW = 16; int rH = 14; 

		registry.handleRecipes(RefinerRecipes.class, new IRecipeWrapperFactory<RefinerRecipes>() { @Override @Nonnull public IRecipeWrapper getRecipeWrapper(@Nonnull RefinerRecipes recipe) {return new RefinerRecipeWrapper(recipe);}}, RHRecipeUID.REFINER);
		registry.handleRecipes(DrierRecipes.class, new IRecipeWrapperFactory<DrierRecipes>() { @Override @Nonnull public IRecipeWrapper getRecipeWrapper(@Nonnull DrierRecipes recipe) {return new DrierRecipeWrapper(recipe);}}, RHRecipeUID.REFINER);
		registry.handleRecipes(BloomeryRecipes.class, new IRecipeWrapperFactory<BloomeryRecipes>() { @Override @Nonnull public IRecipeWrapper getRecipeWrapper(@Nonnull BloomeryRecipes recipe) {return new BloomeryRecipeWrapper(recipe);}}, RHRecipeUID.REFINER);

		registry.addRecipes(RefinerRecipeWrapper.getRecipes(), RHRecipeUID.REFINER);
		registry.addRecipes(DrierRecipeWrapper.getRecipes(), RHRecipeUID.DRIER);
		registry.addRecipes(BloomeryRecipeWrapper.getRecipes(), RHRecipeUID.BLOOMERY);

		registry.addRecipeClickArea(GuiCoalRefiner.class, rX, rY, rW, rH, RHRecipeUID.REFINER);
		registry.addRecipeClickArea(GuiPeatDrier.class, rX, rY, rW, rH, RHRecipeUID.DRIER);
		registry.addRecipeClickArea(GuiBloomery.class, rX, rY, rW, rH, RHRecipeUID.BLOOMERY);

		registry.addRecipeCatalyst(new ItemStack(ModBlocks.COAL_REFINER), RHRecipeUID.REFINER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.PEAT_DRIER), RHRecipeUID.DRIER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.BLOOMERY), RHRecipeUID.BLOOMERY);

		registry.addIngredientInfo(new ItemStack(ModBlocks.COAL_REFINER), ItemStack.class, "Improves some types of coal into their next tiers for more efficiency. It requires lava or a fire under the block for heating. If under the block a vanilla hopper is used, the heating source must go under the hopper. Only vanilla hopper is recognized. A fire icon in the gui will inform if the device is correctly working.");
		registry.addIngredientInfo(new ItemStack(ModBlocks.PEAT_DRIER), ItemStack.class, "Dries moist peat chunks into burnable dried peat coal.");
		registry.addIngredientInfo(new ItemStack(ModBlocks.SEAM_FIRE), ItemStack.class, "May spread fire when mined or on impact. Can be cooled down into a coal block by using it on a filled cauldron. Better not placing it around!");

	}
}