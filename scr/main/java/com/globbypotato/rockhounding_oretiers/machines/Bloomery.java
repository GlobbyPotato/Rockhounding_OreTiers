package com.globbypotato.rockhounding_oretiers.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.enums.EnumFluidNbt;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityFueledTank;
import com.globbypotato.rockhounding_core.utils.MachinesUtils;
import com.globbypotato.rockhounding_oretiers.handlers.GuiHandler;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Bloomery extends MachineIO {

	public Bloomery(String name) {
		super(Reference.MODID, name, Material.ROCK, TileEntityBloomery.class, GuiHandler.bloomeryID, 2.0F);
		setHardness(3.0F); setResistance(5.0F);	
		setHarvestLevel("pickaxe", 0);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
    	TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityBloomery){
			TileEntityBloomery bloomery = (TileEntityBloomery) te; 
			if(bloomery.isCooking()){
				double d0 = pos.getX() + 0.5D;
	            double d1 = pos.getY() + 1.9D;
	            double d2 = pos.getZ() + 0.5D;
	            if (rand.nextDouble() < 0.1D){
	                worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	            }
	            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);

	            EnumFacing enumfacing = stateIn.getValue(FACING);
	            double f0 = pos.getX() + 0.5D;
	            double f1 = pos.getY() + rand.nextDouble() * 1.5D / 16.0D;
	            double f2 = pos.getZ() + 0.5D;
	            double f4 = rand.nextDouble() * 0.6D - 0.3D;
	
	            if (rand.nextDouble() < 0.1D){
	                worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	            }
	
	            switch (enumfacing){
	                case WEST:
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, f0 + 0.52D, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
	                    break;
	                case EAST:
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, f0 - 0.52D, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
	                    break;
	                case NORTH:
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, f0 + f4, f1, f2 + 0.52D, 0.0D, 0.0D, 0.0D, new int[0]);
	                    break;
	                case SOUTH:
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, f0 + f4, f1, f2 - 0.52D, 0.0D, 0.0D, 0.0D, new int[0]);
				default:
					break;
	            }

			}
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
		if(stack.hasTagCompound()){
        	TileEntity te = worldIn.getTileEntity(pos);
			if(te != null){
		        if(te instanceof TileEntityFueledTank){
		        	MachinesUtils.restoreMachineNBT(stack, te, -1);
		        }
		        if(te instanceof TileEntityFueledTank){
		        	restoreBloomeryNBT(stack, te);
		        }
			}
		}
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);
        List<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
        if(te != null){
    		itemstack.setTagCompound(new NBTTagCompound());
	    	if(te instanceof TileEntityFueledTank){
	    		MachinesUtils.addMachineNbt(itemstack, te);
	    	}
	        if(te != null && te instanceof TileEntityBloomery){
	  			addBloomeryNbt(itemstack, te);
	        }
        }
        if (!itemstack.isEmpty()){ items.add(itemstack); }
        ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
        for (ItemStack item : items){ spawnAsEntity(worldIn, pos, item); }
    }

	private static void addBloomeryNbt(ItemStack itemstack, TileEntity tileentity) {
		TileEntityBloomery bloomery = ((TileEntityBloomery)tileentity);
		NBTTagCompound bloom = new NBTTagCompound(); 
		if(bloomery.bloomTank.getFluid() != null){
			bloomery.bloomTank.getFluid().writeToNBT(bloom);
			itemstack.getTagCompound().setTag(EnumFluidNbt.FLUID.nameTag(), bloom);
		}
	}

    private static void restoreBloomeryNBT(ItemStack stack, TileEntity te) {
    	TileEntityBloomery tank = ((TileEntityBloomery)te);
    	if(stack.hasTagCompound() && tank != null){
			if(stack.getTagCompound().hasKey(EnumFluidNbt.FLUID.nameTag())){
				tank.bloomTank.setFluid(FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(EnumFluidNbt.FLUID.nameTag())));
			}
    	}
	}

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

}