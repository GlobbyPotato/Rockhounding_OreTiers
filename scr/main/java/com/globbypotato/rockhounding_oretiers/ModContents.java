package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.blocks.CoalBlocks;
import com.globbypotato.rockhounding_oretiers.blocks.CoalOres;
import com.globbypotato.rockhounding_oretiers.blocks.IronOres;
import com.globbypotato.rockhounding_oretiers.blocks.SeamFire;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.items.ItemConsumable;
import com.globbypotato.rockhounding_oretiers.items.TiersBook;
import com.globbypotato.rockhounding_oretiers.items.TiersItems;
import com.globbypotato.rockhounding_oretiers.machines.Bloomery;
import com.globbypotato.rockhounding_oretiers.machines.CoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.PeatDrier;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.FluidRegistry;

public class ModContents {
	public static Block coalOres;
	public static Block coalBlocks;
	public static Block ironOres;
	public static Block seamFire;

    public static final int peatDrierID = 0;
	public static Block peatDrier;
    public static final int coalRefinerID = 1;
	public static Block coalRefiner;
    public static final int bloomeryID = 2;
	public static Block bloomery;

	public static Item tiersItems;
	public static ItemConsumable forgeHammer;
	public static Item tiersBook;

	//initialize the block
	public static void blockInit() {
		coalOres = new CoalOres(Material.ROCK, EnumCoalOres.getNames(), 3.0F, 5.0F, "coalOres", SoundType.STONE);
		coalBlocks = new CoalBlocks(Material.ROCK, EnumCoalBlocks.getNames(), 3.0F, 5.0F, "coalBlocks", SoundType.STONE);
		ironOres = new IronOres(Material.ROCK, EnumIronOres.getNames(), 3.0F, 5.0F, "ironOres", SoundType.STONE);
		seamFire = new SeamFire(3.0F, 3.0F, "seamFire");
		peatDrier = new PeatDrier(3.0F, 3.0F, "peatDrier");
		coalRefiner = new CoalRefiner(3.0F, 3.0F, "coalRefiner");
		bloomery = new Bloomery(3.0F, 3.0F, "bloomery");
	}

	public static void itemInit() {
		tiersItems = new TiersItems("tiersItems");										
		tiersBook = new TiersBook("tiersBook");										
		forgeHammer = new ItemConsumable("forgeHammer", ModConfig.hammerUses);
	}

	//recall the renders
	public static void initBlockClient(){
		for(int i = 0; i < EnumCoalOres.size(); i++){				registerMetaBlockRender(coalOres, i, EnumCoalOres.getName(i));		}
		for(int i = 0; i < EnumCoalBlocks.size(); i++){				registerMetaBlockRender(coalBlocks, i, EnumCoalBlocks.getName(i));	}
		for(int i = 0; i < EnumIronOres.size(); i++){				registerMetaBlockRender(ironOres, i, EnumIronOres.getName(i));		}
		registerSingleBlockRender(peatDrier, 0, "peatDrier");
		registerSingleBlockRender(coalRefiner, 0, "coalRefiner");
		registerSingleBlockRender(bloomery, 0, "bloomery");
		registerSingleBlockRender(seamFire, 0, "seamFire");
	}		
	public static void initItemClient(){
		for(int i = 0; i < EnumTiersItems.size(); i++){				registerMetaItemRender(tiersItems, i, EnumTiersItems.getName(i));	}
		registerSimpleItemRender(tiersBook, 0, "guide");
		
		if( !FluidRegistry.isUniversalBucketEnabled() ){
			registerSimpleItemRender(ModFluids.bloomBucket, 0, "bloomBucket");
		}
		registerSimpleItemRender(forgeHammer, 0, "forgeHammer");
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