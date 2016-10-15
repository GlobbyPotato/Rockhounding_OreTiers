package com.globbypotato.rockhounding_oretiers.machines.tileentity;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.container.ContainerPeatDrier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class TileEntityPeatDrier extends TileEntityLockable implements ITickable, ISidedInventory {
	public ItemStack[] slots = new ItemStack[2];
    private static final int[] SLOTS_BOTTOM = new int[] {1};
    private static final int[] SLOTS_SIDES = new int[] {0};
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;
    private int dryingSpeed = 2400;
    public static int dryingMultiplier;

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
        return ItemStackHelper.getAndSplit(slots, slot, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
        return ItemStackHelper.getAndRemove(slots, slot);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(slots[index]) && ItemStack.areItemStackTagsEqual(stack, slots[index]);
        slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()){
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag){
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
	}
    public int getCookTime(@Nullable ItemStack stack){
        return dryingSpeed * dryingMultiplier;
    }

    public void setCustomInventoryName(String displayname){
        this.furnaceCustomName = displayname;
    }

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {	}

	@Override
	public void closeInventory(EntityPlayer player) {	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 1 ? false : true;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){
		return direction == EnumFacing.DOWN || index == 1 || stack.getItem() == Items.BUCKET;
    }

	@Override
	public int getField(int id) {
        switch (id){
            case 0:
                return this.cookTime;
            case 1:
                return this.totalCookTime;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
        switch (id){
            case 0:
                this.cookTime = value;
                break;
            case 1:
                this.totalCookTime = value;
        }
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
        for (int i = 0; i < slots.length; ++i){
        	slots[i] = null;
        }
	}

	@Override
	public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.peatDrier";
	}

	@Override
	public boolean hasCustomName() {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : SLOTS_SIDES;
	}

    public String getGuiID(){
        return Reference.MODID + ":peatDrier";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn){
        return new ContainerPeatDrier(playerInventory, this);
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing){
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    public static void func_189676_a(DataFixer p_189676_0_){
        p_189676_0_.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("PeatDrier", new String[] {"Items"}));
    }

	@Override
	public void update() {
		boolean flag = false;
		//polish
		if(!worldObj.isRemote){
			if(canDry()){
	            ++this.cookTime;
				if(cookTime >= (dryingSpeed * dryingMultiplier)) { 
					cookTime = 0; 
					dryPeat(); 
					flag = true;
				}
			}else{
				cookTime = 0;
			}
		}
        if (flag){this.markDirty();}
	}


	private boolean canDry() {
        if (slots[0] == null){
            return false;
        }else{
			ItemStack itemstack = new ItemStack(ModContents.tiersItems, 1, 8);
            if (slots[0].getItem() != ModContents.tiersItems && slots[0].getItemDamage() != 4) return false;
            if (slots[1] == null) return true;
            if (!slots[1].isItemEqual(itemstack)) return false;
            int result = slots[1].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= slots[1].getMaxStackSize();
        }
	}
	
	private void dryPeat() {
		if(canDry()) {
			ItemStack itemstack = new ItemStack(ModContents.tiersItems,1,8);
			handleOutput(itemstack);
		}
	}

	private void handleOutput(ItemStack itemstack) {
		//add output
        if (slots[1] == null){
        	slots[1] = itemstack.copy();
        }else if (slots[1].getItem() == itemstack.getItem()){
        	slots[1].stackSize += itemstack.stackSize;
        }

		//decrease input
        --slots[0].stackSize;
        if (slots[0].stackSize <= 0){
        	slots[0] = null;
        }

	}

    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        slots = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i){
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");
            if (j >= 0 && j < slots.length){
            	slots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        if (compound.hasKey("CustomName", 8)){
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < slots.length; ++i){
            if (slots[i] != null){
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                slots[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        compound.setTag("Items", nbttaglist);
        if (this.hasCustomName()){
            compound.setString("CustomName", this.furnaceCustomName);
        }
        return compound;
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
        readFromNBT(packet.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}	
}
