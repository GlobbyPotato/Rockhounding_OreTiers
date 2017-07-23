package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModDictionary {

	public static void loadDictionary()  {
		//ORES
		for(int x = 0; x < EnumCoalOres.size(); x++){
			OreDictionary.registerOre(EnumCoalOres.getOredict(x), 		new ItemStack(ModBlocks.coalOres, 1, x));
		}
		for(int x = 0; x < EnumIronOres.size(); x++){
			OreDictionary.registerOre(EnumIronOres.getOredict(x), 		new ItemStack(ModBlocks.ironOres, 1, x));
		}
	//BLOCKS
		for(int x = 0; x < EnumCoalBlocks.size(); x++){
			OreDictionary.registerOre(EnumCoalBlocks.getOredict(x), 	new ItemStack(ModBlocks.coalBlocks, 1, x));
		}
	//ITEMS
		for(int x = 0; x < EnumTiersItems.size(); x++){
			OreDictionary.registerOre(EnumTiersItems.getOredict(x), 	new ItemStack(ModItems.tiersItems, 1, x));
		}
	}
}