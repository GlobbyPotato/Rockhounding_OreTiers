package com.globbypotato.rockhounding_oretiers.machines;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoalRefiner extends BaseMachine {

	public CoalRefiner(float hardness, float resistance, String name) {
		super(name, Material.ROCK, resistance, resistance, TileEntityCoalRefiner.class, ModContents.coalRefinerID, 1.0F);
		setHarvestLevel("pickaxe", 0);
		setSoundType(SoundType.STONE);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
        if (isDirectlyHeated(worldIn, pos) || isHopperHeated(worldIn, pos)){
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 1D;
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D){
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

	private boolean isHopperHeated(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() == Blocks.HOPPER && (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-2, pos.getZ())).getBlock() instanceof BlockTorch || worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-2, pos.getZ())).getBlock() == Blocks.FIRE);
	}

	private boolean isDirectlyHeated(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() instanceof BlockTorch || worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() == Blocks.FIRE;
	}

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

}