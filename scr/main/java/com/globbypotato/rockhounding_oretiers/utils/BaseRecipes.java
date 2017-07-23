package com.globbypotato.rockhounding_oretiers.utils;

import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumTiersItems;

public class BaseRecipes {

	public static String getOredict(EnumTiersItems item) {return item.getOredict(item.ordinal());}
	public static String getOredict(EnumCoalBlocks item) {return item.getOredict(item.ordinal());}

}