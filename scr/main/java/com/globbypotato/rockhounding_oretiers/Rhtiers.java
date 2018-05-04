package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.proxy.CommonProxy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION, dependencies = "required-after:rockhounding_core@[1.09,);")
public class Rhtiers {

	@Instance(Reference.MODID)
	public static Rhtiers instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy globbypotatoProxy;

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		globbypotatoProxy.preInit(event);
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		globbypotatoProxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		globbypotatoProxy.postInit(event);
	}

	@EventHandler
	public void handleIMCMessage(FMLInterModComms.IMCEvent event) {
		globbypotatoProxy.imcInit(event);
	}

}