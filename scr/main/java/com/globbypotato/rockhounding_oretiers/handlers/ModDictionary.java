package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ModDictionary {

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {
		/**
		 * @param event  
		 */
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerOreDictEntries(final RegistryEvent.Register<Item> event) {

		//ORES
			for(int x = 0; x < EnumCoalOres.size(); x++){
				OreDictionary.registerOre(EnumCoalOres.getOredict(x), 		new ItemStack(ModBlocks.COAL_ORES, 1, x));
			}
			if(ToolUtils.registerAsOres()){
				for(int x = 0; x < EnumIronOres.size(); x++){
					OreDictionary.registerOre(EnumIronOres.getOredict(x), 	new ItemStack(ModBlocks.IRON_ORES, 1, x));
				}
			}

		//BLOCKS
			for(int x = 0; x < EnumCoalBlocks.size(); x++){
				OreDictionary.registerOre(EnumCoalBlocks.getOredict(x), 	new ItemStack(ModBlocks.COAL_BLOCKS, 1, x));
			}
		//ITEMS
			for(int x = 0; x < EnumTiersItems.size(); x++){
				OreDictionary.registerOre(EnumTiersItems.getOredict(x), 	new ItemStack(ModItems.TIER_ITEMS, 1, x));
			}
		}
	}

}