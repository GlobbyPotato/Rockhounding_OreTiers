package com.globbypotato.rockhounding_oretiers.compat;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeHandler;
import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeHandler;
import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.compat.jei.refiner.RefinerRecipeCategory;
import com.globbypotato.rockhounding_oretiers.compat.jei.refiner.RefinerRecipeHandler;
import com.globbypotato.rockhounding_oretiers.compat.jei.refiner.RefinerRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBloomery;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiPeatDrier;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class RockhoundingPlugin extends BlankModPlugin{

	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(IModRegistry registry) {

		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registry.addRecipeCategories(
				new RefinerRecipeCategory(guiHelper),
				new DrierRecipeCategory(guiHelper),
				new BloomeryRecipeCategory(guiHelper)
				);

		registry.addRecipeHandlers(
				new RefinerRecipeHandler(),
				new DrierRecipeHandler(),
				new BloomeryRecipeHandler()
				);

		registry.addRecipes(RefinerRecipeWrapper.getRecipes());
		registry.addRecipes(DrierRecipeWrapper.getRecipes());
		registry.addRecipes(BloomeryRecipeWrapper.getRecipes());

		registry.addRecipeClickArea(GuiCoalRefiner.class, 55, 35, 66, 38, RHRecipeUID.REFINER);
		registry.addRecipeClickArea(GuiPeatDrier.class, 55, 35, 66, 38, RHRecipeUID.DRIER);
		registry.addRecipeClickArea(GuiBloomery.class, 46, 19, 28, 12, RHRecipeUID.BLOOMERY);
		registry.addRecipeClickArea(GuiBloomery.class, 102, 67, 28, 12, RHRecipeUID.BLOOMERY);

		registry.addRecipeCategoryCraftingItem(new ItemStack(ModContents.coalRefiner), RHRecipeUID.REFINER);
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModContents.peatDrier), RHRecipeUID.DRIER);
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModContents.bloomery), RHRecipeUID.BLOOMERY);

		registry.addDescription(new ItemStack(ModContents.coalRefiner), "Improves some types of coal into their next tiers for more efficiency. It requires a torch or a fire under the block for heating. If under the block is present a vanilla hopper, the heating source must go under the hopper. Only vanilla hopper is recognized. A fire icon in the gui will inform if the device is correctly working.");
		registry.addDescription(new ItemStack(ModContents.peatDrier), "Dries moist peat chunks into burnable dried peat coal.");
		registry.addDescription(new ItemStack(ModContents.seamFire), "May spread fire when mined or on impact. Can be cooled down into a coal block by using it on a filled cauldron. Better not placing it around!");

		if(ModConfig.enableBloomery){
			IIngredientBlacklist itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModContents.tiersItems,1,6));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModContents.tiersItems,1,7));
		}
	}
}