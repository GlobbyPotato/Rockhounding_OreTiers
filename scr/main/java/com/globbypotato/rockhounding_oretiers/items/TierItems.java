package com.globbypotato.rockhounding_oretiers.items;

import com.globbypotato.rockhounding_oretiers.items.io.ArrayIO;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.item.ItemStack;

public class TierItems extends ArrayIO{

	public TierItems(String name, String[] array) {
		super(name, array);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		switch(meta){
		case 0: return TiersGenerator.VALUE_ANTHRACITE;
		case 1: return TiersGenerator.VALUE_BITUMINOUS;
		case 2: return TiersGenerator.VALUE_LIGNITE;
		case 3: return 50;
		case 4: return TiersGenerator.VALUE_CHARCOAL;
		case 5: return 200;
		case 6: return TiersGenerator.VALUE_PEAT;
		default: return 0;
		}
	}
}