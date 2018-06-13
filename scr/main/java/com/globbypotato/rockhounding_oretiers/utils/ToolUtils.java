package com.globbypotato.rockhounding_oretiers.utils;

import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;

import net.minecraft.item.ItemStack;

public class ToolUtils {

	public static ItemStack hammer = new ItemStack(ModItems.FORGING_HAMMER);

	public static boolean registerAsOres(){
		return ModConfig.enableIronPebbles <= 1;
	}
	
	public static boolean registerAsPebbles(){
		return ModConfig.enableIronPebbles >= 1;
	}

}