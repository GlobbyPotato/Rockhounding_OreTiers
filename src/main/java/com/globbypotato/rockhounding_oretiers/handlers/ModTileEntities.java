package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.*;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityPeatDrier.class, "PeatDrier");
		GameRegistry.registerTileEntity(TileEntityCoalRefiner.class, "CoalRefiner");
	}

}
