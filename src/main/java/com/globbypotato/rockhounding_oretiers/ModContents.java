package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.blocks.CoalBlocks;
import com.globbypotato.rockhounding_oretiers.blocks.CoalOres;
import com.globbypotato.rockhounding_oretiers.blocks.IronOres;
import com.globbypotato.rockhounding_oretiers.blocks.SeamFire;
import com.globbypotato.rockhounding_oretiers.blocks.SeamFireIB;
import com.globbypotato.rockhounding_oretiers.blocks.SimpleIB;
import com.globbypotato.rockhounding_oretiers.blocks.TiersIB;
import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.items.TiersBook;
import com.globbypotato.rockhounding_oretiers.items.TiersItems;
import com.globbypotato.rockhounding_oretiers.machines.CoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.PeatDrier;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModContents {
	public static Block coalOres;
	public static Block coalBlocks;
	public static Block ironOres;
	public static Block seamFire;

    public static final int peatDrierID = 0;
	public static Block peatDrier;
    public static final int coalRefinerID = 1;
	public static Block coalRefiner;

	public static Item tiersItems;
	public static Item tiersBook;

	//initialize the block
	public static void init() {
		//blocks
		coalOres = new CoalOres(3.0F, 5.0F, "coalOres");								
		coalBlocks = new CoalBlocks(Material.ROCK, 3.0F, 5.0F, "coalBlocks");							
		ironOres = new IronOres(3.0F, 5.0F, "ironOres");
		peatDrier = new PeatDrier(3.0F, 3.0F, "peatDrier");
		coalRefiner = new CoalRefiner(3.0F, 3.0F, "coalRefiner");
		seamFire = new SeamFire(3.0F, 3.0F, "seamFire");

		//items
		tiersItems = new TiersItems("tiersItems");										
		tiersBook = new TiersBook("tiersBook");										
	}

	//recall the registry
	public static void register() {
		//blocks
		registerMetaBlock(coalOres, "coalOres");
		registerMetaBlock(coalBlocks, "coalBlocks");
		registerMetaBlock(ironOres, "ironOres");
		registerSimpleBlock(peatDrier, "peatDrier");
		registerSimpleBlock(coalRefiner, "coalRefiner");
		registerFireBlock(seamFire, "seamFire");

		//items
		registerItem(tiersItems);
		registerItem(tiersBook);
	}

	//register blocks and itemblocks
	public static void registerMetaBlock(Block block, String name) {
		GameRegistry.register(block);
		GameRegistry.register(new TiersIB(block).setRegistryName(name));
	}
	//register simple blocks and itemblocks
	public static void registerSimpleBlock(Block block, String name) {
		GameRegistry.register(block);
		GameRegistry.register(new SimpleIB(block).setRegistryName(name));
	}
	//register simple blocks and itemblocks
	public static void registerFireBlock(Block block, String name) {
		GameRegistry.register(block);
		GameRegistry.register(new SeamFireIB(block).setRegistryName(name));
	}

	//register items
	private static void registerItem(Item item) {
		GameRegistry.register(item);
	}

	//recall the renders
	public static void registerRenders(){
		//blocks
		for(int i = 0; i < ModArray.coalOresArray.length; i++){		registerMetaBlockRender(coalOres, i, ModArray.coalOresArray[i]);		}
		for(int i = 0; i < ModArray.coalBlocksArray.length; i++){	registerMetaBlockRender(coalBlocks, i, ModArray.coalBlocksArray[i]);	}
		for(int i = 0; i < ModArray.ironOresArray.length; i++){		registerMetaBlockRender(ironOres, i, ModArray.ironOresArray[i]);		}
		registerSingleBlockRender(peatDrier, 0, "peatDrier");
		registerSingleBlockRender(coalRefiner, 0, "coalRefiner");
		registerSingleBlockRender(seamFire, 0, "seamFire");
		
		//items
		for(int i = 0; i < ModArray.tiersItemsArray.length; i++){	registerMetaItemRender(tiersItems, i, ModArray.tiersItemsArray[i]);	}
		registerSimpleItemRender(tiersBook, 0, "guide");
	}

	//render meta block
	public static void registerMetaBlockRender(Block block, int meta, String fileName){
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName() + "_" + fileName, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}
	//render single block
	public static void registerSingleBlockRender(Block block, int meta, String fileName){
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
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
