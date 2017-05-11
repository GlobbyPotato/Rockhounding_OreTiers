package com.globbypotato.rockhounding_oretiers.items;

import java.util.List;

import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.handlers.enums.EnumTiersItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TiersItems extends ItemBase {

	public TiersItems(String name) {
		super(name);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getItemDamage();
		if( i < 0 || i >= EnumTiersItems.size()){ i = 0; }
		return super.getUnlocalizedName() + "." + EnumTiersItems.getName(i);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(int i = 0; i < EnumTiersItems.size(); i++){
			if(ModConfig.enableBloomery){
				if((i != 6 && i != 7)){
					subItems.add(new ItemStack(itemIn, 1, i));
				}
			}else{
				subItems.add(new ItemStack(itemIn, 1, i));
			}
		}
	}
}