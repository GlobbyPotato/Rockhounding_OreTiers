package com.globbypotato.rockhounding_oretiers.fluids;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

//Courtesy of Choonster, (MIT License) https://github.com/Choonster/TestMod3
public class ModFluids {
	/**
	 * The fluids registered by this mod. Includes fluids that were already registered by another mod.
	 */
	public static final Set<Fluid> FLUIDS = new HashSet<>();

	/**
	 * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
	 */
	public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

	public static final Fluid BLOOM = createFluid("bloom", true,
		fluid -> fluid.setDensity(60000).setLuminosity(5).setTemperature(1300).setViscosity(60000).canBePlacedInWorld(),
		fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.RED)){
			@Override
		    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
				if(entityIn instanceof EntityLivingBase){
					((EntityLivingBase)entityIn).attackEntityFrom(DamageSource.onFire, 6.0F);
				}
			}
		}
	);

	/**
	 * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
	 *
	 * @param name                 The name of the fluid
	 * @param hasFlowIcon          Does the fluid have a flow icon?
	 * @param fluidPropertyApplier A function that sets the properties of the {@link Fluid}
	 * @param blockFactory         A function that creates the {@link IFluidBlock}
	 * @return The fluid and block
	 */
	private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
		final String texturePrefix = Reference.MODID + ":fluids/";
		final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
		final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

		if (useOwnFluid) {
			fluidPropertyApplier.accept(fluid);
			MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
		} else {
			fluid = FluidRegistry.getFluid(name);
		}
		FLUIDS.add(fluid);
		return fluid;
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		/**
		 * Register this mod's fluid {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				block.setRegistryName(Reference.MODID, "fluid." + fluidBlock.getFluid().getName());
				block.setUnlocalizedName(Reference.MODID + ":" + fluidBlock.getFluid().getUnlocalizedName());
				block.setCreativeTab(Reference.RockhoundingTiers);
				registry.register(block);
			}
		}

		/**
		 * Register this mod's fluid {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				final ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				registry.register(itemBlock);
			}
		}
	}

	public static void registerFluidContainers() {
		for (final Fluid fluid : FLUIDS) {
			registerBucket(fluid);
		}
	}

	private static void registerBucket(Fluid fluid) {
		FluidRegistry.addBucketForFluid(fluid);
	}

}