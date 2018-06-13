package com.globbypotato.rockhounding_oretiers.items.io;

import com.globbypotato.rockhounding_core.items.BaseConsumable;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

public class ConsumableIO extends BaseConsumable{

	public ConsumableIO(String name, int uses) {
		super(Reference.MODID, name, uses);
		setCreativeTab(Reference.RockhoundingTiers);
	}
}