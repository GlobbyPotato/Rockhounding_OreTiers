package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModDictionary {

	public static void loadDictionary()  {
	//ORES
		for(int x = 0; x <= 3; x++){
			OreDictionary.registerOre(ModArray.dictCoalOres[x], 		new ItemStack(ModContents.coalOres, 1, x));
		}
		for(int x = 0; x < ModArray.dictIronOres.length; x++){
			OreDictionary.registerOre(ModArray.dictIronOres[x], 		new ItemStack(ModContents.ironOres, 1, x));
		}
	//BLOCKS
		for(int x = 0; x < ModArray.dictCoalBlocks.length; x++){
			OreDictionary.registerOre(ModArray.dictCoalBlocks[x], 		new ItemStack(ModContents.coalBlocks, 1, x));
		}
	//ITEMS
		for(int x = 0; x < ModArray.dictCoalSmelt.length; x++){
			OreDictionary.registerOre(ModArray.dictCoalSmelt[x], 		new ItemStack(ModContents.tiersItems, 1, x));
		}
		OreDictionary.registerOre("pelletCoal", 	new ItemStack(ModContents.tiersItems, 1, 5));
		OreDictionary.registerOre("pelletIron", 	new ItemStack(ModContents.tiersItems, 1, 6));
		OreDictionary.registerOre("lumpIron", 		new ItemStack(ModContents.tiersItems, 1, 7));	
		OreDictionary.registerOre("itemDryPeat",	new ItemStack(ModContents.tiersItems, 1, 8));	
	}
}