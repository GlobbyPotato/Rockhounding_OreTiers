package com.globbypotato.rockhounding_oretiers.proxy;

import com.globbypotato.rockhounding_oretiers.compat.crafttweaker.CTSupport;
import com.globbypotato.rockhounding_oretiers.compat.top.TopCompat;
import com.globbypotato.rockhounding_oretiers.compat.waila.WailaCompat;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.handlers.GlobbyEventHandler;
import com.globbypotato.rockhounding_oretiers.handlers.GuiHandler;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.OreEventHandler;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.integration.RH_Support;
import com.globbypotato.rockhounding_oretiers.utils.IMCUtils;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraftforge.common.MinecraftForge;
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

		// Regidter Events
		MinecraftForge.EVENT_BUS.register(new GlobbyEventHandler());	

		// Register generator 
		if(ToolUtils.registerAsOres()){
			GameRegistry.registerWorldGenerator(new TiersGenerator(), 1);
		}

		// Mod compatibility
        WailaCompat.init();
        TopCompat.init();
		CTSupport.init();
	}

	/**
	 * @param e  
	 */
	public void init(FMLInitializationEvent e){

		//Register Guis
		NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());
		
		// Register new events
	    MinecraftForge.ORE_GEN_BUS.register(new OreEventHandler());

		//IMC support
		RH_Support.init();
	}

	public void imcInit(IMCEvent event) {
		// Add custom recipes
		IMCUtils.extraRecipes(event.getMessages());
	}

	/**
	 * @param e  
	 */
	public void postInit(FMLPostInitializationEvent e){
		//
	}

}