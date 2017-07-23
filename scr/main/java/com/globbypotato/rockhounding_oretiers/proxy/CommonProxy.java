package com.globbypotato.rockhounding_oretiers.proxy;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.handlers.FuelHandler;
import com.globbypotato.rockhounding_oretiers.handlers.GlobbyEventHandler;
import com.globbypotato.rockhounding_oretiers.handlers.GuiHandler;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.ModDictionary;
import com.globbypotato.rockhounding_oretiers.handlers.ModRecipes;
import com.globbypotato.rockhounding_oretiers.handlers.OreEventHandler;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.integration.crafttweaker.CTSupport;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.utils.IMCUtils;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		// Load Config
		ModConfig.loadConfig(event);

		// Register Fluids
		ModFluids.registerFluidContainers();

		// Register Contents
		ModBlocks.init();
		ModItems.init();

		// Regidter Events
		MinecraftForge.EVENT_BUS.register(new GlobbyEventHandler());	

		// Register Spawning 
		GameRegistry.registerWorldGenerator(new TiersGenerator(), 1);

		// Register fuel handler
		GameRegistry.registerFuelHandler(new FuelHandler());

		// Register oreDictionary
		ModDictionary.loadDictionary();
	}

	public void init(FMLInitializationEvent e){
		// Register Recipes
		ModRecipes.init();
		MachineRecipes.machinesRecipes();

		//Register Guis
		NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());
		
		// Register new events
	    MinecraftForge.ORE_GEN_BUS.register(new OreEventHandler());
	}

	public void imcInit(IMCEvent event) {
		// Add custom recipes
		IMCUtils.extraRecipes(event.getMessages());
	}

	public void postInit(FMLPostInitializationEvent e){
		// Register Craft Tweaker Support
		CTSupport.init();
	}

	public void initFluidModel(Block block, Fluid fluid) {}

}