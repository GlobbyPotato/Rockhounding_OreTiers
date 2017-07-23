package com.globbypotato.rockhounding_oretiers.items;

import com.globbypotato.rockhounding_core.items.BaseArray;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;

public class ArrayIO extends BaseArray{

	public ArrayIO(String name, String[] array) {
		super(name, array);
		setCreativeTab(Reference.RockhoundingTiers);
	}
}