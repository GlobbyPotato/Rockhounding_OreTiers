package com.globbypotato.rockhounding_oretiers.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_oretiers.Bloomery")
public class CastingRecipes extends CTSupport{
	public static String name = "Bloomery";
	public static ArrayList<BloomeryRecipes> recipeList = MachineRecipes.bloomeryRecipe;

    @ZenMethod
    public static void add(IItemStack ore, ILiquidStack bloom, int bloomAmount, IItemStack output) {
        if(ore == null || bloom == null || output == null) {error(name); return;}
        FluidStack bloomStack = getFluid(bloom, bloomAmount);
        CraftTweakerAPI.apply(new AddToBloomery(new BloomeryRecipes(toStack(ore), bloomStack, toStack(output))));
    }
		    private static class AddToBloomery implements IAction {
		    	private BloomeryRecipes recipe;
		    	public AddToBloomery(BloomeryRecipes recipe){
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
        CraftTweakerAPI.apply(new RemoveFromBloomery(toStack(input)));
    }
		    private static class RemoveFromBloomery implements IAction {
		    	private ItemStack input;
		    	public RemoveFromBloomery(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}

		    	@Override
		    	public void apply() {
		    		for(BloomeryRecipes recipe : recipeList){
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