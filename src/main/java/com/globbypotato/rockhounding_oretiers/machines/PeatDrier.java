package com.globbypotato.rockhounding_oretiers.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.Rhtiers;
import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class PeatDrier extends BlockContainer {
    private static final AxisAlignedBB BOUNDBOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4D, 1.0D);

	public PeatDrier(float hardness, float resistance, String name) {
		super(Material.WOOD);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("axe", 0);
		setCreativeTab(Reference.RockhoundingTiers);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return BOUNDBOX;
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOUNDBOX;
    }

	@Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPeatDrier();
	}

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!worldIn.isRemote) {
			FMLNetworkHandler.openGui(playerIn, Rhtiers.instance, ModContents.peatDrierID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
       worldIn.setBlockState(pos, this.blockState.getBaseState(), 2);
       if (stack.hasDisplayName()){
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityPeatDrier){
                ((TileEntityPeatDrier)tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityPeatDrier){
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityPeatDrier)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(worldIn, pos, state);
    }

    public boolean hasComparatorInputOverride(IBlockState state){
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos){
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

}
