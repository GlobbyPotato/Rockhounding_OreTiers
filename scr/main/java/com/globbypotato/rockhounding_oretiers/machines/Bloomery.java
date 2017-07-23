package com.globbypotato.rockhounding_oretiers.machines;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.handlers.GuiHandler;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Bloomery extends BaseMachine {

	public Bloomery(float hardness, float resistance, String name) {
		super(name, Material.ROCK, TileEntityBloomery.class, GuiHandler.bloomeryID, 2.0F);
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 0);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
    	TileEntity te = getTileEntitySafely(worldIn, pos);
		if (te != null && te instanceof TileEntityBloomery){
			TileEntityBloomery bloomery = (TileEntityBloomery) te; 
			if(bloomery.isCooking()){
				double d0 = (double)pos.getX() + 0.5D;
	            double d1 = (double)pos.getY() + 1.9D;
	            double d2 = (double)pos.getZ() + 0.5D;
	            if (rand.nextDouble() < 0.1D){
	                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	            }
	            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
	            
	            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
	            double f0 = (double)pos.getX() + 0.5D;
	            double f1 = (double)pos.getY() + rand.nextDouble() * 1.5D / 16.0D;
	            double f2 = (double)pos.getZ() + 0.5D;
	            double f3 = 0.52D;
	            double f4 = rand.nextDouble() * 0.6D - 0.3D;
	
	            if (rand.nextDouble() < 0.1D){
	                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
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
        	TileEntityBloomery te = (TileEntityBloomery)getTileEntitySafely(worldIn, pos);
			if(te != null){
        		if(stack.getTagCompound().hasKey("Bloom")){
        			te.bloomTank.setFluid(FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("Bloom")));
        		}
			}
		}
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);
        java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
        if(te != null && te instanceof TileEntityBloomery){
  			addNbt(itemstack, te);
        }
        if (itemstack != null){ items.add(itemstack); }
        net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
        for (ItemStack item : items){ spawnAsEntity(worldIn, pos, item); }
    }

	private void addNbt(ItemStack itemstack, TileEntity tileentity) {
		TileEntityBloomery bloomery = ((TileEntityBloomery)tileentity);
		itemstack.setTagCompound(new NBTTagCompound());
    	addPowerNbt(itemstack, tileentity);
		NBTTagCompound bloom = new NBTTagCompound(); 
		if(bloomery.bloomTank.getFluid() != null){
			bloomery.bloomTank.getFluid().writeToNBT(bloom);
			itemstack.getTagCompound().setTag("Bloom", bloom);
		}
	}

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

}