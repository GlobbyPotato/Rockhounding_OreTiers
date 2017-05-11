package com.globbypotato.rockhounding_oretiers.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.Rhtiers;
import com.globbypotato.rockhounding_oretiers.blocks.itemblocks.FueledMachineIB;
import com.globbypotato.rockhounding_oretiers.fluids.IFluidHandlingTile;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBase;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BaseMachine extends BlockContainer{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ENABLED = PropertyBool.create("enabled");
	final Class<? extends TileEntity> tileClass;
	public int guiID;
	private float top;

	protected BaseMachine(String name, Material material, float hardness, float resistance, Class<? extends TileEntity> tileClass, int guiID, float top) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this); 
		GameRegistry.register(new FueledMachineIB(this).setRegistryName(name));
		setHardness(hardness); setResistance(resistance);	
		setCreativeTab(Reference.RockhoundingTiers);
		String tileName = name.substring(0,1).toUpperCase() + name.substring(1);
		GameRegistry.registerTileEntity(tileClass, tileName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ENABLED, Boolean.valueOf(false)));
        this.tileClass = tileClass;
        this.guiID = guiID;
        this.top = top;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	public void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state){
		if (!worldIn.isRemote){
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 3);
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
		this.setDefaultFacing(worldIn, pos, state);
	}

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

	public static TileEntity getTileEntitySafely(IBlockAccess blockAccess, BlockPos pos) {
		if (blockAccess instanceof ChunkCache) {
			return ((ChunkCache) blockAccess).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
		}else{
			return blockAccess.getTileEntity(pos);
		}
    }

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean cooking = false;
		TileEntity te = getTileEntitySafely(worldIn, pos);
		if(te != null && te instanceof TileEntityBase){
			TileEntityBase mech = (TileEntityBase) te;
			cooking = mech.isCooking();
		}
		return state.withProperty(FACING, state.getValue(FACING)).withProperty(ENABLED, cooking);
	}

    @Override
    public IBlockState getStateFromMeta(int meta){
        EnumFacing enumfacing = EnumFacing.getFront(meta);
        if (enumfacing.getAxis() == EnumFacing.Axis.Y){
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot){
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, ENABLED});
    }

	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
    	worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 3);
    }

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		TileEntity te = getTileEntitySafely(world, pos);
		if (te instanceof TileEntityBase){
			if(te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null)){
				IItemHandler inventory = ((TileEntityBase) te).getInventory();
				int slots = inventory.getSlots();
				for(int i=0;i<slots; i++){
					if(inventory.getStackInSlot(i) != null){
						world.spawnEntityInWorld(new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),inventory.getStackInSlot(i)));
					}
				}
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
    public boolean hasComparatorInputOverride(IBlockState state){
        return true;
    }

	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos){
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		try {
			return tileClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!world.isRemote) {
			if(world.getTileEntity(pos) instanceof IFluidHandlingTile){
				if (heldItem != null){
					if (heldItem.getItem() instanceof ItemBucket || heldItem.getItem() instanceof UniversalBucket){
						((IFluidHandlingTile)world.getTileEntity(pos)).interactWithBucket(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
						return true;
					}
				}
			}
			player.openGui(Rhtiers.instance, guiID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

}