package com.globbypotato.rockhounding_oretiers.handlers;

import java.util.Iterator;
import java.util.Map;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.utils.BaseRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes extends BaseRecipes{

	public static void init() {
		ModRecipes.removeRecipes();
		ModRecipes.tiersRecipes();
	}

	private static void tiersRecipes() {
	//Book
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersBook), new Object[] { Items.PAPER, Items.PAPER, Items.PAPER, getOredict(EnumTiersItems.TIERCHARCOAL) }));
	//drying pallet
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.peatDrier), new Object[] { "sss","SSS","P P", 's', "stickWood", 'S', "slabWood", 'P', "plankWood" }));
	//coal refiner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalRefiner), new Object[] { " S ","SFS","SIS", 'S', "stone", 'F', Blocks.FURNACE, 'I', "ingotIron"  }));
	//bloomery
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.bloomery), new Object[] { "X X", "X X", "SFS", 'S', Blocks.STONEBRICK, 'X', Blocks.STONE_BRICK_STAIRS, 'F', Blocks.FURNACE }));
	//hammer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.forgeHammer), new Object[] { " Ss", " sS", "s  ", 's', "stickWood", 'S', "stone" }));
	//charcoal conversion
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.COAL, 1, 1), new Object[] { getOredict(EnumTiersItems.TIERCHARCOAL), getOredict(EnumTiersItems.TIERCHARCOAL), getOredict(EnumTiersItems.TIERCHARCOAL), getOredict(EnumTiersItems.TIERCHARCOAL) }));
	//coal smelting
		for(int x = 0; x < EnumCoalOres.size(); x++){
	    	for(ItemStack ore : OreDictionary.getOres(EnumCoalOres.getOredict(x))) {
	            if(ore != null){
            		GameRegistry.addSmelting(ore, new ItemStack(ModItems.tiersItems, 1, x), 1.0F);
	            }
	        }
		}

	//coal/blocks
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalBlocks, 1, EnumCoalBlocks.ANTHRACITE.ordinal()), 	new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.ANTHRACITE) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalBlocks, 1, EnumCoalBlocks.BITUMINOUS.ordinal()), 	new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.BITUMINOUS) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalBlocks, 1, EnumCoalBlocks.LIGNITE.ordinal()), 		new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.LIGNITE) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalBlocks, 1, EnumCoalBlocks.PEAT.ordinal()), 			new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.DRYPEAT) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coalBlocks, 1, EnumCoalBlocks.TIERCHARCOAL.ordinal()), 	new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.TIERCHARCOAL) }));
	//coal/items
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 9, EnumTiersItems.ANTHRACITE.ordinal()), 	new Object[] { getOredict(EnumCoalBlocks.ANTHRACITE) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 9, EnumTiersItems.BITUMINOUS.ordinal()), 	new Object[] { getOredict(EnumCoalBlocks.BITUMINOUS) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 9, EnumTiersItems.LIGNITE.ordinal()), 	  	new Object[] { getOredict(EnumCoalBlocks.LIGNITE) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 9, EnumTiersItems.TIERCHARCOAL.ordinal()), new Object[] { getOredict(EnumCoalBlocks.TIERCHARCOAL) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 9, EnumTiersItems.DRYPEAT.ordinal()), 		new Object[] { getOredict(EnumCoalBlocks.PEAT) }));
	//coal pellet
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 16, EnumTiersItems.COALPELLET.ordinal()), new Object[] { getOredict(EnumTiersItems.ANTHRACITE) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 12, EnumTiersItems.COALPELLET.ordinal()), new Object[] { getOredict(EnumTiersItems.BITUMINOUS) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 8,  EnumTiersItems.COALPELLET.ordinal()), new Object[] { new ItemStack(Items.COAL, 1, 0) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 6,  EnumTiersItems.COALPELLET.ordinal()), new Object[] { new ItemStack(Items.COAL, 1, 1) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 5,  EnumTiersItems.COALPELLET.ordinal()), new Object[] { getOredict(EnumTiersItems.LIGNITE) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 3,  EnumTiersItems.COALPELLET.ordinal()), new Object[] { getOredict(EnumTiersItems.TIERCHARCOAL) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.tiersItems, 2,  EnumTiersItems.COALPELLET.ordinal()), new Object[] { getOredict(EnumTiersItems.DRYPEAT) }));
	//vanilla torches
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 8), new Object[] { "Y","X", 'Y', getOredict(EnumTiersItems.ANTHRACITE), 	 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 6), new Object[] { "Y","X", 'Y', getOredict(EnumTiersItems.BITUMINOUS), 	 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 3), new Object[] { "Y","X", 'Y', getOredict(EnumTiersItems.LIGNITE), 	 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 1), new Object[] { "Y","X", 'Y', getOredict(EnumTiersItems.DRYPEAT), 	 'X', "stickWood", }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.TORCH, 2), new Object[] { "Y","X", 'Y', getOredict(EnumTiersItems.TIERCHARCOAL), 'X', "stickWood", }));

	//charcoal smelting
    	for(ItemStack ore : OreDictionary.getOres("logWood")) {
            if(ore != null)  {
               if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
            		GameRegistry.addSmelting(ore, new ItemStack(ModItems.tiersItems, 1, EnumTiersItems.COALPELLET.ordinal()), 1.0F);
               }
            }
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