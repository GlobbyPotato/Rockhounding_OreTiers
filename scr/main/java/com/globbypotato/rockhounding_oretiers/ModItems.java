package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.items.ArrayIO;
import com.globbypotato.rockhounding_oretiers.items.ConsumableIO;
import com.globbypotato.rockhounding_oretiers.items.TiersBook;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModItems {

	public static Item tiersItems;
	public static ConsumableIO forgeHammer;
	public static Item tiersBook;

	public static void init() {
		tiersItems = new ArrayIO("tiersItems", EnumTiersItems.getNames());										
		tiersBook = new TiersBook("tiersBook");										
		forgeHammer = new ConsumableIO("forgeHammer", ModConfig.hammerUses);
	}

	public static void initClient(){
		for(int i = 0; i < EnumTiersItems.size(); i++){				registerMetaItemRender(tiersItems, i, EnumTiersItems.getName(i));	}
		registerSimpleItemRender(tiersBook, 0, "guide");
		registerSimpleItemRender(forgeHammer, 0, "forgeHammer");
	}

	//render meta item
	public static void registerMetaItemRender(Item item, int meta, String fileName){
		ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName() + "_" + fileName, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}
	//render simple item
	public static void registerSimpleItemRender(Item item, int meta, String fileName){
		ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}
}