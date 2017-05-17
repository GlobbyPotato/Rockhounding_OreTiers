package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.fluids.IFluidHandlingTile;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.ModRecipes;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBloomery;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_oretiers.utils.FuelUtils;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;
import com.globbypotato.rockhounding_oretiers.utils.Utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityBloomery extends TileEntityBase implements IFluidHandlingTile {
	private ItemStackHandler template = new TemplateStackHandler(1);

	public static final int CONSUMABLE_SLOT = 2;
	public static int consumedBloom;

	public FluidTank bloomTank;
	public int powerMax = 32000;
	public int powerCount;
	public int castTime;
	public boolean drainScan;

	FluidStack moltenStack;
	ItemStack outputStack;

	public TileEntityBloomery() {
		super(3, 1, 1);

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

		input =  new MachineStackHandler(INPUT_SLOTS, this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && hasRecipe(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == FUEL_SLOT && FuelUtils.isItemFuel(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == CONSUMABLE_SLOT && Utils.areItemsEqualIgnoreMeta(new ItemStack(ModContents.forgeHammer), insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input,WriteMode.IN_OUT);
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
		return ModRecipes.bloomeryRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getInput() != null && stack.isItemEqual(recipe.getInput()));
	}

	public static boolean moltenHasRecipe(FluidStack stack){
		return ModRecipes.bloomeryRecipe.stream().anyMatch(
				recipe -> stack != null && recipe.getMolten() != null && recipe.getMolten().isFluidEqual(stack));
	}

	public boolean hasOutput(){
		return ModRecipes.bloomeryRecipe.stream().anyMatch(
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

	protected void fuelHandler() {
		if(input.getStackInSlot(FUEL_SLOT) != null && FuelUtils.isItemFuel(input.getStackInSlot(FUEL_SLOT)) ){
			if( powerCount <= (powerMax - FuelUtils.getItemBurnTime(input.getStackInSlot(FUEL_SLOT))) ){
				burnFuel();
			}
		}
	}

	protected void burnFuel() {
		powerCount += FuelUtils.getItemBurnTime(input.getStackInSlot(FUEL_SLOT));
		ItemStack stack = input.getStackInSlot(FUEL_SLOT);
		stack.stackSize--;
		input.setStackInSlot(FUEL_SLOT, stack);
		if(input.getStackInSlot(FUEL_SLOT).stackSize <= 0){
			input.setStackInSlot(FUEL_SLOT, input.getStackInSlot(FUEL_SLOT).getItem().getContainerItem(input.getStackInSlot(FUEL_SLOT)));
		}
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
		this.powerCount = compound.getInteger("PowerCount");
		this.castTime = compound.getInteger("CastTime");
		this.drainScan = compound.getBoolean("DrainScan");
		this.bloomTank.readFromNBT(compound.getCompoundTag("BloomTank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("PowerCount", this.powerCount);
		compound.setInteger("CastTime", this.castTime);
		compound.setBoolean("DrainScan", this.drainScan);
		NBTTagCompound bloomTankNBT = new NBTTagCompound();
		this.bloomTank.writeToNBT(bloomTankNBT);
		compound.setTag("BloomTank", bloomTankNBT);
		return compound;
	}

	@Override
	public boolean interactWithBucket(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean didFill = FluidUtil.interactWithFluidHandler(heldItem, this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), player);
		this.markDirtyClient();
		return didFill;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
		else return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) bloomTank;
		return super.getCapability(capability, facing);
	}



	//-------------- PROCESS ---------------- 
	@Override
	public void update() {
		if(input.getStackInSlot(FUEL_SLOT) != null){fuelHandler();}
		if(canSmelt()){
			cookTime++; powerCount--;
			if(cookTime >= getCookTime()) { 
				cookTime = 0; 
				addBloom(input.getStackInSlot(INPUT_SLOT)); 
				this.markDirtyClient();
			}
		}else{
			cookTime = 0;
		}
		
		if(canCast()){
			castTime++;
			if(castTime >= getCookTime()) { 
				castTime = 0; 
				castIngot(); 
				this.markDirtyClient();
			}
		}else{
			castTime = 0;
		}
		burnState();
	}

	private boolean canSmelt() {
		return powerCount >= getCookTime() && (hasRecipe(input.getStackInSlot(INPUT_SLOT)) || isValidOredict(input.getStackInSlot(INPUT_SLOT))) && canContainBloom(input.getStackInSlot(INPUT_SLOT));
	}

			private boolean isValidOredict(ItemStack stack) {
				if(stack != null){
					ArrayList<Integer> inputOreIDs = Utils.intArrayToList(OreDictionary.getOreIDs(stack));
					for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
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
					for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
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
			for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
				if(stack.isItemEqual(recipe.getInput())){
					bloomTank.fillInternal(recipe.getMolten(), true);
				}
			}
		}
		input.decrementSlot(INPUT_SLOT);
		if(bloomTank.getFluid().amount >= bloomTank.getCapacity()){bloomTank.getFluid().amount = bloomTank.getCapacity();}
	}

	private boolean canCast() {
		return hasOutput() && canStackOutput(output.getStackInSlot(OUTPUT_SLOT)) && ToolUtils.hasConsumable(ToolUtils.hammer, input.getStackInSlot(CONSUMABLE_SLOT));
	}

			private boolean canStackOutput(ItemStack stack) {
				return (stack == null || (isCorrectOutput(stack) && stack.stackSize < stack.getMaxStackSize()));
			}

					private boolean isCorrectOutput(ItemStack stack) {
						if(stack != null){
							for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
								if(recipe.getOutput() != null){
									if(stack.isItemEqual(recipe.getOutput())){
										return true;
									}
								}
							}
						}
						return false;
					}

	private void castIngot() {
		if(canCast()){
			for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
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