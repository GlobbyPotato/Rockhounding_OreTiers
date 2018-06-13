package com.globbypotato.rockhounding_oretiers.blocks;

import com.globbypotato.rockhounding_oretiers.blocks.io.MetaIO;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class IronOres extends MetaIO{
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumIronOres.class);

    public IronOres(String name){
		super(name, Material.ROCK, EnumIronOres.getNames(), 1.0F, 2.0F, SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumIronOres.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumIronOres.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumIronOres) state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
}