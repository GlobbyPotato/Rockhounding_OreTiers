package com.globbypotato.rockhounding_oretiers.blocks;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.blocks.io.MetaIO;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CoalOres extends MetaIO{
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumCoalOres.class);

    public CoalOres(String name){
		super(name, Material.ROCK, EnumCoalOres.getNames(), 2.0F, 4.0F, SoundType.STONE);
		setHarvestLevel("shovel", 0, this.blockState.getBaseState().withProperty(VARIANT, EnumCoalOres.PEAT));
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumCoalOres.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumCoalOres.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumCoalOres) state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
    	return ModItems.TIER_ITEMS;
	}

	@Override
    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random){
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune)){
            int i = random.nextInt(fortune + 2) - 1;
            if (i < 0){
                i = 0;
            }
            return this.quantityDropped(random) * (i + 1);
        }
		return this.quantityDropped(random);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune){
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)){
            int j = 0;
            j = MathHelper.getInt(rand, 0, 2);
            return j;
        }
        return 0;
    }
}