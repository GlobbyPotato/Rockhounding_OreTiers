package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	public static final String COAL_ANTHRACITE = "Anthracite";
	public static final String COAL_BITUMINOUS = "Bituminous";
	public static final String COAL_VANILLA = "Vanilla";
	public static final String COAL_LIGNITE = "Lignite";
	public static final String COAL_PEAT = "Peat";
	public static final String COAL_SEAMFIRE = "Seamfire";
	public static final String COAL_CHARCOAL = "Charcoal";

	public static final String IRON_MAGNETITE = "Magnetite";
	public static final String IRON_HEMATITE = "Hematite";
	public static final String IRON_LIMONITE = "Limonite";
	public static final String IRON_GOETHITE = "Goethite";
	public static final String IRON_SIDERITE = "Siderite";
	public static final String IRON_BOG_IRON = "BogIron";
	public static final String IRON_TACONITE = "Taconite";
	public static final String IRON_BANDED_IRON = "BandedIron";

	public static final String SUPPORT_SETTINGS = "Absolute_Support";
	public static final String GENERAL_SETTINGS = "Absolute_General";
	public static final String DEVICE_SETTINGS = "Absolute_Devices";
	public static final String DIMENSION_SETTINGS = "Absolute_Dimensions";

	public static int refiningMultiplier;
    public static int dryingMultiplier;
    public static int bloomingSpeed;
    public static int hammerUses;
    public static int baseSpeed;
	public static int tankCapacity;

    public static boolean enableCoalTiers;
    public static boolean enableIronTiers;

	public static boolean enableTOP;

	public static boolean AROMA_ENABLER;
	public static int AROMA_ID;
	public static int AROMA_HEIGHT;
	public static boolean DEEPDARK_ENABLER;
	public static int DEEPDARK_ID;
	public static int FORFAIT_CHANCE;

	public static int[] dimensions = new int[]{};
  
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

//SUPPORT
		config.addCustomCategoryComment("Integration", "Mod support and integration parameters.");
		enableTOP 								= config.get(SUPPORT_SETTINGS, "SupportTheOneProbe",		true,	"Enable the additional info for The One Probe").getBoolean();

