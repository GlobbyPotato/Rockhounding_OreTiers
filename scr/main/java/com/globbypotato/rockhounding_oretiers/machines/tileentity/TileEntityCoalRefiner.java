package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.ModRecipes;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_oretiers.utils.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityCoalRefiner extends TileEntityBase {
	public TileEntityCoalRefiner() {
		super(1, 1, 0);

		input =  new MachineStackHandler(INPUT_SLOTS, this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && hasRecipe(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input,WriteMode.IN_OUT);
		this.markDirtyClient();
	}



	//-------------- HANDLERS ---------------- 
    public int getCookTime(){
        return ModConfig.refiningMultiplier <= 1 ? ModConfig.baseSpeed : ModConfig.baseSpeed * ModConfig.refiningMultiplier;
    }

	@Override
	public int getGUIHeight() {
		return GuiCoalRefiner.HEIGHT;
	}



	//-------------- CUSTOM ---------------- 
	public boolean hasRecipe(ItemStack stack){
		return ModRecipes.refinerRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getInput() != null && stack.isItemEqual(recipe.getInput()));
	}

	private boolean isValidOredict(ItemStack stack) {
		if(stack != null){
			ArrayList<Integer> inputOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(stack));
			for(RefinerRecipes recipe: ModRecipes.refinerRecipe){
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
			for(RefinerRecipes recipe: ModRecipes.refinerRecipe){
				if(inputStack.isItemEqual(recipe.getInput())){
					return recipe.getOutput();
				}
			}
		}
		return null;
	}

	public boolean isHeated() {
		return  (checkBlockInstance(0, -1, 0) || (checkBlock(0, -1, 0, Blocks.HOPPER) && checkBlockInstance(0, -2, 0))) ||
				(checkBlock(0, -1, 0, Blocks.FIRE)  || (checkBlock(0, -1, 0, Blocks.HOPPER) && checkBlock(0, -2, 0, Blocks.FIRE)));
	}

	private boolean checkBlockInstance(int offX, int offY, int offZ){
		return worldObj.getBlockState(new BlockPos(pos.getX() + offX, pos.getY() + offY, pos.getZ() + offZ)).getBlock() instanceof BlockTorch;
	}

	private boolean checkBlock(int offX, int offY, int offZ, Block block){
		return worldObj.getBlockState(new BlockPos(pos.getX() + offX, pos.getY() + offY, pos.getZ() + offZ)).getBlock() == block;
	}

	@Override
	public boolean isCooking(){
		return isHeated();
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
		if(canRefine()){
            this.cookTime++;
			if(cookTime >= getCookTime()) { 
				refine();
				cookTime = 0; 
				this.markDirtyClient();
			}
		}else{
			cookTime = 0;
		}
		burnState();
	}

	private boolean canRefine() {
		return (hasRecipe(input.getStackInSlot(INPUT_SLOT)) || isValidOredict(input.getStackInSlot(INPUT_SLOT)))  
			&& canOutput(input.getStackInSlot(INPUT_SLOT)) 
			&& (isHeated());
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