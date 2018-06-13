package com.globbypotato.rockhounding_oretiers.blocks.itemblocks;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TierMetaIB extends BaseMetaIB{

	public TierMetaIB(Block block, String[] names) {
		super(block, names);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		switch(meta){
		case 0: return TiersGenerator.VALUE_ANTHRACITE * 9;
		case 1: return TiersGenerator.VALUE_BITUMINOUS * 9;
		case 2: return TiersGenerator.VALUE_LIGNITE * 9;
		case 3: return TiersGenerator.VALUE_PEAT * 9;
		case 4: return TiersGenerator.VALUE_CHARCOAL * 9;
		default: return 0;
		}
	}

}