package com.globbypotato.rockhounding_oretiers.utils;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreIngredient;

public class BaseRecipes {

	public static OreIngredient getOredict(EnumTiersItems item) {return new OreIngredient(EnumTiersItems.getOredict(item.ordinal()));}
	public static OreIngredient getOredict(EnumCoalBlocks item) {return new OreIngredient(EnumCoalBlocks.getOredict(item.ordinal()));}

	public static ItemStack tier_items(int num, int meta) {return new ItemStack(ModItems.TIER_ITEMS, num, meta);}
	public static ItemStack tier_blocks(int num, int meta) {return new ItemStack(ModBlocks.COAL_BLOCKS, num, meta);}

}