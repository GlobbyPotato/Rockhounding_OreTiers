package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.IFluidHandlingTile;
import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TemplateStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityMachineTank;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_core.utils.FuelUtils;
import com.globbypotato.rockhounding_core.utils.Utils;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBloomery;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.templates.FluidHandlerConcatenate;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityBloomery extends TileEntityMachineTank implements IFluidHandlingTile {
	private ItemStackHandler template = new TemplateStackHandler(1);

	public static int consumedBloom;
	private boolean cooking;

	public FluidTank bloomTank;
	public int castTime;
	public boolean drainScan;

	FluidStack moltenStack;
	ItemStack outputStack;

	public static int totInput = 3;
	public static int totOutput = 1;
	
	public TileEntityBloomery() {
		super(totInput, totOutput, 1);

		bloomTank = new FluidTank(Fluid.BUCKET_VOLUME + ModConfig.tankCapacity){
			@Override  
			public boolean canFillFluidType(FluidStack fluid){
				return moltenHasRecipe(fluid);
		    }

			@Override
		    public boolean canDrain(){
		        return canDrainTank();
		    }
		};
		bloomTank.setTileEntity(this);
		bloomTank.setCanFill(false);

		input =  new MachineStackHandler(totInput, this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && hasRecipe(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == FUEL_SLOT && FuelUtils.isItemFuel(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == CONSUMABLE_SLOT && CoreUtils.hasConsumable(ToolUtils.hammer, insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input, WriteMode.IN);
		this.markDirtyClient();
	}



	//-------------- HANDLERS ---------------- 
	public ItemStackHandler getTemplate(){
		return this.template;
	}

	public int getCookTime(){
        return ModConfig.bloomingSpeed;
    }

	@Override
	public int getGUIHeight() {
		return GuiBloomery.HEIGHT;
	}



	//-------------- CUSTOM ---------------- 
	public boolean hasRecipe(ItemStack stack){
		return MachineRecipes.bloomeryRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getInput() != null && stack.isItemEqual(recipe.getInput()));
	}

	public static boolean moltenHasRecipe(FluidStack stack){
		return MachineRecipes.bloomeryRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getMolten() != null && recipe.getMolten().isFluidEqual(stack));
	}

	public boolean hasOutput(){
		return MachineRecipes.bloomeryRecipe.stream().anyMatch(
				recipe -> bloomTank.getFluid() != null && recipe.getMolten() != null && recipe.getOutput() != null && bloomTank.getFluid().isFluidEqual(recipe.getMolten()) && bloomTank.getFluidAmount() >= consumedBloom);
	}

	public boolean isCorrectMolten(FluidStack fluid){
		return bloomTank.getFluid() != null && bloomTank.getFluid().isFluidEqual(fluid) && bloomTank.getFluidAmount() <= bloomTank.getCapacity() - fluid.amount;
	}

	public boolean canDrainTank() {
		return bloomTank.getFluid() != null && bloomTank.getFluid().amount > 0 && drainScan;
	}

	@Override
	public boolean isCooking(){
		return (canSmelt() && cookTime > 0) || (canCast() && castTime > 0);
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


	//-------------- I/O ---------------- 
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.castTime = compound.getInteger("CastTime");
		this.drainScan = compound.getBoolean("DrainScan");
		this.bloomTank.readFromNBT(compound.getCompoundTag("BloomTank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("CastTime", this.castTime);
		compound.setBoolean("DrainScan", this.drainScan);
		
		NBTTagCompound bloomTankNBT = new NBTTagCompound();
		this.bloomTank.writeToNBT(bloomTankNBT);
		compound.setTag("BloomTank", bloomTankNBT);
		return compound;
	}

	@Override
	public FluidHandlerConcatenate getCombinedTank(){
		return new FluidHandlerConcatenate(lavaTank, bloomTank);
	}

	@Override
	public void lavaHandler(){
		if(this.getPower() <= this.getPowerMax() - lavaBurntime()){
			if(lavaTank.getFluidAmount() >= Fluid.BUCKET_VOLUME){
				lavaTank.getFluid().amount -= Fluid.BUCKET_VOLUME;
				this.powerCount += lavaBurntime();
				this.markDirtyClient();
			}
		}
	}



	//-------------- PROCESS ---------------- 
	@Override
	public void update() {
		burnFuel(input.getStackInSlot(FUEL_SLOT));
		lavaHandler();
		if(!worldObj.isRemote){
			if(canSmelt()){
				cookTime++; powerCount--;
				if(cookTime >= getCookTime()) { 
					cookTime = 0; 
					addBloom(input.getStackInSlot(INPUT_SLOT)); 
				}
			}else{
				cookTime = 0;
			}

			if(canCast()){
				castTime++;
				if(castTime >= getCookTime()) { 
					castTime = 0; 
					castIngot(); 
				}
			}else{
				castTime = 0;
			}
			this.markDirtyClient();
		}
		burnState();
	}

	private boolean canSmelt() {
		return powerCount >= getCookTime() && (hasRecipe(input.getStackInSlot(INPUT_SLOT)) || isValidOredict(input.getStackInSlot(INPUT_SLOT))) && canContainBloom(input.getStackInSlot(INPUT_SLOT));
	}

			private boolean isValidOredict(ItemStack stack) {
				if(stack != null){
					ArrayList<Integer> inputOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(stack));
					for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
						ArrayList<Integer> recipeOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
						for(Integer oreID: recipeOreIDs){
							if(inputOreIDs.contains(oreID)) return true;
						}
					}
				}
				return false;
			}

			private boolean canContainBloom(ItemStack stack) {
				if(stack != null){
					for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
						if(stack.isItemEqual(recipe.getInput())){
							if(bloomTank.getFluid() == null){
								return true;
							}else{
								if(bloomTank.getFluid().isFluidEqual(recipe.getMolten()) && bloomTank.getFluidAmount() <= bloomTank.getCapacity() - recipe.getMolten().amount){
									return true;
								}
							}
						}
					}
				}
				return false;
			}

	private void addBloom(ItemStack stack) {
		if(stack != null){
			for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
				if(stack.isItemEqual(recipe.getInput())){
					bloomTank.fillInternal(recipe.getMolten(), true);
				}
			}
		}
		input.decrementSlot(INPUT_SLOT);
		if(bloomTank.getFluid().amount >= bloomTank.getCapacity()){bloomTank.getFluid().amount = bloomTank.getCapacity();}
	}

	private boolean canCast() {
		return hasOutput() && canStackOutput(output.getStackInSlot(OUTPUT_SLOT)) && CoreUtils.hasConsumable(ToolUtils.hammer, input.getStackInSlot(CONSUMABLE_SLOT));
	}

			private boolean canStackOutput(ItemStack stack) {
				return (stack == null || (isCorrectOutput(stack) && stack.stackSize < stack.getMaxStackSize()));
			}

					private boolean isCorrectOutput(ItemStack stack) {
						if(stack != null){
							for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
								if(bloomTank.getFluid() != null){
									if(recipe.getMolten().isFluidEqual(bloomTank.getFluid())){
										if(recipe.getOutput() != null){
											if(stack.isItemEqual(recipe.getOutput())){
												return true;
											}
										}
									}
								}
							}
						}
						return false;
					}

	private void castIngot() {
		if(canCast()){
			for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
				if(bloomTank.getFluid() != null && bloomTank.getFluid().isFluidEqual(recipe.getMolten()) && bloomTank.getFluidAmount() >= consumedBloom){
					if(recipe.getOutput() != null){
						moltenStack = recipe.getMolten();
						outputStack = recipe.getOutput();
					}
				}
			}
			output.setOrStack(OUTPUT_SLOT, outputStack);
    		bloomTank.drainInternal(new FluidStack(moltenStack.getFluid(), consumedBloom), true);
    		input.damageSlot(CONSUMABLE_SLOT);
		}
	}

}