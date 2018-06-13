package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBase;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityPeatDrier extends TileEntityInv {
	private boolean cooking;

	public static int inputSlots = 1;
	public static int outputSlots = 1;
	public TileEntityPeatDrier() {
		super(inputSlots, outputSlots, 0);

		this.input =  new MachineStackHandler(inputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && isValidIngredient(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(this.input, WriteMode.IN);
	}



	//-------------- SLOTS ---------------- 
	public ItemStack inputSlot(){
		return this.input.getStackInSlot(INPUT_SLOT);
	}

	public ItemStack outputSlot(){
		return this.output.getStackInSlot(OUTPUT_SLOT);
	}



	//-------------- HANDLERS ---------------- 
	@Override
	public int getGUIHeight() {
		return GuiBase.HEIGHT;
	}

	public String getName() {
		return "peat_drier";
	}

	public boolean isBurning(){
		return this.cooking;
	}



	//-------------- CUSTOM ---------------- 
	public ArrayList<DrierRecipes> recipeList(){
		return MachineRecipes.drierRecipe;
	}

	public DrierRecipes getRecipeList(int x){
		return recipeList().get(x);
	}

	public DrierRecipes getCurrentRecipe(){
		if(!inputSlot().isEmpty()){
			for(int x = 0; x < recipeList().size(); x++){
				if(!getRecipeList(x).getInput().isEmpty() && CoreUtils.isMatchingIngredient(inputSlot(), getRecipeList(x).canOredict(), getRecipeList(x).getInput())){
					return getRecipeList(x);
				}
			}
		}
		return null;
	}

	public boolean isValidRecipe() {
		return getCurrentRecipe() != null;
	}

	private boolean isValidIngredient(ItemStack stack) {
		if(!stack.isEmpty()){
			ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(stack));
			if(!inputOreIDs.isEmpty()){
				for(DrierRecipes recipe: recipeList()){
					if(recipe.canOredict()){
						ArrayList<Integer> recipeOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
						if(!inputOreIDs.isEmpty()){
							if(CoreUtils.compareDictArrays(inputOreIDs, recipeOreIDs)){
								return true;
							}
						}
					}
				}
			}
			return recipeList().stream().anyMatch(recipe -> !stack.isEmpty() && !recipe.getInput().isEmpty() && stack.isItemEqual(recipe.getInput()));
		}
		return false;
	}

	public int getCooktimeMax(){
        return isValidRecipe() ? getCurrentRecipe().getRefining() : ModConfig.dryingSpeed;
    }

	@Override
	public boolean isCooking(){
		return canRefine() && getCooktime() > 0;
	}

	private void burnState(){
		if(this.world.isRemote){
			if (isCooking() != isBurning()) {
				this.cooking = isCooking();
				this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
			}
		}
	}



	//-------------- PROCESS ---------------- 
	@Override
	public void update() {
		if(!this.world.isRemote){
			if(!this.inputSlot().isEmpty()){
				if(canRefine()){
		            this.cooktime++;
					if(getCooktime()>= getCooktimeMax()) { 
						this.cooktime = 0; 
						refine(); 
					}
					this.markDirtyClient();
				}else{
					tickOff();
				}
			}else{
				tickOff();
			}
		}
		burnState();
	}

	private boolean canRefine() {
		return isValidRecipe()
			&& this.output.canSetOrStack(outputSlot(), getCurrentRecipe().getOutput());
	}

	private void refine() {
		this.output.setOrStack(OUTPUT_SLOT, getCurrentRecipe().getOutput());
		this.input.decrementSlot(INPUT_SLOT);
	}

}