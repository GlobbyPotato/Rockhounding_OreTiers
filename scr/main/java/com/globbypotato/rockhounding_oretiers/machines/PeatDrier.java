package com.globbypotato.rockhounding_oretiers.machines;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PeatDrier extends BaseMachine {

	public PeatDrier(float hardness, float resistance, String name) {
		super(name, Material.WOOD, resistance, resistance, TileEntityPeatDrier.class, ModContents.peatDrierID, 0.2F);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
    	TileEntity te = getTileEntitySafely(worldIn, pos);
		if (te != null && te instanceof TileEntityPeatDrier){
			TileEntityPeatDrier drier = (TileEntityPeatDrier) te; 
			if(drier.isCooking()){
				double d0 = (double)pos.getX() + rand.nextFloat();
	            double d1 = (double)pos.getY() + 0.4D;
	            double d2 = (double)pos.getZ() + rand.nextFloat();
	            worldIn.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
        }
    }

}