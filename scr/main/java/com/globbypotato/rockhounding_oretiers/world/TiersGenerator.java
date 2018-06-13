package com.globbypotato.rockhounding_oretiers.world;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TiersGenerator implements IWorldGenerator {
	Random rand = new Random();
//COAL ORES
	public static int FREQUENCY_ANTHRACITE;		public static int SIZE_MIN_ANTHRACITE;		public static int SIZE_MAX_ANTHRACITE;		public static int VALUE_ANTHRACITE;
	public static int FREQUENCY_BITUMINOUS;		public static int SIZE_MIN_BITUMINOUS;		public static int SIZE_MAX_BITUMINOUS;		public static int VALUE_BITUMINOUS;
	public static int FREQUENCY_VANILLA;		public static int SIZE_MIN_VANILLA;			public static int SIZE_MAX_VANILLA;			
	public static int FREQUENCY_LIGNITE;		public static int SIZE_MIN_LIGNITE;			public static int SIZE_MAX_LIGNITE;			public static int VALUE_LIGNITE;
	public static int FREQUENCY_PEAT;			public static int SIZE_MIN_PEAT;			public static int SIZE_MAX_PEAT;			public static int VALUE_PEAT;
	public static int FREQUENCY_SEAMFIRE;		public static int SIZE_MIN_SEAMFIRE;		public static int SIZE_MAX_SEAMFIRE;		
	public static int VALUE_CHARCOAL;
//IRON ORES
	public static int FREQUENCY_MAGNETITE;		public static int SIZE_MIN_MAGNETITE;		public static int SIZE_MAX_MAGNETITE;		public static int VALUE_MAGNTITE;
	public static int FREQUENCY_HEMATITE;		public static int SIZE_MIN_HEMATITE;		public static int SIZE_MAX_HEMATITE;		public static int VALUE_HEMATITE;
	public static int FREQUENCY_GOETHITE;		public static int SIZE_MIN_GOETHITE;		public static int SIZE_MAX_GOETHITE;		public static int VALUE_GOETHITE;
	public static int FREQUENCY_LIMONITE;		public static int SIZE_MIN_LIMONITE;		public static int SIZE_MAX_LIMONITE;		public static int VALUE_LIMONITE;
	public static int FREQUENCY_SIDERITE;		public static int SIZE_MIN_SIDERITE;		public static int SIZE_MAX_SIDERITE;		public static int VALUE_SIDERITE;
	public static int FREQUENCY_BOG_IRON;		public static int SIZE_MIN_BOG_IRON;		public static int SIZE_MAX_BOG_IRON;		public static int VALUE_BOG_IRON;
	public static int FREQUENCY_TACONITE;		public static int SIZE_MIN_TACONITE;		public static int SIZE_MAX_TACONITE;		public static int VALUE_TACONITE;
	public static int FREQUENCY_BANDED_IRON;	public static int SIZE_MIN_BANDED_IRON;		public static int SIZE_MAX_BANDED_IRON;		public static int VALUE_BANDED_IRON;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0){
			generateOverworld(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
		}else if(world.provider.getDimension() == ModConfig.AROMA_ID){
			generateAroma(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
		}else if(world.provider.getDimension() == ModConfig.DEEPDARK_ID){
			generateDeepDark(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
		}else{
			for(int X = 0; X < ModConfig.dimensions.length; X++ ){
				if(isNotBuiltIn(ModConfig.dimensions[X])){
					if(world.provider.getDimension() == ModConfig.dimensions[X]){
						generateCustom(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
				}
			}
		}
	}

	private static boolean isNotBuiltIn(int dim) {
		return dim != 0 && dim != ModConfig.AROMA_ID && dim != ModConfig.DEEPDARK_ID;
	}

	private void generateCustom(World world, Random random, BlockPos pos) {
		if(FREQUENCY_ANTHRACITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 0, 	 world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, FREQUENCY_ANTHRACITE, 15, 29, Blocks.STONE);//anthracite
		}
		if(FREQUENCY_BITUMINOUS > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 1, 	 world, random, pos, 16, 16, SIZE_MIN_BITUMINOUS, SIZE_MAX_BITUMINOUS, FREQUENCY_BITUMINOUS, 28, 39, Blocks.STONE);//bituminous
		}
		if(FREQUENCY_VANILLA > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(Blocks.COAL_ORE, 0, 		 world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, FREQUENCY_VANILLA, 		 40, 70, Blocks.STONE);//sub
			int RARITY_VANILLA = FREQUENCY_ANTHRACITE / 4; if(RARITY_VANILLA < 1){RARITY_VANILLA = 1;}
			addNewOre(Blocks.COAL_ORE, 0, 		 world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, RARITY_VANILLA, 			 80, 250, Blocks.STONE);//sub
		}
		if(this.rand.nextInt(20 + (FREQUENCY_SEAMFIRE * 2)) == 0){
			if(FREQUENCY_SEAMFIRE > 0){
				addNewOre(ModBlocks.SEAM_FIRE, 0,  world, random, pos, 16, 16, SIZE_MIN_SEAMFIRE, SIZE_MAX_SEAMFIRE, FREQUENCY_SEAMFIRE, 	 40, 55, Blocks.STONE);//seam fire
			}
		}
		if(FREQUENCY_LIGNITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, FREQUENCY_LIGNITE, 			 60, 200, Blocks.STONE);//lignite
			int RARITY_LIGNITE = FREQUENCY_ANTHRACITE / 4; if(RARITY_LIGNITE < 1){RARITY_LIGNITE = 1;}
			addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, RARITY_LIGNITE, 			 80, 250, Blocks.STONE);//lignite
		}
		if(FREQUENCY_PEAT > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_PEAT, SIZE_MAX_PEAT, FREQUENCY_PEAT, 					 60, 80, Blocks.DIRT);//peat
		}

		if(FREQUENCY_MAGNETITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_MAGNETITE, SIZE_MAX_MAGNETITE, FREQUENCY_MAGNETITE, 	 20, 32, Blocks.STONE);//magnetite
		}
		if(FREQUENCY_HEMATITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 1,  world, random, pos, 16, 16, SIZE_MIN_HEMATITE, SIZE_MAX_HEMATITE, FREQUENCY_HEMATITE, 		 14, 25, Blocks.STONE);//hematite
		}
		if(FREQUENCY_LIMONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_LIMONITE, SIZE_MAX_LIMONITE, FREQUENCY_LIMONITE, 		 30, 45, Blocks.STONE);//limonite
		}
		if(FREQUENCY_GOETHITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){	
			addNewOre(Blocks.IRON_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_GOETHITE, SIZE_MAX_GOETHITE, FREQUENCY_GOETHITE, 			 45, 65, Blocks.STONE);//goethite
		}
		if(FREQUENCY_SIDERITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 4,  world, random, pos, 16, 16, SIZE_MIN_SIDERITE, SIZE_MAX_SIDERITE, FREQUENCY_SIDERITE, 		 55, 70, Blocks.STONE);//siderite
			int RARITY_SIDERITE = FREQUENCY_SIDERITE / 4; if(RARITY_SIDERITE < 1){RARITY_SIDERITE = 1;}
			addNewOre(ModBlocks.IRON_ORES, 4,  world, random, pos, 16, 16, SIZE_MIN_SIDERITE, SIZE_MAX_SIDERITE, RARITY_SIDERITE, 			 80, 250, Blocks.STONE);//siderite
		}
		if(FREQUENCY_TACONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 6, world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, FREQUENCY_TACONITE, 		 70, 250, Blocks.STONE);//taconite
			int RARITY_TACONITE = FREQUENCY_TACONITE / 4; if(RARITY_TACONITE < 1){RARITY_TACONITE = 1;}
			addNewOre(ModBlocks.IRON_ORES, 6,  world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, RARITY_TACONITE, 			 80, 250, Blocks.STONE);//TACONITE
		}
		if(FREQUENCY_BANDED_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 7, world, random, pos, 16, 16, SIZE_MIN_BANDED_IRON, SIZE_MAX_BANDED_IRON, FREQUENCY_BANDED_IRON,70, 250, Blocks.STONE);//bif
			int RARITY_BANDED_IRON = FREQUENCY_BANDED_IRON / 4; if(RARITY_BANDED_IRON < 1){RARITY_BANDED_IRON = 1;}
			addNewOre(ModBlocks.IRON_ORES, 7,  world, random, pos, 16, 16, SIZE_MIN_BANDED_IRON, SIZE_MAX_BANDED_IRON, RARITY_BANDED_IRON,  80, 250, Blocks.STONE);//BANDED_IRON
		}
		if(FREQUENCY_BOG_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 5, world, random, pos, 16, 16, SIZE_MIN_BOG_IRON, SIZE_MAX_BOG_IRON, FREQUENCY_BOG_IRON, 		 15, 80, Blocks.DIRT);//bog
		}
	}

	private void generateDeepDark(World world, Random random, BlockPos pos) {
		if(FREQUENCY_ANTHRACITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 0, 	 world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, FREQUENCY_ANTHRACITE, 15, 29, Blocks.STONE);//anthracite
			int RARITY_ANTHRACITE = FREQUENCY_ANTHRACITE / 4; if(RARITY_ANTHRACITE < 1){RARITY_ANTHRACITE = 1;}
			addNewOre(ModBlocks.COAL_ORES, 0, 	 world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, RARITY_ANTHRACITE, 	 150, 250, Blocks.STONE);//anthracite
		}
		if(FREQUENCY_BITUMINOUS > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 1, 	 world, random, pos, 16, 16, SIZE_MIN_BITUMINOUS, SIZE_MAX_BITUMINOUS, FREQUENCY_BITUMINOUS, 29, 40, Blocks.STONE);//bituminous
			int RARITY_BITUMINOUS = FREQUENCY_ANTHRACITE / 4; if(RARITY_BITUMINOUS < 1){RARITY_BITUMINOUS = 1;}
			addNewOre(ModBlocks.COAL_ORES, 1, 	 world, random, pos, 16, 16, SIZE_MIN_BITUMINOUS, SIZE_MAX_BITUMINOUS, RARITY_BITUMINOUS, 	 150, 250, Blocks.STONE);//bituminous
		}
		if(FREQUENCY_VANILLA > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(Blocks.COAL_ORE, 0, 		 world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, FREQUENCY_VANILLA, 		 40, 70, Blocks.STONE);//sub
			int RARITY_VANILLA = FREQUENCY_ANTHRACITE / 4; if(RARITY_VANILLA < 1){RARITY_VANILLA = 1;}
			addNewOre(Blocks.COAL_ORE, 0, 		 world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, RARITY_VANILLA, 			 150, 250, Blocks.STONE);//sub
		}
		if(this.rand.nextInt(20 + (FREQUENCY_SEAMFIRE * 2)) == 0){
			if(FREQUENCY_SEAMFIRE > 0){
				addNewOre(ModBlocks.SEAM_FIRE, 0,  world, random, pos, 16, 16, SIZE_MIN_SEAMFIRE, SIZE_MAX_SEAMFIRE, FREQUENCY_SEAMFIRE, 	 40, 55, Blocks.STONE);//seam fire
			}
		}
		if(FREQUENCY_LIGNITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, FREQUENCY_LIGNITE, 			 60, 150, Blocks.STONE);//lignite
			int RARITY_LIGNITE = FREQUENCY_ANTHRACITE / 4; if(RARITY_LIGNITE < 1){RARITY_LIGNITE = 1;}
			addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, RARITY_LIGNITE, 			 150, 250, Blocks.STONE);//lignite
		}
		if(FREQUENCY_PEAT > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_PEAT, SIZE_MAX_PEAT, FREQUENCY_PEAT, 					 60, 100, Blocks.COBBLESTONE);//peat
		}

		if(FREQUENCY_MAGNETITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_MAGNETITE, SIZE_MAX_MAGNETITE, FREQUENCY_MAGNETITE, 	 18, 30, Blocks.STONE);//magnetite
		}
		if(FREQUENCY_HEMATITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 1,  world, random, pos, 16, 16, SIZE_MIN_HEMATITE, SIZE_MAX_HEMATITE, FREQUENCY_HEMATITE, 		 14, 26, Blocks.STONE);//hematite
		}
		if(FREQUENCY_LIMONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_LIMONITE, SIZE_MAX_LIMONITE, FREQUENCY_LIMONITE, 		 32, 45, Blocks.STONE);//limonite
		}
		if(FREQUENCY_GOETHITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){	
			addNewOre(Blocks.IRON_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_GOETHITE, SIZE_MAX_GOETHITE, FREQUENCY_GOETHITE, 			 45, 65, Blocks.STONE);//goethite
		}
		if(FREQUENCY_SIDERITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 4,  world, random, pos, 16, 16, SIZE_MIN_SIDERITE, SIZE_MAX_SIDERITE, FREQUENCY_SIDERITE, 		 55, 70, Blocks.STONE);//siderite
		}
		if(FREQUENCY_TACONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 6, world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, FREQUENCY_TACONITE, 		 150, 250, Blocks.STONE);//taconite
		}
		if(FREQUENCY_BANDED_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 7, world, random, pos, 16, 16, SIZE_MIN_BANDED_IRON, SIZE_MAX_BANDED_IRON, FREQUENCY_BANDED_IRON,150, 250, Blocks.STONE);//bif
		}
		if(FREQUENCY_BOG_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 5, world, random, pos, 16, 16, SIZE_MIN_BOG_IRON, SIZE_MAX_BOG_IRON, FREQUENCY_BOG_IRON, 		 15, 250, Blocks.COBBLESTONE);//bog
		}
	}

	private void generateAroma(World world, Random random, BlockPos pos) {
		int aromaCoalDivision = ModConfig.AROMA_HEIGHT / 7;
		if(FREQUENCY_ANTHRACITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 0, 	 world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, FREQUENCY_ANTHRACITE, aromaCoalDivision, (aromaCoalDivision * 2), Blocks.STONE);//anthracite
		}
		if(FREQUENCY_BITUMINOUS > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 1, 	 world, random, pos, 16, 16, SIZE_MIN_BITUMINOUS, SIZE_MAX_BITUMINOUS, FREQUENCY_BITUMINOUS, (aromaCoalDivision * 2), (aromaCoalDivision * 3), Blocks.STONE);//bituminous
		}
		if(FREQUENCY_VANILLA > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(Blocks.COAL_ORE, 0, 		 world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, FREQUENCY_VANILLA, 		 (aromaCoalDivision * 3), (aromaCoalDivision * 4), Blocks.STONE);//sub
		}
		if(this.rand.nextInt(20 + (FREQUENCY_SEAMFIRE * 2)) == 0){
			if(FREQUENCY_SEAMFIRE > 0){
				addNewOre(ModBlocks.SEAM_FIRE, 0,  world, random, pos, 16, 16, SIZE_MIN_SEAMFIRE, SIZE_MAX_SEAMFIRE, FREQUENCY_SEAMFIRE, (aromaCoalDivision * 4), (aromaCoalDivision * 5), Blocks.STONE);//seam fire
			}
		}
		if(FREQUENCY_LIGNITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, FREQUENCY_LIGNITE, (aromaCoalDivision * 5), (aromaCoalDivision * 6), Blocks.STONE);//lignite
		}
		if(FREQUENCY_PEAT > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.COAL_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_PEAT, SIZE_MAX_PEAT, FREQUENCY_PEAT, (aromaCoalDivision * 6), (aromaCoalDivision * 7), Blocks.DIRT);//peat
			addNewOre(ModBlocks.COAL_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_PEAT, SIZE_MAX_PEAT, FREQUENCY_PEAT, (aromaCoalDivision * 6), (aromaCoalDivision * 7), Blocks.STONE);//peat
		}

		int aromaIronDivision = ModConfig.AROMA_HEIGHT / 9;
		if(FREQUENCY_MAGNETITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_MAGNETITE, SIZE_MAX_MAGNETITE, FREQUENCY_MAGNETITE, aromaIronDivision, (aromaIronDivision * 2), Blocks.STONE);//magnetite
		}
		if(FREQUENCY_HEMATITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 1,  world, random, pos, 16, 16, SIZE_MIN_HEMATITE, SIZE_MAX_HEMATITE, FREQUENCY_HEMATITE, (aromaIronDivision * 2), (aromaIronDivision * 3), Blocks.STONE);//hematite
		}
		if(FREQUENCY_LIMONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_LIMONITE, SIZE_MAX_LIMONITE, FREQUENCY_LIMONITE, (aromaIronDivision * 3), (aromaIronDivision * 4), Blocks.STONE);//limonite
		}
		if(FREQUENCY_GOETHITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){	
			addNewOre(Blocks.IRON_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_GOETHITE, SIZE_MAX_GOETHITE, FREQUENCY_GOETHITE, (aromaIronDivision * 4), (aromaIronDivision * 5), Blocks.STONE);//goethite
		}
		if(FREQUENCY_SIDERITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 4,  world, random, pos, 16, 16, SIZE_MIN_SIDERITE, SIZE_MAX_SIDERITE, FREQUENCY_SIDERITE, (aromaIronDivision * 5), (aromaIronDivision * 6), Blocks.STONE);//siderite
		}
		if(FREQUENCY_TACONITE > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 6, world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, FREQUENCY_TACONITE, (aromaIronDivision * 6), (aromaIronDivision * 7), Blocks.STONE);//taconite
		}
		if(FREQUENCY_BANDED_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 7, world, random, pos, 16, 16, SIZE_MIN_BANDED_IRON, SIZE_MAX_BANDED_IRON, FREQUENCY_BANDED_IRON, (aromaIronDivision * 7), (aromaIronDivision * 8), Blocks.STONE);//bif
		}
		if(FREQUENCY_BOG_IRON > 0 && (this.rand.nextInt(100) + 1) <= ModConfig.FORFAIT_CHANCE){
			addNewOre(ModBlocks.IRON_ORES, 5, world, random, pos, 16, 16, SIZE_MIN_BOG_IRON, SIZE_MAX_BOG_IRON, FREQUENCY_BOG_IRON, (aromaIronDivision * 8), (aromaIronDivision * 9), Blocks.DIRT);//bog
			addNewOre(ModBlocks.IRON_ORES, 5, world, random, pos, 16, 16, SIZE_MIN_BOG_IRON, SIZE_MAX_BOG_IRON, FREQUENCY_BOG_IRON, (aromaIronDivision * 8), (aromaIronDivision * 9), Blocks.STONE);//bog
		}
	}

	private void generateOverworld(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
//COAL SPAWN
		if(ModConfig.enableCoalTiers){
			if(!isBiome(biome, Type.OCEAN)){

				if(!isBiome(biome, Type.DEAD) && !isBiome(biome, Type.SANDY)){
					if(FREQUENCY_ANTHRACITE > 0){
						addNewOre(ModBlocks.COAL_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, FREQUENCY_ANTHRACITE, 15, 29, Blocks.STONE);//anthracite
						int RARITY_ANTHRACITE = FREQUENCY_ANTHRACITE / 4; if(RARITY_ANTHRACITE < 1){RARITY_ANTHRACITE = 1;}
						addNewOre(ModBlocks.COAL_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, RARITY_ANTHRACITE, 120, 200, Blocks.STONE);//anthracite
					}
				}

				if(isBiome(biome, Type.SANDY)){
					if(FREQUENCY_ANTHRACITE > 0){
						int RARITY_ANTHRACITE = FREQUENCY_ANTHRACITE / 2; if(RARITY_ANTHRACITE < 1){RARITY_ANTHRACITE = 1;}
						addNewOre(ModBlocks.COAL_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_ANTHRACITE, SIZE_MAX_ANTHRACITE, RARITY_ANTHRACITE, 15, 29, Blocks.STONE);//anthracite
					}
				}else{
					if(FREQUENCY_LIGNITE > 0){
						addNewOre(ModBlocks.COAL_ORES, 2,  world, random, pos, 16, 16, SIZE_MIN_LIGNITE, SIZE_MAX_LIGNITE, FREQUENCY_LIGNITE, 60, 150, Blocks.STONE);//lignite
					}
				}

				if(isBiome(biome, Type.WET)){
					if(FREQUENCY_PEAT > 0){
						addNewOre(ModBlocks.COAL_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_PEAT, SIZE_MAX_PEAT, FREQUENCY_PEAT, world.getSeaLevel() - 8, world.getSeaLevel() + 8, Blocks.DIRT);//peat
					}
					if(FREQUENCY_VANILLA > 0){
						int RARITY_VANILLA = FREQUENCY_VANILLA / 4; if(RARITY_VANILLA < 1){RARITY_VANILLA = 1;}
						addNewOre(Blocks.COAL_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, RARITY_VANILLA, 40, 70, Blocks.STONE);//sub
					}
				}else{
					if(FREQUENCY_VANILLA > 0){
						addNewOre(Blocks.COAL_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_VANILLA, SIZE_MAX_VANILLA, FREQUENCY_VANILLA, 40, 70, Blocks.STONE);//sub
					}
					if(FREQUENCY_BITUMINOUS > 0){
						addNewOre(ModBlocks.COAL_ORES, 1,  world, random, pos, 16, 16, SIZE_MIN_BITUMINOUS, SIZE_MAX_BITUMINOUS, FREQUENCY_BITUMINOUS, 28, 39, Blocks.STONE);//bituminous
					}
					if(!isBiome(biome, Type.BEACH) && this.rand.nextInt(20 + (FREQUENCY_SEAMFIRE * 2)) == 0){
						if(FREQUENCY_SEAMFIRE > 0){
							addNewOre(ModBlocks.SEAM_FIRE, 0,  world, random, pos, 16, 16, SIZE_MIN_SEAMFIRE, SIZE_MAX_SEAMFIRE, FREQUENCY_SEAMFIRE, 40, 55, Blocks.STONE);//seam fire
						}
					}
				}
			}
		}

//IRON SPAWN
		if(ModConfig.enableIronTiers){
			if(isBiome(biome, Type.HOT)){
				if(FREQUENCY_HEMATITE > 0){
					addNewOre(ModBlocks.IRON_ORES, 1,  world, random, pos, 16, 16, SIZE_MIN_HEMATITE, SIZE_MAX_HEMATITE, FREQUENCY_HEMATITE, 14, 25, Blocks.STONE);//hematite
				}
			}else{
				if(FREQUENCY_MAGNETITE > 0){
					addNewOre(ModBlocks.IRON_ORES, 0,  world, random, pos, 16, 16, SIZE_MIN_MAGNETITE, SIZE_MAX_MAGNETITE, FREQUENCY_MAGNETITE, 20, 32, Blocks.STONE);//magnetite
				}
			}

			if(isBiome(biome, Type.SANDY)){
				if(FREQUENCY_LIMONITE > 0){
					addNewOre(ModBlocks.IRON_ORES, 3,  world, random, pos, 16, 16, SIZE_MIN_LIMONITE, SIZE_MAX_LIMONITE, FREQUENCY_LIMONITE, 45, 60, Blocks.STONE);//limonite
				}
			}else{
				if(FREQUENCY_GOETHITE > 0){	
					addNewOre(Blocks.IRON_ORE, 0,  world, random, pos, 16, 16, SIZE_MIN_GOETHITE, SIZE_MAX_GOETHITE, FREQUENCY_GOETHITE, 40, 55, Blocks.STONE);//goethite
				}
			}

			if(!isBiome(biome, Type.WET) && !isBiome(biome, Type.SWAMP)){
				if(FREQUENCY_SIDERITE > 0){
					addNewOre(ModBlocks.IRON_ORES, 4,  world, random, pos, 16, 16, SIZE_MIN_SIDERITE, SIZE_MAX_SIDERITE, FREQUENCY_SIDERITE, 55, 70, Blocks.STONE);//siderite
				}
			}

			if(isBiome(biome, Type.SWAMP)){
				if(FREQUENCY_BOG_IRON > 0){
					addNewOre(ModBlocks.IRON_ORES, 5, world, random, pos, 16, 16, SIZE_MIN_BOG_IRON, SIZE_MAX_BOG_IRON, FREQUENCY_BOG_IRON, world.getSeaLevel() - 8, world.getSeaLevel() + 8, Blocks.DIRT);//bog
				}
			}

			if(isBiome(biome, Type.HILLS) || isBiome(biome, Type.MOUNTAIN)){
				if(FREQUENCY_BANDED_IRON > 0){
					addNewOre(ModBlocks.IRON_ORES, 7, world, random, pos, 16, 16, SIZE_MIN_BANDED_IRON, SIZE_MAX_BANDED_IRON, FREQUENCY_BANDED_IRON, 80, 150, Blocks.STONE);//bif
				}
			}
			if(FREQUENCY_TACONITE > 0){
				addNewOre(ModBlocks.IRON_ORES, 6, world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, FREQUENCY_TACONITE, 70, 90, Blocks.STONE);//taconite
				
				int RARITY_TACONITE = FREQUENCY_TACONITE / 4; if(RARITY_TACONITE < 1){RARITY_TACONITE = 1;}
				addNewOre(ModBlocks.IRON_ORES, 6, world, random, pos, 16, 16, SIZE_MIN_TACONITE, SIZE_MAX_TACONITE, RARITY_TACONITE, 100, 200, Blocks.STONE);//taconite
			}
		}
	}

	private static boolean isBiome(Biome biome, Type type) {
		return BiomeDictionary.hasType(biome, type);
	}

	private static void addNewOre(Block block, int metadata, World world, Random random, BlockPos pos, int maxX, int maxZ, int minVeinSize, int maxVeinSize, int chanceToSpawn, int minY, int maxY, Block generateIn) {
		if (minY < 0 || maxY > 256 || minY > maxY) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		int coalVeinSize = minVeinSize + random.nextInt(1 + (maxVeinSize - minVeinSize));
		for (int i = 0; i < chanceToSpawn + 1; i++) {
			int x = pos.getX() + random.nextInt(maxX);
			int y = minY + random.nextInt(1 + (maxY - minY));
			int z = pos.getZ() + random.nextInt(maxZ);
            IBlockState state = block.getStateFromMeta(metadata);
			WorldGenMinable mine = new WorldGenMinable(state, coalVeinSize + 1, BlockMatcher.forBlock(generateIn));
			mine.generate(world, random, new BlockPos(x, y, z));
		}
	}
}