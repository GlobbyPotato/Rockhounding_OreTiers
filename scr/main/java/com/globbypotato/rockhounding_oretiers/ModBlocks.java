package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_oretiers.blocks.CoalBlocks;
import com.globbypotato.rockhounding_oretiers.blocks.CoalOres;
import com.globbypotato.rockhounding_oretiers.blocks.IronOres;
import com.globbypotato.rockhounding_oretiers.blocks.SeamFire;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.machines.Bloomery;
import com.globbypotato.rockhounding_oretiers.machines.CoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.PeatDrier;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModBlocks {
	public static Block coalOres;
	public static Block coalBlocks;
	public static Block ironOres;
	public static Block seamFire;

	public static Block peatDrier;
	public static Block coalRefiner;
	public static Block bloomery;

	public static void init(){
		coalOres = new CoalOres(Material.ROCK, EnumCoalOres.getNames(), 3.0F, 5.0F, "coalOres", SoundType.STONE);
		coalBlocks = new CoalBlocks(Material.ROCK, EnumCoalBlocks.getNames(), 3.0F, 5.0F, "coalBlocks", SoundType.STONE);
		ironOres = new IronOres(Material.ROCK, EnumIronOres.getNames(), 3.0F, 5.0F, "ironOres", SoundType.STONE);
		seamFire = new SeamFire(3.0F, 3.0F, "seamFire");

		peatDrier = new PeatDrier(3.0F, 3.0F, "peatDrier");
		coalRefiner = new CoalRefiner(3.0F, 3.0F, "coalRefiner");
		bloomery = new Bloomery(3.0F, 3.0F, "bloomery");
	}

	public static void initClient(){
		for(int i = 0; i < EnumCoalOres.size(); i++){				registerMetaBlockRender(coalOres, i, EnumCoalOres.getName(i));		}
		for(int i = 0; i < EnumCoalBlocks.size(); i++){				registerMetaBlockRender(coalBlocks, i, EnumCoalBlocks.getName(i));	}
		for(int i = 0; i < EnumIronOres.size(); i++){				registerMetaBlockRender(ironOres, i, EnumIronOres.getName(i));		}
		registerSingleBlockRender(peatDrier, 0, "peatDrier");
		registerSingleBlockRender(coalRefiner, 0, "coalRefiner");
		registerSingleBlockRender(bloomery, 0, "bloomery");
		registerSingleBlockRender(seamFire, 0, "seamFire");
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

}