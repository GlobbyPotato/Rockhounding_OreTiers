package com.globbypotato.rockhounding_oretiers.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	public static final ArrayList<DrierRecipes> drierRecipe = new ArrayList<DrierRecipes>();
	public static final ArrayList<RefinerRecipes> refinerRecipe = new ArrayList<RefinerRecipes>();
	public static final ArrayList<BloomeryRecipes> bloomeryRecipe = new ArrayList<BloomeryRecipes>();

	public static void init() {
		ModRecipes.tiersRecipes();
		ModRecipes.refinerRecipes();
		ModRecipes.drierRecipes();
		ModRecipes.bloomeryRecipes();
	}

	private static void bloomeryRecipes() {
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 0), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_MAGNTITE), new ItemStack(Items.IRON_INGOT))); //magnetite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 1), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_HEMATITE), new ItemStack(Items.IRON_INGOT))); //hematite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 2),	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), new ItemStack(Items.IRON_INGOT))); //goethite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 3), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_LIMONITE), new ItemStack(Items.IRON_INGOT))); //limonite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 4), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_SIDERITE), new ItemStack(Items.IRON_INGOT))); //siderite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 5), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BOG_IRON), new ItemStack(Items.IRON_INGOT))); //bog iron
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 6), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_TACONITE), new ItemStack(Items.IRON_INGOT))); //taconite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModContents.ironOres, 1, 7), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BANDED_IRON), new ItemStack(Items.IRON_INGOT))); //banded iron
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(Blocks.IRON_ORE), 				new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), new ItemStack(Items.IRON_INGOT))); //vanilla Iron
	}

	private static void drierRecipes() {
		drierRecipe.add(new DrierRecipes(new ItemStack(ModContents.tiersItems, 1, 3), new ItemStack(ModContents.tiersItems, 1, 8))); //peat+
	}

	public static void refinerRecipes(){
		refinerRecipe.add(new RefinerRecipes(new ItemStack(ModContents.tiersItems, 1, 2), new ItemStack(Items.COAL))); //lignite+
		refinerRecipe.add(new RefinerRecipes(new ItemStack(Items.COAL), new ItemStack(ModContents.tiersItems, 1, 1))); //coal+
	}

	private static void tiersRecipes() {
	//remove recipes
		ModRecipes.removeRecipes();
	//Book
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersBook), new Object[] { Items.PAPER, Items.PAPER, Items.PAPER, "lumpCharcoal" }));
	//drying pallet
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.peatDrier), new Object[] { "sss","SSS","P P", 's', "stickWood", 'S', "slabWood", 'P', "plankWood" }));
	//coal refiner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalRefiner), new Object[] { " S ","SFS","SIS", 'S', "stone", 'F', Blocks.FURNACE, 'I', "ingotIron"  }));
	//charcoal conversion
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.COAL, 1, 1), new Object[] { "lumpCharcoal", "lumpCharcoal", "lumpCharcoal", "lumpCharcoal" }));
	//coal smelting
		for(int x = 0; x < 3; x++){
	    	for(ItemStack ore : OreDictionary.getOres(ModArray.dictCoalOres[x])) {
	            if(ore != null)  {
	               if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
	            		GameRegistry.addSmelting(ore, new ItemStack(ModContents.tiersItems, 1, x), 1.0F);
	               }
	            }
	        }
		}
	//coal/blocks
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalBlocks, 1, 0), new Object[] { "XXX","XXX","XXX",'X', "itemAnthracite" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalBlocks, 1, 1), new Object[] { "XXX","XXX","XXX",'X', "itemBituminous" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalBlocks, 1, 2), new Object[] { "XXX","XXX","XXX",'X', "itemLignite" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalBlocks, 1, 3), new Object[] { "XXX","XXX","XXX",'X', "itemDryPeat" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.coalBlocks, 1, 4), new Object[] { "XXX","XXX","XXX",'X', "lumpCharcoal" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 9, 0), new Object[] { "blockAnthracite" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 9, 1), new Object[] { "blockBituminous" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 9, 2), new Object[] { "blockLignite" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 9, 4), new Object[] { "blockTierCharcoal" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 9, 8), new Object[] { "blockPeat" }));
	//coal pellet
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 16, 5), new Object[] { "itemAnthracite" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 12, 5), new Object[] { "itemBituminous" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 8,  5), new Object[] { new ItemStack(Items.COAL, 1, 0) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 6,  5), new Object[] { new ItemStack(Items.COAL, 1, 1) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 5,  5), new Object[] { "itemLignite" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 3,  5), new Object[] { "lumpCharcoal" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModContents.tiersItems, 2,  5), new Object[] { "itemDryPeat" }));
	//vanilla torches
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 8), new Object[] { "Y","X", 'Y', "itemAnthracite", 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 6), new Object[] { "Y","X", 'Y', "itemBituminous", 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 3), new Object[] { "Y","X", 'Y', "itemLignite", 	  'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 1), new Object[] { "Y","X", 'Y', "itemDryPeat", 	  'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 2), new Object[] { "Y","X", 'Y', "lumpCharcoal",   'X', "stickWood", }));

	//charcoal smelting
    	for(ItemStack ore : OreDictionary.getOres("logWood")) {
            if(ore != null)  {
               if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
            		GameRegistry.addSmelting(ore, new ItemStack(ModContents.tiersItems, 1, 4), 1.0F);
               }
            }
        }

	//iron conversion
		if(!ModConfig.enableBloomery){
		//iron smelting
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 0), new ItemStack(ModContents.tiersItems, 3, 6), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 1), new ItemStack(ModContents.tiersItems, 3, 6), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 2), new ItemStack(Items.IRON_INGOT, 1), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 3), new ItemStack(Items.IRON_INGOT, 1), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 4), new ItemStack(ModContents.tiersItems, 5, 7), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 5), new ItemStack(ModContents.tiersItems, 4, 7), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 6), new ItemStack(ModContents.tiersItems, 3, 7), 1.0F);
	 		GameRegistry.addSmelting(new ItemStack(ModContents.ironOres, 1, 7), new ItemStack(ModContents.tiersItems, 2, 7), 1.0F);
		//lump-pellet
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.tiersItems, 1, 6), new Object[] { "XX", "XX", 'X', "lumpIron" }));
	 	//iron ingot-pellet
		    GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.IRON_INGOT), new Object[] { "pelletIron", "pelletIron" }));
		}else{
		//hammer
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.forgeHammer), new Object[] { " Ss", " sS", "s  ", 's', "stickWood", 'S', "stone" }));
		//bloomery
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModContents.bloomery), new Object[] { "X X", "X X", "SFS", 'S', Blocks.STONEBRICK, 'X', Blocks.STONE_BRICK_STAIRS, 'F', Blocks.FURNACE }));
		}
	}

	private static void removeRecipes() {
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		for (Iterator<Map.Entry<ItemStack, ItemStack>> entries = recipes.entrySet().iterator(); entries.hasNext(); ){
			Map.Entry<ItemStack,ItemStack> entry = entries.next();
			ItemStack result = entry.getValue();
			if(ItemStack.areItemStacksEqual(result, new ItemStack(Items.COAL, 1, 1) )){
				entries.remove();
			}
		}
	}
}