package com.globbypotato.rockhounding_oretiers.blocks;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.blocks.itemblocks.SeamFireIB;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeamFire extends Block {
	public static final AxisAlignedBB COLLISION = new AxisAlignedBB(0.01D, 0.01D, 0.01D, 0.99D, 0.99D, 0.99D);
    Random rand = new Random();

    public SeamFire(float hardness, float resistance, String name) {
		super(Material.ROCK);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this); 
		GameRegistry.register(new SeamFireIB(this).setRegistryName(name));		
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(Reference.RockhoundingTiers);
		setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState());
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
    	return Item.getItemFromBlock(this);
	}

	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 11;
	}

	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		for(int y = -3; y <= 2; y++){
			for(int x = -3; x <= 3; x++){
    			for(int z = -3; z <= 3; z++){
    				BlockPos spreadPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if(worldIn.getBlockState(spreadPos).getBlock() == Blocks.AIR){
    					if(rand.nextInt(5) == 0){
    						if(!worldIn.isRemote){
    							worldIn.setBlockState(spreadPos, Blocks.FIRE.getDefaultState());
    						}
    					}
					}
    			}
    		}
		}
    }

	@Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn){
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)){
        	entityIn.setFire(8);
        	entityIn.attackEntityFrom(DamageSource.onFire, 3.0F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
    	entityIn.setFire(4);
		entityIn.attackEntityFrom(DamageSource.onFire, 1.5F);
		if(rand.nextInt(10) == 0 ){
        	super.breakBlock(worldIn, pos, state);
		}
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return COLLISION;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos){
        return COLLISION;
    }

}