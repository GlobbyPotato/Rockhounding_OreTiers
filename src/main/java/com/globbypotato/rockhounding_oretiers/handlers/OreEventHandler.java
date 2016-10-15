package com.globbypotato.rockhounding_oretiers.handlers;

import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreEventHandler {
	@SubscribeEvent
    public void generateMineable(GenerateMinable event) {
	    switch(event.getType()) {
		    case IRON:
				event.setResult(Result.DENY);
			    break;
		    case COAL:
				event.setResult(Result.DENY);
			    break;
		    default:
	    }
    }
}
