package com.globbypotato.rockhounding_oretiers.integration.crafttweaker;

import com.globbypotato.rockhounding_oretiers.compat.jei.bloomery.BloomeryRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_oretiers.Bloomery")
public class CastingRecipes {
	private static String name = "Bloomery";

    @ZenMethod
    public static void add(IItemStack ore, ILiquidStack bloom, int bloomAmount, IItemStack output) {
        if(ore == null || bloom == null || output == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        FluidStack bloomStack = CTSupport.getFluid(bloom, bloomAmount);
        MineTweakerAPI.apply(new AddToBloomery(new BloomeryRecipes(CTSupport.toStack(ore), bloomStack, CTSupport.toStack(output))));
    }
		    private static class AddToBloomery implements IUndoableAction {
		    	private BloomeryRecipes recipe;
		    	public AddToBloomery(BloomeryRecipes recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		MachineRecipes.bloomeryRecipe.add(this.recipe);
		    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new BloomeryRecipeWrapper(this.recipe));
		    	}
		    	@Override
		    	public void undo() {
		    		for(BloomeryRecipes recipe : MachineRecipes.bloomeryRecipe){
		    			if(recipe.equals(this.recipe)){
		    				MachineRecipes.bloomeryRecipe.remove(recipe);
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new BloomeryRecipeWrapper(recipe));
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }


    @ZenMethod
    public static void remove(IItemStack input) {
        if(input == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        MineTweakerAPI.apply(new RemoveFromBloomery(CTSupport.toStack(input)));
    }
		    private static class RemoveFromBloomery implements IUndoableAction {
		    	private ItemStack input;
		    	BloomeryRecipes removedRecipe;
		    	public RemoveFromBloomery(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}
		    	@Override
		    	public void apply() {
		    		for(BloomeryRecipes recipe : MachineRecipes.bloomeryRecipe){
		    			if(this.input != null && recipe.getInput().isItemEqual(this.input)){
		    				removedRecipe =recipe;
		    				MachineRecipes.bloomeryRecipe.remove(recipe);
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new BloomeryRecipeWrapper(removedRecipe));
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public void undo() {
		    		if(removedRecipe != null){
		    			MachineRecipes.bloomeryRecipe.add(removedRecipe);
			    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new BloomeryRecipeWrapper(removedRecipe));
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }
}