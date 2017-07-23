package com.globbypotato.rockhounding_oretiers.blocks;

import com.globbypotato.rockhounding_core.blocks.BaseMetaBlock;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockIO extends BaseMetaBlock{

	public BlockIO(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound) {
		super(material, array, hardness, resistance, name, stepSound);
		setCreativeTab(Reference.RockhoundingTiers);
	}
}