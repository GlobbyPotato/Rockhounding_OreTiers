package com.globbypotato.rockhounding_oretiers.handlers.enums;


import net.minecraft.util.IStringSerializable;

public enum EnumCoalOres implements IStringSerializable {
	ANTHRACITE, 
	BITUMINOUS,
	LIGNITE,
	PEAT;

	@Override
	public String getName() {
        return toString().toLowerCase();
	}

	public static int size(){
		return values().length;
	}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return EnumCoalOres.values()[index].getName();
	}
}