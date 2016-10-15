package com.globbypotato.rockhounding_oretiers.blocks;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoalOres extends Block implements IMetaBlockName{

	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumType.class);
    
	public CoalOres(float hardness, float resistance, String name) {
		super(Material.ROCK);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 0);
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
		return ModArray.coalOresArray[stack.getItemDamage()];
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
    	return ModContents.tiersItems;
	}

    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

	public int quantityDropped(Random rand) {
		return 1;
	}
	
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random){
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)){
            int j = random.nextInt(fortune + 2) - 1;
            if (j < 0){j = 0;}
            return this.quantityDropped(random) * (j + 1);
        }else{
            return this.quantityDropped(random);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune){
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getMetaFromState(state) < 5 && this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)){
            int j = 0;
            j = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            return j;
        }
        return 0;
    }

	public enum EnumType implements IStringSerializable {
		ANTHRACITE		(0,  "anthracite"), 
		BITUMINOUS		(1,  "bituminous"),
		LIGNITE			(2,  "lignite"),
		PEAT			(3,  "peat");
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