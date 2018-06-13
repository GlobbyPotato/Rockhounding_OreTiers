package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBase;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityCoalRefiner extends TileEntityInv {
	private boolean cooking;

	public static int inputSlots = 1;
	public static int outputSlots = 1;
	public TileEntityCoalRefiner() {
		super(inputSlots, outputSlots, 0);

		this.input =  new MachineStackHandler(inputSlots, this){
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
		return "coal_refiner";
	}

	public boolean isBurning(){
		return this.cooking;
	}



	//-------------- CUSTOM ---------------- 
	public ArrayList<RefinerRecipes> recipeList(){
		return MachineRecipes.refinerRecipe;
	}

	public RefinerRecipes getRecipeList(int x){
		return recipeList().get(x);
	}

	public RefinerRecipes getCurrentRecipe(){
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
				for(RefinerRecipes recipe: recipeList()){
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
        return isValidRecipe() ? getCurrentRecipe().getRefining() : ModConfig.refiningSpeed;
    }

	public boolean isHeated() {
		return  (burnableBlocks(-1)  || (checkBlock(0, -1, 0, Blocks.HOPPER) && burnableBlocks(-2)));
	}

	private boolean burnableBlocks(int i) {
		return checkBlock(0, i, 0, Blocks.FIRE) || checkBlock(0, i, 0, Blocks.LAVA);
	}

	private boolean checkBlock(int offX, int offY, int offZ, Block block){
		return this.world.getBlockState(new BlockPos(this.pos.getX() + offX, this.pos.getY() + offY, this.pos.getZ() + offZ)).getBlock() == block;
	}

	@Override
	public boolean isCooking(){
		return isHeated();
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
					if(getCooktime() >= getCooktimeMax()) { 
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
		return isHeated()
			&& isValidRecipe()
			&& this.output.canSetOrStack(outputSlot(), getCurrentRecipe().getOutput());
	}

	private void refine() {
		this.output.setOrStack(OUTPUT_SLOT, getCurrentRecipe().getOutput());
		this.input.decrementSlot(INPUT_SLOT);
	}

}