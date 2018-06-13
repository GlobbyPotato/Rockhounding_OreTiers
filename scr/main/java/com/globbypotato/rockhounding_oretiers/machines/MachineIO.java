package com.globbypotato.rockhounding_oretiers.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.machines.BaseMachine;
import com.globbypotato.rockhounding_core.machines.tileentity.IFluidHandlingTile;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.Rhtiers;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MachineIO extends BaseMachine{
    public static final PropertyBool ENABLED = PropertyBool.create("enabled");
    float top;

	public MachineIO(String modid, String name, Material material, Class<? extends TileEntity> tileClass, int guiID, float top) {
		super(modid, name, material, tileClass, guiID);
		this.top = top;
		setCreativeTab(Reference.RockhoundingTiers);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ENABLED, Boolean.valueOf(false)));
	}

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean cooking = false;
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TileEntityInv){
			TileEntityInv mech = (TileEntityInv) te;
			cooking = mech.isCooking();
		}
		return state.withProperty(FACING, state.getValue(FACING)).withProperty(ENABLED, cooking);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if (!world.isRemote) {
			if(world.getTileEntity(pos) instanceof IFluidHandlingTile){
				if(!player.getHeldItemMainhand().isEmpty()){
					if (CoreUtils.isBucketType(player.getHeldItemMainhand())){
						((IFluidHandlingTile)world.getTileEntity(pos)).interactWithFluidHandler(player, hand, world, pos, facing);
						return true;
					}
				}
			}
			player.openGui(Rhtiers.instance, this.guiID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, ENABLED});
    }

}