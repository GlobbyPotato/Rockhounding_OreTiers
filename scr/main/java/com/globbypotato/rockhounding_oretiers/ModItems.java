package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_core.handlers.RegistryHandler;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.items.IronPebbles;
import com.globbypotato.rockhounding_oretiers.items.TierItems;
import com.globbypotato.rockhounding_oretiers.items.TiersBook;
import com.globbypotato.rockhounding_oretiers.items.io.ConsumableIO;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

	public static final Item TIER_ITEMS = new TierItems("tier_items", EnumTiersItems.getNames());
	public static final Item IRON_PEBBLES = new IronPebbles("iron_pebbles", EnumIronOres.getNames());
	public static final Item FORGING_HAMMER = new ConsumableIO("forging_hammer", ModConfig.hammerUses);
	public static final Item TIERS_BOOK = new TiersBook("tiers_book");

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		// register the item
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registry.register(FORGING_HAMMER);
			registry.register(TIER_ITEMS);
			if(ToolUtils.registerAsPebbles()){
				registry.register(IRON_PEBBLES);
			}
			registry.register(TIERS_BOOK);
		}
		
		// register the model
		/**
		 * @param event  
		 */
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event){
			RegistryHandler.registerSingleModel(FORGING_HAMMER);
			RegistryHandler.registerMetaModel(TIER_ITEMS, EnumTiersItems.getNames());
			if(ToolUtils.registerAsPebbles()){
				RegistryHandler.registerMetaModel(IRON_PEBBLES, EnumIronOres.getNames());
			}
			RegistryHandler.registerSingleModel(TIERS_BOOK);
		}

	}
}