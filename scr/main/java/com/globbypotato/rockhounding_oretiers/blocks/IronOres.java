package com.globbypotato.rockhounding_oretiers.blocks;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class IronOres extends BlockIO{
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumIronOres.class);

    public IronOres(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound){
        super(material, array, hardness, resistance, name, stepSound);
        GameRegistry.register(new BaseMetaIB(this, EnumIronOres.getNames()	).setRegistryName(name));
        setHarvestLevel("pickaxe", 0);
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