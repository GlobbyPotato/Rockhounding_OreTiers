package com.globbypotato.rockhounding_oretiers.items;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {

	public ItemBase(String name){
	    setRegistryName(name);
        setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
        setCreativeTab(Reference.RockhoundingTiers);
	}

}