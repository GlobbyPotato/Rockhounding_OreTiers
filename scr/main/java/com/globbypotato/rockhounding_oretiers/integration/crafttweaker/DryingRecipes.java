package com.globbypotato.rockhounding_oretiers.integration.crafttweaker;

import com.globbypotato.rockhounding_oretiers.compat.jei.drier.DrierRecipeWrapper;
import com.globbypotato.rockhounding_oretiers.handlers.ModRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_oretiers.DryingPallet")
public class DryingRecipes {
	private static String name = "Drying Pallet";

    @ZenMethod
    public static void add(IItemStack input, IItemStack output) {
        if(input == null || output == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        MineTweakerAPI.apply(new AddToDrying(new DrierRecipes(CTSupport.toStack(input), CTSupport.toStack(output))));
    }
		    private static class AddToDrying implements IUndoableAction {
		    	private DrierRecipes recipe;
		    	public AddToDrying(DrierRecipes recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		ModRecipes.drierRecipe.add(this.recipe);
		    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new DrierRecipeWrapper(this.recipe));
		    	}
		    	@Override
		    	public void undo() {
		    		for(DrierRecipes recipe : ModRecipes.drierRecipe){
		    			if(recipe.equals(this.recipe)){
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new DrierRecipeWrapper(recipe));
		    				ModRecipes.drierRecipe.remove(recipe);	
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
        MineTweakerAPI.apply(new RemoveFromDrying(CTSupport.toStack(input)));    
    }
		    private static class RemoveFromDrying implements IUndoableAction {
		    	private ItemStack input;
		    	private DrierRecipes removedRecipe;
		    	public RemoveFromDrying(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}
		    	@Override
		    	public void apply() {
		    		for(DrierRecipes recipe : ModRecipes.drierRecipe){
		    			if(this.input != null && recipe.getInput().isItemEqual(this.input)){
				    		this.removedRecipe = recipe;
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new DrierRecipeWrapper(recipe));
		    				ModRecipes.drierRecipe.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public void undo() {
		    		if(this.removedRecipe != null){
			    		ModRecipes.drierRecipe.add(this.removedRecipe);
			    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new DrierRecipeWrapper(this.removedRecipe));
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