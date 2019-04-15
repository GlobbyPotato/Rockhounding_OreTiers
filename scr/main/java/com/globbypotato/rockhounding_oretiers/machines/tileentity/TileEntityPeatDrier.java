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
		super(inputSlots, outputSlots, 0, 0);

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
				if(getRecipeList(x).getType()){
					ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(inputSlot()));
					if(inputOreIDs.contains(OreDictionary.getOreID(getRecipeList(x).getOredict()))){
						return getRecipeList(x);
					}
				}else{
					if(getRecipeList(x).getInput().isItemEqual(inputSlot())){
						return getRecipeList(x);
					}
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
			for(DrierRecipes recipe: recipeList()){
				if(recipe.getType()){
					ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(stack));
					if(!inputOreIDs.isEmpty()){
						if(inputOreIDs.contains(OreDictionary.getOreID(recipe.getOredict()))){
							return true;
						}
					}
				}else{
					if(recipe.getInput().isItemEqual(stack)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getCooktimeMax(){
        return isValidRecipe() ? getCurrentRecipe().getRefining() : ModConfig.dryingSpeed;
    }

	@Override
	public boolean isCooking(){
		return isValidRecipe() && canRefine() && getCooktime() > 0;
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
		ItemStack dried = getCurrentRecipe().getOutput();
		dried.setCount(drySize());
		return isValidRecipe() && this.output.canSetOrStack(outputSlot(), dried);
	}

	private void refine() {
		ItemStack dried = getCurrentRecipe().getOutput();
		dried.setCount(drySize());
		this.output.setOrStack(OUTPUT_SLOT, dried);
		this.input.decrementSlotBy(INPUT_SLOT, drySize());
	}

	private int drySize() {
		if(inputSlot().getCount() > 9){
			return 9;
		}else if(inputSlot().getCount() > 0 && inputSlot().getCount() <= 9){
			return inputSlot().getCount();
		}
		return 0;
	}

}