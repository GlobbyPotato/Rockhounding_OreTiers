package com.globbypotato.rockhounding_oretiers.proxy;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.handlers.ModTileEntity;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
  		ModContents.initBlockClient();
  		ModContents.initItemClient();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		ModTileEntity.specoalRenders();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
	@Override
	public void imcInit(IMCEvent e) {
		super.imcInit(e);
	}

	@Override
	public void initFluidModel(Block block, Fluid fluid) {
		final ModelResourceLocation fluidLocation = new ModelResourceLocation(
				Reference.MODID + ":textures/blocks" + block.getUnlocalizedName());

		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidLocation;
			}
		});
	}

}