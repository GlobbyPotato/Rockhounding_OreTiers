package com.globbypotato.rockhounding_oretiers.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TiersIB extends ItemBlock {
	private String[] array;
	
	public TiersIB(Block block, String[] array) {
        super(block);
        this.setHasSubtypes(true);
        this.array = array;
	}

	public int getMetadata(int meta){
		return meta;
	}

    @Override
    public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getItemDamage();
		if( i < 0 || i >= this.array.length){ i = 0; }
        return super.getUnlocalizedName(stack) + "." + this.array[i];
    }

}