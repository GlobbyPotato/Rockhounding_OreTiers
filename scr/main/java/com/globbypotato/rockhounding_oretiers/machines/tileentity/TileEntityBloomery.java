package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TemplateStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityFueledTank;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.gui.GuiBase;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;

import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.templates.FluidHandlerConcatenate;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityBloomery extends TileEntityFueledTank {
	public static int inputSlots = 3;
	public static int outputSlots = 1;
	public static int templateSlots = 1;
	private ItemStackHandler template = new TemplateStackHandler(templateSlots);

	public static int consumedBloom;
	private boolean cooking;

	public FluidTank bloomTank;
	public int castTime;
	public boolean drainScan;

	public TileEntityBloomery() {
		super(inputSlots, outputSlots, templateSlots);

		this.bloomTank = new FluidTank(Fluid.BUCKET_VOLUME + ModConfig.tankCapacity){
			@Override  
			public boolean canFillFluidType(FluidStack fluidin){
				return moltenHasRecipe(fluidin);
		    }

			@Override
		    public boolean canDrain(){
		        return canDrainTank();
		    }
		};
		this.bloomTank.setTileEntity(this);

		this.input =  new MachineStackHandler(inputSlots, this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && isValidInput(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if (slot == fuelID() && isGatedPowerSource(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == CONSUMABLE_SLOT && CoreUtils.hasConsumable(ToolUtils.hammer, insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(this.input, WriteMode.IN);
		this.markDirtyClient();
	}



	//----------------------- SLOTS -----------------------
	public ItemStack inputSlot(){
		return this.input.getStackInSlot(INPUT_SLOT);
	}

	public ItemStack outputSlot(){
		return this.output.getStackInSlot(OUTPUT_SLOT);
	}

	public ItemStack hammerSlot(){
		return this.input.getStackInSlot(CONSUMABLE_SLOT);
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
		compound.setInteger("CastTime", getCastTime());
		compound.setBoolean("DrainScan", isDraining());
		
		NBTTagCompound bloomTankNBT = new NBTTagCompound();
		this.bloomTank.writeToNBT(bloomTankNBT);
		compound.setTag("BloomTank", bloomTankNBT);
		return compound;
	}

	@Override
	public FluidHandlerConcatenate getCombinedTank(){
		return new FluidHandlerConcatenate(this.lavaTank, this.bloomTank);
	}



	//-------------- HANDLERS ---------------- 
	public ItemStackHandler getTemplate(){
		return this.template;
	}

	public int getCooktimeMax(){
        return ModConfig.bloomingSpeed;
    }

	@Override
	public int getGUIHeight() {
		return GuiBase.HEIGHT;
	}

	public String getName() {
		return "bloomery";
	}

	@Override
	public int fuelID() {
		return 1;
	}



	//-------------- CUSTOM ---------------- 
	public boolean isDraining(){
		return this.drainScan;
	}

	public int getCastTime(){
		return this.castTime;
	}

	public int getConsumedBloom(){
		return this.consumedBloom;
	}

	public boolean isBurning(){
		return this.cooking;
	}

	public boolean canDrainTank() {
		return this.bloomTank.getFluid() != null && this.bloomTank.getFluidAmount() > 0 && isDraining();
	}

	@Override
	public boolean isCooking(){
		return (canSmelt() && getCooktime() > 0) || (canCast() && getCastTime() > 0);
	}

	private void burnState(){
		if(this.world.isRemote){
			if (isCooking() != isBurning()) {
				this.cooking = isCooking();
				this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
			}
		}
	}


	
	//-------------- RECIPE ---------------- 
	public ArrayList<BloomeryRecipes> recipeList(){
		return MachineRecipes.bloomeryRecipe;
	}

	public BloomeryRecipes getRecipeList(int x){
		return recipeList().get(x);
	}

	public BloomeryRecipes getCurrentMelting(){
		if(!inputSlot().isEmpty()){
			for(int x = 0; x < recipeList().size(); x++){
				if(!getRecipeList(x).getInput().isEmpty() && CoreUtils.isMatchingIngredient(inputSlot(), getRecipeList(x).getInput())){
					return getRecipeList(x);
				}
			}
		}
		return null;
	}

	public boolean isValidMelting() {
		return getCurrentMelting() != null;
	}

	public FluidStack recipeMolten(){
		return isValidMelting() ? getCurrentMelting().getMolten().copy() : null;
	}

	public BloomeryRecipes getCurrentCasting(){
		if(this.bloomTank.getFluid() != null){
			for(int x = 0; x < recipeList().size(); x++){
				if(getRecipeList(x).getMolten() != null && this.input.canDrainFluid(this.bloomTank.getFluid(), getRecipeList(x).getMolten(), getConsumedBloom())){
					return getRecipeList(x);
				}
			}
		}
		return null;
	}

	public boolean isValidCasting() {
		return getCurrentCasting() != null;
	}

	public ItemStack recipeOutput(){
		return isValidCasting() ? getCurrentCasting().getOutput().copy() : ItemStack.EMPTY;
	}

	boolean isValidInput(ItemStack stack) {
		if(!stack.isEmpty()){
			ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(stack));
			if(!inputOreIDs.isEmpty()){
				for(BloomeryRecipes recipe: recipeList()){
					ArrayList<Integer> recipeOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
					if(!recipeOreIDs.isEmpty()){
						if(CoreUtils.compareDictArrays(inputOreIDs, recipeOreIDs)){
							return true;
						}
					}
				}
			}
			return recipeList().stream().anyMatch(recipe -> !stack.isEmpty() && !recipe.getInput().isEmpty() && stack.isItemEqual(recipe.getInput()));
		}
		return false;
	}
	
	public boolean moltenHasRecipe(FluidStack stack){
		return recipeList().stream().anyMatch(
				recipe -> stack != null && recipe.getMolten() != null && recipe.getMolten().isFluidEqual(stack));
	}



	//-------------- PROCESS ---------------- 
	@Override
	public void update() {
		if(!this.world.isRemote){
			boolean flag = false;
			handleSupplies();

			if(!this.inputSlot().isEmpty()){
				if(canSmelt()){
					this.cooktime++; 
					this.powerCount--;
					if(getCooktime() >= getCooktimeMax()) { 
						addBloom(); 
						this.cooktime = 0; 
						this.markDirtyClient();
					}
					if(!flag) flag = true;
				}else{
					tickOff();
				}
			}else{
				tickOff();
			}

			if(hasBloom()){
				if(canCast()){
					this.castTime++;
					if(getCastTime() >= getCooktimeMax()) { 
						castIngot(); 
						this.castTime = 0; 
					}
					if(!flag) flag = true;
				}else{
					if(this.getCastTime() > 0){
						this.castTime = 0;
						this.markDirtyClient();
					}
				}
			}else{
				if(this.getCastTime() > 0){
					this.castTime = 0;
					this.markDirtyClient();
				}
			}

			if(flag){
				this.markDirtyClient();
			}
		}

		burnState();
	}

	private void handleSupplies() {
		fuelHandler(this.input.getStackInSlot(fuelID()));
		lavaHandler();
	}

	private boolean hasBloom() {
		return this.bloomTank.getFluid() != null && this.bloomTank.getFluidAmount() >= getConsumedBloom();
	}

	private boolean canSmelt() {
		return isValidMelting() 
			&& getPower() > 0
			&& this.input.canSetOrFillFluid(this.bloomTank, this.bloomTank.getFluid(), getCurrentMelting().getMolten());
	}

	private void addBloom() {
		this.input.setOrFillFluid(this.bloomTank, recipeMolten());
		this.input.decrementSlot(INPUT_SLOT);
	}

	private boolean canCast() {
		return isValidCasting() 
			&& this.output.canSetOrStack(outputSlot(), recipeOutput()) 
			&& CoreUtils.hasConsumable(ToolUtils.hammer, hammerSlot());
	}

	private void castIngot() {
		int unbreakingLevel = CoreUtils.getEnchantmentLevel(Enchantments.UNBREAKING, hammerSlot());
		this.output.setOrStack(OUTPUT_SLOT, recipeOutput());
		this.input.drainOrCleanFluid(this.bloomTank, getConsumedBloom(), true);
		this.input.damageUnbreakingSlot(unbreakingLevel, CONSUMABLE_SLOT);
	}

}