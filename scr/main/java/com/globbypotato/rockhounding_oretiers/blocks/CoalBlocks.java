package com.globbypotato.rockhounding_oretiers.blocks;

import com.globbypotato.rockhounding_oretiers.blocks.io.MetaIO;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class CoalBlocks extends MetaIO{
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumCoalBlocks.class);

    public CoalBlocks(String name){
		super(name, Material.ROCK, EnumCoalBlocks.getNames(), 2.0F, 4.0F, SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumCoalBlocks.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumCoalBlocks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumCoalBlocks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

}