package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityMachineEnergy;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.Utils;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiPeatDrier;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityPeatDrier extends TileEntityMachineEnergy {
	private boolean cooking;

	public static int totInput = 1;
	public static int totOutput = 1;

	public TileEntityPeatDrier() {
		super(totInput, totOutput, 0);

		input =  new MachineStackHandler(totInput,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && hasRecipe(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input, WriteMode.IN);
		this.markDirtyClient();
	}



	//-------------- HANDLERS ---------------- 
    public int getCookTime(){
        return ModConfig.dryingMultiplier <= 1 ? ModConfig.baseSpeed : ModConfig.baseSpeed * ModConfig.dryingMultiplier;
    }

	@Override
	public int getGUIHeight() {
		return GuiPeatDrier.HEIGHT;
	}



	//-------------- CUSTOM ---------------- 
	public boolean hasRecipe(ItemStack stack){
		return MachineRecipes.drierRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getInput() != null && stack.isItemEqual(recipe.getInput()));
	}

	private boolean isValidOredict(ItemStack stack) {
		if(stack != null){
			ArrayList<Integer> inputOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(stack));
			for(DrierRecipes recipe: MachineRecipes.drierRecipe){
				ArrayList<Integer> recipeOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
				for(Integer oreID: recipeOreIDs){
					if(inputOreIDs.contains(oreID)) return true;
				}
			}
		}
		return false;
	}

	public ItemStack getRecipeOutput(ItemStack inputStack){
		if(inputStack != null){
			for(DrierRecipes recipe: MachineRecipes.drierRecipe){
				if(inputStack.isItemEqual(recipe.getInput())){
					return recipe.getOutput();
				}
			}
		}
		return null;
	}

	@Override
	public boolean isCooking(){
		return canRefine() && cookTime > 0;
	}

	private void burnState(){
		if(worldObj.isRemote){
			if (isCooking() != cooking) {
				cooking = isCooking();
				worldObj.notifyBlockOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
				worldObj.markBlockRangeForRenderUpdate(pos, pos);
			}
		}
	}



	//-------------- PROCESS ---------------- 
	@Override
	public void update() {
		if(!worldObj.isRemote){
			if(canRefine()){
	            this.cookTime++;
				if(cookTime >= getCookTime()) { 
					cookTime = 0; 
					refine(); 
				}
			}else{
				cookTime = 0;
			}
			this.markDirtyClient();
		}
		burnState();
	}

	private boolean canRefine() {
		return (hasRecipe(input.getStackInSlot(INPUT_SLOT)) || isValidOredict(input.getStackInSlot(INPUT_SLOT))) 
			&& canOutput(input.getStackInSlot(INPUT_SLOT));
	}

	private void refine() {
		if(canRefine()) {
			ItemStack recipeOutput = getRecipeOutput(input.getStackInSlot(INPUT_SLOT));
			output.setOrStack(OUTPUT_SLOT, recipeOutput);
			input.decrementSlot(INPUT_SLOT);
		}
	}

	private boolean canOutput(ItemStack stack){
		ItemStack recipeOutput = getRecipeOutput(stack);
		return recipeOutput != null && output.canSetOrStack(output.getStackInSlot(OUTPUT_SLOT), recipeOutput);
	}

}