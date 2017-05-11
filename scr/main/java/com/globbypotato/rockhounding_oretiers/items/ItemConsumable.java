package com.globbypotato.rockhounding_oretiers.items;

public class ItemConsumable extends ItemBase {
	public ItemConsumable(String name, int uses){
		super(name);
		this.setMaxDamage(uses);
		this.setMaxStackSize(1);
	}
}