//COAL SETTINGS
		config.addCustomCategoryComment("Charcoal", "Parameters of the tier charcoal.");
		TiersGenerator.VALUE_CHARCOAL 			= config.get(COAL_CHARCOAL, 	"Burntime",		600,	"Fuel value").getInt();

		config.addCustomCategoryComment("Anthracite", "Spawning of the Anthracite Coal.");
		TiersGenerator.FREQUENCY_ANTHRACITE 	= config.get(COAL_ANTHRACITE, 	"Frequency",	7,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_ANTHRACITE 		= config.get(COAL_ANTHRACITE, 	"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_ANTHRACITE 		= config.get(COAL_ANTHRACITE, 	"Max Size",		14,		"Max vein size").getInt();
		TiersGenerator.VALUE_ANTHRACITE 		= config.get(COAL_ANTHRACITE, 	"Burntime",		3200,	"Fuel value").getInt();

		config.addCustomCategoryComment("Bituminous", "Spawning of the Bituminous Coal.");
		TiersGenerator.FREQUENCY_BITUMINOUS 	= config.get(COAL_BITUMINOUS, 	"Frequency",	8,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_BITUMINOUS 		= config.get(COAL_BITUMINOUS, 	"Min Size",		5,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_BITUMINOUS 		= config.get(COAL_BITUMINOUS, 	"Max Size",		12,		"Max vein size").getInt();
		TiersGenerator.VALUE_BITUMINOUS 		= config.get(COAL_BITUMINOUS, 	"Burntime",		2400,	"Fuel value").getInt();

		config.addCustomCategoryComment("Vanilla", "Spawning of the Vanilla Coal.");
		TiersGenerator.FREQUENCY_VANILLA 		= config.get(COAL_VANILLA, 		"Frequency",	10,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_VANILLA 		= config.get(COAL_VANILLA, 		"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_VANILLA 		= config.get(COAL_VANILLA, 		"Max Size",		16,		"Max vein size").getInt();

		config.addCustomCategoryComment("Lignite", "Spawning of the Lignite Coal.");
		TiersGenerator.FREQUENCY_LIGNITE 		= config.get(COAL_LIGNITE, 		"Frequency",	9,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_LIGNITE 		= config.get(COAL_LIGNITE, 		"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_LIGNITE 		= config.get(COAL_LIGNITE, 		"Max Size",		14,		"Max vein size").getInt();
		TiersGenerator.VALUE_LIGNITE 			= config.get(COAL_LIGNITE, 		"Burntime",		1000,	"Fuel value").getInt();

		config.addCustomCategoryComment("Peat", "Spawning of the Peat Coal.");
		TiersGenerator.FREQUENCY_PEAT 			= config.get(COAL_PEAT, 		"Frequency",	16,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_PEAT 			= config.get(COAL_PEAT, 		"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_PEAT 			= config.get(COAL_PEAT, 		"Max Size",		24,		"Max vein size").getInt();
		TiersGenerator.VALUE_PEAT 				= config.get(COAL_PEAT, 		"Burntime",		400,	"Fuel value").getInt();

		config.addCustomCategoryComment("Seamfire", "Spawning of the Seam Fire Deposit.");
		TiersGenerator.FREQUENCY_SEAMFIRE 		= config.get(COAL_SEAMFIRE, 	"Frequency",	2,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_SEAMFIRE 		= config.get(COAL_SEAMFIRE, 	"Min Size",		14,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_SEAMFIRE 		= config.get(COAL_SEAMFIRE, 	"Max Size",		24,		"Max vein size").getInt();

//IRON SETTINGS
		config.addCustomCategoryComment("Magnetite", "Spawning of the Magnetite Ore.");
		TiersGenerator.FREQUENCY_MAGNETITE 		= config.get(IRON_MAGNETITE, 	"Frequency",	7,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_MAGNETITE 		= config.get(IRON_MAGNETITE, 	"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_MAGNETITE 		= config.get(IRON_MAGNETITE, 	"Max Size",		9,		"Max vein size").getInt();
		TiersGenerator.VALUE_MAGNTITE 			= config.get(IRON_MAGNETITE, 	"Bloom",		700,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("Hematite", "Spawning of the Hematite Ore.");
		TiersGenerator.FREQUENCY_HEMATITE 		= config.get(IRON_HEMATITE, 	"Frequency",	8,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_HEMATITE 		= config.get(IRON_HEMATITE, 	"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_HEMATITE 		= config.get(IRON_HEMATITE, 	"Max Size",		10,		"Max vein size").getInt();
		TiersGenerator.VALUE_HEMATITE 			= config.get(IRON_HEMATITE, 	"Bloom",		650,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("Limonite", "Spawning of the Limonite Ore.");
		TiersGenerator.FREQUENCY_LIMONITE 		= config.get(IRON_LIMONITE, 	"Frequency",	9,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_LIMONITE 		= config.get(IRON_LIMONITE, 	"Min Size",		2,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_LIMONITE 		= config.get(IRON_LIMONITE, 	"Max Size",		9,		"Max vein size").getInt();
		TiersGenerator.VALUE_LIMONITE 			= config.get(IRON_LIMONITE, 	"Bloom",		450,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("Goethite", "Spawning of the Goethite Ore.");
		TiersGenerator.FREQUENCY_GOETHITE 		= config.get(IRON_GOETHITE, 	"Frequency",	8,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_GOETHITE 		= config.get(IRON_GOETHITE, 	"Min Size",		5,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_GOETHITE 		= config.get(IRON_GOETHITE, 	"Max Size",		8,		"Max vein size").getInt();
		TiersGenerator.VALUE_GOETHITE 			= config.get(IRON_GOETHITE, 	"Bloom",		500,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("Siderite", "Spawning of the Siderite Ore.");
		TiersGenerator.FREQUENCY_SIDERITE 		= config.get(IRON_SIDERITE, 	"Frequency",	8,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_SIDERITE 		= config.get(IRON_SIDERITE, 	"Min Size",		3,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_SIDERITE 		= config.get(IRON_SIDERITE, 	"Max Size",		7,		"Max vein size").getInt();
		TiersGenerator.VALUE_SIDERITE 			= config.get(IRON_SIDERITE, 	"Bloom",		400,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("BogIron", "Spawning of the BogIron Ore.");
		TiersGenerator.FREQUENCY_BOG_IRON 		= config.get(IRON_BOG_IRON, 	"Frequency",	9,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_BOG_IRON 		= config.get(IRON_BOG_IRON, 	"Min Size",		5,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_BOG_IRON 		= config.get(IRON_BOG_IRON, 	"Max Size",		10,		"Max vein size").getInt();
		TiersGenerator.VALUE_BOG_IRON 			= config.get(IRON_BOG_IRON, 	"Bloom",		300,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("Taconite", "Spawning of the Taconite Ore.");
		TiersGenerator.FREQUENCY_TACONITE 		= config.get(IRON_TACONITE, 	"Frequency",	7,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_TACONITE 		= config.get(IRON_TACONITE, 	"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_TACONITE 		= config.get(IRON_TACONITE, 	"Max Size",		10,		"Max vein size").getInt();
		TiersGenerator.VALUE_TACONITE 			= config.get(IRON_TACONITE, 	"Bloom",		200,	"Bloom quantity").getInt();

		config.addCustomCategoryComment("BandedIron", "Spawning of the BandedIron Ore.");
		TiersGenerator.FREQUENCY_BANDED_IRON 	= config.get(IRON_BANDED_IRON, 	"Frequency",	7,		"Frequency of the ore. Zero to disable").getInt();
		TiersGenerator.SIZE_MIN_BANDED_IRON 	= config.get(IRON_BANDED_IRON, 	"Min Size",		4,		"Min vein size").getInt();
		TiersGenerator.SIZE_MAX_BANDED_IRON 	= config.get(IRON_BANDED_IRON, 	"Max Size",		9,		"Max vein size").getInt();
		TiersGenerator.VALUE_BANDED_IRON 		= config.get(IRON_BANDED_IRON, 	"Bloom",		100,	"Bloom quantity").getInt();

//DEVICE SETTINGS
		config.addCustomCategoryComment(DEVICE_SETTINGS, "These settings handle the device settings.");
		dryingMultiplier 							= config.get(DEVICE_SETTINGS, 	"DryingMultiplier", 	2,		"Multiplier for BaseRefiningSpeed to dry a Moist Peat Shard in the Pallet").getInt();
		refiningMultiplier 							= config.get(DEVICE_SETTINGS, 	"RefiningMultiplier",	2,		"Multiplier for BaseRefiningSpeed to refine a coal type in the Coal Refiner").getInt();
		bloomingSpeed								= config.get(DEVICE_SETTINGS, 	"BloomerySpeed",		200,	"Ticks required to cast an iron Ingot").getInt();
		TileEntityBloomery.consumedBloom			= config.get(DEVICE_SETTINGS, 	"ConsumedMolten",		500,	"How many millibuckets of molten material are consumed to cast the output").getInt();
		hammerUses									= config.get(DEVICE_SETTINGS, 	"ForgeHammerUses",		150,	"Max uses for the Forge Hammer inside the Bloomery").getInt();
		baseSpeed									= config.get(DEVICE_SETTINGS, 	"BaseRefiningSpeed",	3000,	"Base Speed for Coal Refiner and Peat Drier").getInt();
		tankCapacity								= config.get(DEVICE_SETTINGS, 	"TankCapacity",			4000,	"Capacity of the Bloomery tank in mB. Will be increased by 1000mB by default").getInt();

//GENERAL SETTINGS
		config.addCustomCategoryComment(GENERAL_SETTINGS, "General settings of the mod.");
		enableCoalTiers 							= config.get(GENERAL_SETTINGS, 	"EnableCoalTiers",		true,	"General enabler for Coal Tiers").getBoolean();
		enableIronTiers 							= config.get(GENERAL_SETTINGS, 	"EnableIronTiers",		true,	"General enabler for Iron Tiers").getBoolean();

//DIMENSION SETTINGS
		config.addCustomCategoryComment(DIMENSION_SETTINGS, "Parameters about dimensions. For Built-In dimensions, parameters should match those in the original mods. Additional dimensions should be listed in the array");
		FORFAIT_CHANCE								= config.get(DIMENSION_SETTINGS, "AbsoluteChance",		70,		"Additional percentage chance for the ore to be generated").getInt();
		AROMA_ENABLER 								= config.get(DIMENSION_SETTINGS, "AromaEnabler",		false,	"Aroma1997 Mining dimension Enabler ").getBoolean();
		AROMA_ID									= config.get(DIMENSION_SETTINGS, "AromaID",				6,		"Aroma1997 Mining dimension ID").getInt();
		AROMA_HEIGHT								= config.get(DIMENSION_SETTINGS, "AromaHeight",			80,		"Aroma1997 Mining dimension Height").getInt();
		DEEPDARK_ENABLER 							= config.get(DIMENSION_SETTINGS, "DeepDarkEnabler",		false,	"Extra Utilities Deep Dark Dimension Enabler").getBoolean();
		DEEPDARK_ID									= config.get(DIMENSION_SETTINGS, "DeepDarkID",			-11325,	"Extra Utilities Deep Dark Dimension ID").getInt();
		dimensions 									= config.get(DIMENSION_SETTINGS, "dimension whitelist", dimensions, "Additional allowed dimensions. Overworld and builtIn dimensions (see above) excluded)").getIntList();

        if (config.hasChanged()) {
        	config.save();
        }
	}

}