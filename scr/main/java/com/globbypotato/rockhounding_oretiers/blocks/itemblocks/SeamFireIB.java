package com.globbypotato.rockhounding_oretiers.blocks.itemblocks;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeamFireIB extends ItemBlock {
	public SeamFireIB(Block block) {
        super(block);
	}

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack);
    }

    @Override
	public int getMetadata(int meta){
		return meta;
	}

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        if(block == Blocks.CAULDRON){
            int i = ((Integer)iblockstate.getValue(BlockCauldron.LEVEL)).intValue();
            if(i == 3){
	        	if(playerIn.getHeldItem(EnumHand.MAIN_HAND) != null && playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(ModContents.seamFire)){
	                if (!worldIn.isRemote){
                        if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Blocks.COAL_BLOCK))){
                        	playerIn.dropItem(new ItemStack(Blocks.COAL_BLOCK), false);
                        }
                		((BlockCauldron) block).setWaterLevel(worldIn, pos, iblockstate, 0);
        		        playerIn.addStat(StatList.CAULDRON_USED);
	                }
                    playerIn.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 0.5F, 1.5F);
                    spawnParticles(worldIn, iblockstate, pos);
        			playerIn.getHeldItem(EnumHand.MAIN_HAND).stackSize--;
			        return EnumActionResult.SUCCESS;
	        	}else{
	    	        return EnumActionResult.FAIL;
	        	}
        	}else{
    	        return EnumActionResult.FAIL;
        	}
	    }else{
	        if (!block.isReplaceable(worldIn, pos)){
	            pos = pos.offset(facing);
	        }
	        if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack)){
	            int i = this.getMetadata(stack.getMetadata());
	            IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, i, playerIn);
	            if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1)){
	                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
	                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	                --stack.stackSize;
	            }
	            return EnumActionResult.SUCCESS;
	        }else{
	            return EnumActionResult.FAIL;
	        }
	    }
    }

    Random rand = new Random();
	private void spawnParticles(World worldIn, IBlockState iblockstate, BlockPos pos) {
		for (int p = 0; p < 8; p++){
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + 1D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

}