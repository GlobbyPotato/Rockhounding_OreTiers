package com.globbypotato.rockhounding_oretiers.blocks.io;

import com.globbypotato.rockhounding_core.blocks.BaseMetaBlock;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MetaIO extends BaseMetaBlock{

	public MetaIO(String name, Material material, String[] array, float hardness, float resistance, SoundType stepSound) {
		super(Reference.MODID, name, material, array);
		setCreativeTab(Reference.RockhoundingTiers);
		setHardness(hardness); setResistance(resistance);	
		setSoundType(stepSound);
	}
}