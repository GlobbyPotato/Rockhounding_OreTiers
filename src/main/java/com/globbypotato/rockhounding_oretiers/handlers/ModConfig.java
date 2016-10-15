package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;
import com.globbypotato.rockhounding_oretiers.world.TiersOresGenerator;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	public static final String CATEGORY_TIERS = "TiersOres";


	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

	//TIERS ORES
		config.addCustomCategoryComment("TiersOres", "These settings handle the coal and iron tiers feature.");
		TiersOresGenerator.coalFrequency = config.get(			CATEGORY_TIERS, "coalFrequency", 			10, 	"Frequency of the coal ores spawning").getInt();
		TiersOresGenerator.coalMaxVein = config.get(			CATEGORY_TIERS, "coalMaxVein", 				16, 	"Highest coal vein size").getInt();
		TiersOresGenerator.coalMinVein = config.get(			CATEGORY_TIERS, "coalMinVein", 				4, 		"Lowest coal vein size").getInt();
		TiersOresGenerator.ironFrequency = config.get(			CATEGORY_TIERS, "ironFrequency", 			8, 		"Frequency of the iron ores spawning").getInt();
		TiersOresGenerator.ironMaxVein = config.get(			CATEGORY_TIERS, "ironMaxVein", 				8, 		"Highest iron vein size").getInt();
		TiersOresGenerator.ironMinVein = config.get(			CATEGORY_TIERS, "ironMinVein", 				4,		"Lowest coal vein size").getInt();
		TileEntityPeatDrier.dryingMultiplier = config.get(		CATEGORY_TIERS, "DryingSpeedMultiplier", 	1,		"Multiply ticks required to dry a Moist Peat Shard in the Pallet. Base ticks = 2400").getInt();
		TileEntityCoalRefiner.refiningMultiplier = config.get(	CATEGORY_TIERS, "RefiningSpeedMultiplier",	1,		"Multiply ticks required to refine a coal type in the Coal Refiner. Base ticks = 3200").getInt();
		config.save();
	}

}
