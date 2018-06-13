package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.renders.RendererCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.renders.RendererPeatDrier;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModRenderers {

	public static void specialRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPeatDrier.class, new RendererPeatDrier());		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoalRefiner.class, new RendererCoalRefiner());		
	}
}