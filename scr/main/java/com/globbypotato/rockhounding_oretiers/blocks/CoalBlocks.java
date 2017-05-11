package com.globbypotato.rockhounding_oretiers.blocks;

import com.globbypotato.rockhounding_oretiers.blocks.itemblocks.TiersIB;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumCoalBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoalBlocks extends BaseMetaBlock{
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumCoalBlocks.class);

    public CoalBlocks(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound){
        super(material, array, hardness, resistance, name, stepSound);
        GameRegistry.register(new TiersIB(this, EnumCoalBlocks.getNames()).setRegistryName(name));
        setHarvestLevel("pickaxe", 0);
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