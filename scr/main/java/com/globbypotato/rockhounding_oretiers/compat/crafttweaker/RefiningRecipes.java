package com.globbypotato.rockhounding_oretiers.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_oretiers.CoalRefiner")
public class RefiningRecipes extends CTSupport{
	public static String name = "Basic Coal Refiner";
	public static ArrayList<RefinerRecipes> recipeList = MachineRecipes.refinerRecipe;

    @ZenMethod
    public static void add(IItemStack input, IItemStack output, int refining) {
        if(input == null || output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToRefining(new RefinerRecipes(toStack(input), toStack(output), refining)));
    }
    @ZenMethod
    public static void add(String oredict, IItemStack output, int refining) {
        if(output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToRefining(new RefinerRecipes(oredict, toStack(output), refining)));
    }
    	private static class AddToRefining implements IAction {
	    	private RefinerRecipes recipe;
	    	public AddToRefining(RefinerRecipes recipe){
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
        CraftTweakerAPI.apply(new RemoveFromRefining(toStack(input)));    
    }
		    private static class RemoveFromRefining implements IAction {
		    	private ItemStack input;
		    	public RemoveFromRefining(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}

		    	@Override
		    	public void apply() {
		    		for(RefinerRecipes recipe : recipeList){
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