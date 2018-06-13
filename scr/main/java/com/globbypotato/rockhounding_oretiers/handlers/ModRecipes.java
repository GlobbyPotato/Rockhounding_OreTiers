package com.globbypotato.rockhounding_oretiers.handlers;

import java.util.Iterator;
import java.util.Map;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.utils.BaseRecipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class ModRecipes extends BaseRecipes{

	/**
	 * @param event  
	 */
	@SubscribeEvent
	public static void registerRecipes(final RegistryEvent.Register<IRecipe> event){

		removeRecipes();

	//coal smelting
		for(int x = 0; x < EnumCoalOres.size(); x++){
	    	for(ItemStack ore : OreDictionary.getOres(EnumCoalOres.getOredict(x))) {
	            if(!ore.isEmpty()){
            		GameRegistry.addSmelting(ore, new ItemStack(ModItems.TIER_ITEMS, 1, x), 1.0F);
	            }
	        }
		}

	//coal/blocks
		GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MODID, "anthracite_item_to_block"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModBlocks.COAL_BLOCKS, 1, EnumCoalBlocks.ANTHRACITE.ordinal()), 		new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.ANTHRACITE) });
		GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MODID, "bituminous_item_to_block"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModBlocks.COAL_BLOCKS, 1, EnumCoalBlocks.BITUMINOUS.ordinal()), 		new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.BITUMINOUS) });
		GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MODID, "lignite_item_to_block"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModBlocks.COAL_BLOCKS, 1, EnumCoalBlocks.LIGNITE.ordinal()),				new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.LIGNITE) });
		GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MODID, "drypeat_item_to_block"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModBlocks.COAL_BLOCKS, 1, EnumCoalBlocks.PEAT.ordinal()), 				new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.DRYPEAT) });
		GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MODID, "tier_charcoal_item_to_block"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModBlocks.COAL_BLOCKS, 1, EnumCoalBlocks.TIERCHARCOAL.ordinal()), 	new Object[] {"XXX","XXX","XXX",'X', getOredict(EnumTiersItems.TIERCHARCOAL) });
	//coal/items
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "anthracite_block_to_item"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 9, EnumTiersItems.ANTHRACITE.ordinal()), 		new Ingredient[] { getOredict(EnumCoalBlocks.ANTHRACITE) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "bituminous_block_to_item"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 9, EnumTiersItems.BITUMINOUS.ordinal()), 		new Ingredient[] { getOredict(EnumCoalBlocks.BITUMINOUS) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "lignite_block_to_item"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 9, EnumTiersItems.LIGNITE.ordinal()), 	  		new Ingredient[] { getOredict(EnumCoalBlocks.LIGNITE) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "drypeat_block_to_item"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 9, EnumTiersItems.TIERCHARCOAL.ordinal()), 		new Ingredient[] { getOredict(EnumCoalBlocks.TIERCHARCOAL) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "tier_charcoal_block_to_item"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 9, EnumTiersItems.DRYPEAT.ordinal()), 		new Ingredient[] { getOredict(EnumCoalBlocks.PEAT) });
	//coal pellet
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "anthracite_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 16, EnumTiersItems.COALPELLET.ordinal()), 	new Ingredient[] { getOredict(EnumTiersItems.ANTHRACITE) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "bituminous_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"), new ItemStack(ModItems.TIER_ITEMS, 12, EnumTiersItems.COALPELLET.ordinal()), 	new Ingredient[] { getOredict(EnumTiersItems.BITUMINOUS) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "coal_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"),new ItemStack(ModItems.TIER_ITEMS, 8,  EnumTiersItems.COALPELLET.ordinal()), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.COAL, 1, 0)) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "charcoal_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"),new ItemStack(ModItems.TIER_ITEMS, 6,  EnumTiersItems.COALPELLET.ordinal()), 		new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.COAL, 1, 1)) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "lignite_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"),new ItemStack(ModItems.TIER_ITEMS, 5,  EnumTiersItems.COALPELLET.ordinal()), 		new Ingredient[] { getOredict(EnumTiersItems.LIGNITE) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "tier_charcoal_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"),new ItemStack(ModItems.TIER_ITEMS, 3,  EnumTiersItems.COALPELLET.ordinal()),	new Ingredient[] { getOredict(EnumTiersItems.TIERCHARCOAL) });
		GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "drypeat_item_to_pellet"), new ResourceLocation(Reference.MODID, "fuel"),new ItemStack(ModItems.TIER_ITEMS, 2,  EnumTiersItems.COALPELLET.ordinal()), 		new Ingredient[] { getOredict(EnumTiersItems.DRYPEAT) });

	//charcoal smelting
    	for(ItemStack ore : OreDictionary.getOres("logWood")) {
            if(!ore.isEmpty()){
               if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
            		GameRegistry.addSmelting(ore, new ItemStack(ModItems.TIER_ITEMS, 1, EnumTiersItems.TIERCHARCOAL.ordinal()), 1.0F);
               }
            }
        }

		MachineRecipes.machinesRecipes();

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