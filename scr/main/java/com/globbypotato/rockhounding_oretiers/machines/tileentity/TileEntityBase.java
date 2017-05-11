package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public abstract class TileEntityBase extends TileEntity implements ITickable {
	public final int INPUT_SLOTS;
	public final int OUTPUT_SLOTS;
	public int FUEL_SLOT;
	public int SIZE;
	protected MachineStackHandler input;
	protected IItemHandlerModifiable automationInput;
	protected MachineStackHandler output;
	protected IItemHandlerModifiable automationOutput;

	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 0;

	public abstract int getGUIHeight();

    public int cookTime;
    public boolean cooking;
    
    public TileEntityBase(int inputSlots, int outputSlots, int fuelSlot){
		this.INPUT_SLOTS = inputSlots;
		this.OUTPUT_SLOTS = outputSlots;
		this.FUEL_SLOT = fuelSlot;
		this.SIZE = INPUT_SLOTS + OUTPUT_SLOTS;

		input = new MachineStackHandler(inputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){
					return super.insertItem(slot, stack, simulate);
			}
		};
		automationInput = new WrappedItemHandler(input, WrappedItemHandler.WriteMode.IN_OUT);

		output = new MachineStackHandler(outputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){
				return stack;
			}
			@Override
		    protected void onContentsChanged(int slot){
				this.tile.markDirty();
		    }
		};
		automationOutput = new WrappedItemHandler(output, WrappedItemHandler.WriteMode.IN_OUT){
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate){
				ItemStack stack = getStackInSlot(slot);
				if(stack!= null) return super.extractItem(slot, amount, simulate);
				else return null;
			}
		};
    }

	public IItemHandler getOutput() {
		return this.output;
	}

	public IItemHandler getInput() {
		return this.input;
	}

	public IItemHandler getInventory(){
		return new CombinedInvWrapper(input,output);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if(facing == EnumFacing.DOWN){
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(automationOutput);
			}else{
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(automationInput);
			}
		}
		return super.getCapability(capability, facing);
	}

	public int getInventoryStackLimit(){
		return 64;
	}
	
	public boolean isCooking() {
		return false;
	}

	@Override
	public void update() {}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	//Courtesy of mcjtylib
	public void markDirtyClient() {
		markDirty();
		if (worldObj != null) {
			IBlockState state = worldObj.getBlockState(getPos());
			worldObj.notifyBlockUpdate(getPos(), state, state, 3);
		}
	}

	@Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
		input.deserializeNBT(compound.getCompoundTag("input"));
		output.deserializeNBT(compound.getCompoundTag("output"));
        this.cookTime = compound.getInteger("CookTime");
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("CookTime", this.cookTime);
		compound.setTag("input", input.serializeNBT());
		compound.setTag("output", output.serializeNBT());
        return compound;
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = getUpdateTag();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
	    NBTTagCompound tag = packet.getNbtCompound();
	    handleUpdateTag(tag);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		return nbtTagCompound;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag){
		this.readFromNBT(tag);
	}

}