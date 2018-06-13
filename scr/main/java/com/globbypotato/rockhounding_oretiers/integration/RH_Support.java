package com.globbypotato.rockhounding_oretiers.integration;

import java.util.Arrays;

import com.globbypotato.rockhounding_core.utils.CoreBasics;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.utils.BaseRecipes;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import rockhounding.api.IReciper;
import rockhounding.api.IReciperBase;

public class RH_Support extends IReciper {
	public static void init(){
		if(IReciperBase.chemistryLoaded()){

			sendToLabBlender(	Arrays.asList(BaseRecipes.tier_items(3, EnumTiersItems.ANTHRACITE.ordinal())), 
								CoreUtils.getModdedStack(chemicals(), 5, 2));

			sendToLabBlender(	Arrays.asList(BaseRecipes.tier_items(3, EnumTiersItems.BITUMINOUS.ordinal())), 
								CoreUtils.getModdedStack(chemicals(), 3, 2));

			sendToSlurryPond(	BaseRecipes.tier_items(1, EnumTiersItems.PEAT.ordinal()),
								CoreBasics.waterStack(400),
								CoreUtils.getFluid("organic_slurry", 400));

			sendToMineralSizer(	BaseRecipes.tier_blocks(1, EnumCoalBlocks.ANTHRACITE.ordinal()),
								Arrays.asList(CoreUtils.getModdedStack(chemicals(), 64, 0)), 
								Arrays.asList(2));

			sendToMineralSizer(	BaseRecipes.tier_blocks(1, EnumCoalBlocks.BITUMINOUS.ordinal()),
								Arrays.asList(CoreUtils.getModdedStack(chemicals(), 48, 0)), 
								Arrays.asList(2));
		}

		if(IReciperBase.oretiersLoaded()){
			sendToCompostBin(BaseRecipes.tier_items(1, EnumTiersItems.PEAT.ordinal()), -1, 60);
			sendToCompostBin(BaseRecipes.tier_items(1, EnumTiersItems.DRYPEAT.ordinal()), -1, 30);
		}

	}

	public static Item chemicals(){
		Item item = Item.REGISTRY.getObject(new ResourceLocation(IReciperBase.rh_chemistry_id + ":" + "chemical_items"));
		return item;
	}

}