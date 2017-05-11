package com.globbypotato.rockhounding_oretiers.utils;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Utils {
	
	public static ArrayList<Integer> intArrayToList(int[] array){
		ArrayList<Integer> temp = new ArrayList<Integer>(array.length);
		for(int i=0;i<array.length;i++){
			temp.add(array[i]);
		}
		return temp;
	}

	public static boolean areItemsEqualIgnoreMeta(ItemStack stack1, ItemStack stack2){
//		if(stack1 == null && stack2 == null) return true;
		if(stack1 != null && stack2 != null){
			return stack1.getItem() == stack2.getItem();
		}
		return false;
	}

}