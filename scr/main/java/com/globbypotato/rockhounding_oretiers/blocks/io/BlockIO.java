package com.globbypotato.rockhounding_oretiers.blocks.io;

import com.globbypotato.rockhounding_core.blocks.BaseBlock;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockIO extends BaseBlock {

	public BlockIO(String name, Material material, float hardness, float resistance, SoundType stepSound) {
		super(Reference.MODID, name, material);
		setCreativeTab(Reference.RockhoundingTiers);
		setHardness(hardness); 
		setResistance(resistance);	
		setHarvestLevel("pickaxe", 1);
		setSoundType(stepSound);
	}

}