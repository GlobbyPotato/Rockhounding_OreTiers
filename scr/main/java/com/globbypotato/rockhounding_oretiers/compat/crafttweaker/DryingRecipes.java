package com.globbypotato.rockhounding_oretiers.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_oretiers.DryingPallet")
public class DryingRecipes extends CTSupport{
	public static String name = "Drying Pallet";
	public static ArrayList<DrierRecipes> recipeList = MachineRecipes.drierRecipe;

    @ZenMethod
    public static void add(IItemStack input, IItemStack output, int refining) {
        if(input == null || output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToDrying(new DrierRecipes(toStack(input), toStack(output), refining)));
    }
    @ZenMethod
    public static void add(String oredict, IItemStack output, int refining) {
        if(output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToDrying(new DrierRecipes(oredict, toStack(output), refining)));
    }
		    private static class AddToDrying implements IAction {
		    	private DrierRecipes recipe;
		    	public AddToDrying(DrierRecipes recipe){
		          super();
		          this.recipe = recipe;
		    	}

		    	@Override
		    	public void apply() {
		    		recipeList.add(this.recipe);
		    	}

		    	@Override
		    	public String describe() {
					return addCaption(name);
		    	}
		    }


    @ZenMethod
    public static void remove(IItemStack input) {
        if(input == null) {error(name); return;}
        CraftTweakerAPI.apply(new RemoveFromDrying(toStack(input)));    
    }
		    private static class RemoveFromDrying implements IAction {
		    	private ItemStack input;
		    	public RemoveFromDrying(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}

		    	@Override
		    	public void apply() {
		    		for(DrierRecipes recipe : recipeList){
		    			if(!this.input.isEmpty() && recipe.getInput().isItemEqual(this.input)){
				    		recipeList.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}

				@Override
				public String describe() {
					return removeCaption(name);
				}
		    }
}