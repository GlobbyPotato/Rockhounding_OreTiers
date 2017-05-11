package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 0) return TiersGenerator.VALUE_ANTHRACITE;	// anthracite
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 1) return TiersGenerator.VALUE_BITUMINOUS;	// bituminous
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 2) return TiersGenerator.VALUE_LIGNITE;		// lignite
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 8) return TiersGenerator.VALUE_PEAT;			// dry peat
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 4) return TiersGenerator.VALUE_CHARCOAL;		// charcoal
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 5) return 200;	// coal pellet
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 0) return TiersGenerator.VALUE_ANTHRACITE * 9;	// anthracite
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 1) return TiersGenerator.VALUE_BITUMINOUS * 9;	// bituminous
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 2) return TiersGenerator.FREQUENCY_LIGNITE * 9;	// lignite
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 3) return TiersGenerator.VALUE_PEAT * 9;			// peat
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 4) return TiersGenerator.VALUE_CHARCOAL * 9;		// charcoal
		return 0;
	}
}