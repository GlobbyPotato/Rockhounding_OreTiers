package com.globbypotato.rockhounding_oretiers.items;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BloomBucket extends ItemBucket{
	public BloomBucket(String name, Block block) {
		super(block);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHasSubtypes(true);
        GameRegistry.register(this);
		setCreativeTab(Reference.RockhoundingTiers);
	}

}