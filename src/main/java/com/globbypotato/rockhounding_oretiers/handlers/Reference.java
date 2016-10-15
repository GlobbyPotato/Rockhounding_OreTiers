package com.globbypotato.rockhounding_oretiers.handlers;


import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Reference {
	// Create Mod Reference 
	public static final String MODID = "rockhounding_oretiers";
	public static final String VERSION = "v1.00";
	public static final String CLIENT_PROXY_CLASS = "com.globbypotato.rockhounding_oretiers.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.globbypotato.rockhounding_oretiers.proxy.CommonProxy";

	//Create new Creative Tab with Icon
	public static CreativeTabs RockhoundingTiers = new CreativeTabs("rockhoundingTiers") {
		public Item getTabIconItem() { return ModContents.tiersItems; }
	};
}
