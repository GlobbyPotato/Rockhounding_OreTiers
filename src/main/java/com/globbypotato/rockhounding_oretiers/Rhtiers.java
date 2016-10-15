package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.handlers.FuelHandler;
import com.globbypotato.rockhounding_oretiers.handlers.GuiHandler;
import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.ModDictionary;
import com.globbypotato.rockhounding_oretiers.handlers.ModRecipes;
import com.globbypotato.rockhounding_oretiers.handlers.ModTileEntities;
import com.globbypotato.rockhounding_oretiers.handlers.OreEventHandler;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.proxy.CommonProxy;
import com.globbypotato.rockhounding_oretiers.world.TiersOresGenerator;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class Rhtiers {

	@Instance(Reference.MODID)
	public static Rhtiers instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy globbypotatoProxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Load Config
		ModConfig.loadConfig(event);

		// Load Arrays
		ModArray.loadArray();

		// Register Contents
		ModContents.init();
		ModContents.register();

		// Register Spawning 
		GameRegistry.registerWorldGenerator(new TiersOresGenerator(), 1);

		// Register fuel handler
		GameRegistry.registerFuelHandler(new FuelHandler());

		// Register oreDictionary
		ModDictionary.loadDictionary();

		// Register new Renders
		globbypotatoProxy.registerRenders(); 
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
        // Register Recipes
		ModRecipes.init();

		//Register Guis
		NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());

		// Register tile entities
		ModTileEntities.registerTileEntities();

		// Register new events
	    MinecraftForge.ORE_GEN_BUS.register(new OreEventHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}