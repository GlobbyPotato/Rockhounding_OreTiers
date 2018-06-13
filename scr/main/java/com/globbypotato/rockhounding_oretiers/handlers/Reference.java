package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Reference {
	// Create Mod Reference 
	public static final String MODID = "rockhounding_oretiers";
    public static final String NAME = "Rockhounding Mod: Ore Tiers";
	public static final String VERSION = "${version_mod}";
	public static final String CLIENT_PROXY_CLASS = "com.globbypotato.rockhounding_oretiers.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.globbypotato.rockhounding_oretiers.proxy.CommonProxy";

	//Create new Creative Tab with Icon
	public static CreativeTabs RockhoundingTiers = new CreativeTabs("rockhoundingTiers") {
		@Override
		public ItemStack getTabIconItem() { 
			return new ItemStack(ModItems.TIER_ITEMS); 
		}
	};
}