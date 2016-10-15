package com.globbypotato.rockhounding_oretiers.blocks;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
public class IronOres extends Block implements IMetaBlockName{

	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumType.class);

	public IronOres(float hardness, float resistance, String name) {
		super(Material.ROCK);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(Reference.RockhoundingTiers);
		setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.byMetadata(0)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumType type = (EnumType) state.getValue(VARIANT);
		return type.getMetadata();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumType.values().length; i++){
			list.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return ModArray.ironOresArray[stack.getItemDamage()];
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this) ;
	}

    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

	public int quantityDropped(Random rand) {
		return 1;
	}
	
	public enum EnumType implements IStringSerializable {
		MAGNETITE		(0,  "magnetite"),
		HEMATITE		(1,  "hematite"),
		GOETHITE		(2,  "goethite"),
		LIMONITE		(3,  "limonite"),
		SIDERITE		(4,  "siderite"),
		BOG				(5,  "bog"),
		TACONITE		(6,  "taconite"),
		BIF				(7,  "bif");
        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private int meta;
		private final String name;

		private EnumType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

        public int getMetadata() {
            return this.meta;
        }

		@Override
		public String toString() {
			return this.getName();
		}

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) { meta = 0; }
            return META_LOOKUP[meta];
        }

        static {
        	EnumType[] metas = values();
            int metaLenght = metas.length;
            for (int x = 0; x < metaLenght; ++x) {
            	EnumType metaIn = metas[x];
                META_LOOKUP[metaIn.getMetadata()] = metaIn;
            }
        }

	}

}