package com.globbypotato.rockhounding_oretiers.items;

import java.util.List;

import com.globbypotato.rockhounding_oretiers.handlers.ModArray;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TiersItems extends Item {

	public TiersItems(String name) {
		super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHasSubtypes(true);
		setCreativeTab(Reference.RockhoundingTiers);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getItemDamage();
		if( i < 0 || i >= ModArray.tiersItemsArray.length){ i = 0; }
		return super.getUnlocalizedName() + "." + ModArray.tiersItemsArray[i];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(int i = 0; i < ModArray.tiersItemsArray.length; i++){
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

}