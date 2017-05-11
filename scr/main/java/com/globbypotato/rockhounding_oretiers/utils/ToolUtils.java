package com.globbypotato.rockhounding_oretiers.utils;

import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.item.ItemStack;

public class ToolUtils {

	public static boolean hasConsumable(ItemStack consumable, ItemStack insertingStack) {
		return insertingStack != null 
			&& Utils.areItemsEqualIgnoreMeta(consumable, insertingStack)
			&& insertingStack.getItemDamage() < insertingStack.getMaxDamage();
	}

	public static ItemStack hammer = new ItemStack(ModContents.forgeHammer);

}