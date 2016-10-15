package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 0) return 3200;	// anthracite
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 1) return 2400;	// bituminous
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 2) return 1000;	// lignite
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 3) return 50;	// peat
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 8) return 400;	// dry peat
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 4) return 600;	// charcoal
		if(fuel.getItem() == ModContents.tiersItems && fuel.getItemDamage() == 5) return 200;	// coal pellet
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 0) return 28800;	// anthracite
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 1) return 21600;	// bituminous
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 2) return 9000;	// lignite
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 3) return 3600;	// peat
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.coalBlocks) && fuel.getItemDamage() == 4) return 5400;	// charcoal
		if(fuel.getItem() == Item.getItemFromBlock(ModContents.seamFire)) return 16000;	// seamfire
		return 0;
	}

}
