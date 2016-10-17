package com.globbypotato.rockhounding_oretiers.world;

import java.util.Random;

import com.globbypotato.rockhounding_oretiers.ModContents;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TiersOresGenerator implements IWorldGenerator {

	public static int coalFrequency; public static int coalMinVein; public static int coalMaxVein;
	public static int ironFrequency; public static int ironMinVein; public static int ironMaxVein;
	Random rand = new Random();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
			case 1: generateEnd(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
			case 0: generateOverworld(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
			case -1: generateNether(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
		}
	}

	private void generateEnd(World world, Random random, BlockPos pos) { }
	private void generateNether(World world, Random random, BlockPos pos) { }
	private void generateOverworld(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiomeGenForCoords(pos);

	//Coal ores
		if(!BiomeDictionary.isBiomeOfType(biome, Type.DEAD) && !BiomeDictionary.isBiomeOfType(biome, Type.SANDY)  && !BiomeDictionary.isBiomeOfType(biome, Type.OCEAN)){
			addNewOre(ModContents.coalOres, 0,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency, 15, 33, Blocks.STONE);//anthracite
		}
		
		if(BiomeDictionary.isBiomeOfType(biome, Type.SANDY)){
			addNewOre(ModContents.coalOres, 0,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency / 2, 15, 33, Blocks.STONE);//anthracite
		}else{
			addNewOre(ModContents.coalOres, 2,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency, 60, 150, Blocks.STONE);//lignite
		}

		addNewOre(ModContents.coalOres, 0,  world, random, pos, 16, 16, coalMinVein, coalMinVein + 1, coalFrequency / 4, 120, 200, Blocks.STONE);//anthracite

		if(BiomeDictionary.isBiomeOfType(biome, Type.WET)){
			addNewOre(ModContents.coalOres, 3,  world, random, pos, 16, 16, coalMinVein, coalMaxVein * 3, coalFrequency * 2, world.getSeaLevel() - 8, world.getSeaLevel() + 8, Blocks.DIRT);//peat
			addNewOre(Blocks.COAL_ORE, 0,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency / 4, 40, 70, Blocks.STONE);//sub
		}else{
			if(!BiomeDictionary.isBiomeOfType(biome, Type.OCEAN)){
				addNewOre(Blocks.COAL_ORE, 0,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency, 40, 70, Blocks.STONE);//sub
				addNewOre(ModContents.coalOres, 1,  world, random, pos, 16, 16, coalMinVein, coalMaxVein, coalFrequency, 29, 44, Blocks.STONE);//bituminous
				if(rand.nextInt(20) == 0){addNewOre(ModContents.seamFire, 0,  world, random, pos, 16, 16, (coalMaxVein-1)*3, coalMaxVein*3, 1, 40, 55, Blocks.STONE);//seam fire}
			}
		}



	//Iron Ores
		if(BiomeDictionary.isBiomeOfType(biome, Type.HOT)){
			addNewOre(ModContents.ironOres, 1,  world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 14, 34, Blocks.STONE);//hematite
		}else{
			addNewOre(ModContents.ironOres, 0,  world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 20, 42, Blocks.STONE);//magnetite
		}

		if(BiomeDictionary.isBiomeOfType(biome, Type.SANDY)){
			addNewOre(ModContents.ironOres, 3,  world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 50, 70, Blocks.STONE);//limonite
		}else{
			addNewOre(ModContents.ironOres, 2,  world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 40, 55, Blocks.STONE);//goethite
		}

		if(!BiomeDictionary.isBiomeOfType(biome, Type.WET) && !BiomeDictionary.isBiomeOfType(biome, Type.SWAMP)){
			addNewOre(ModContents.ironOres, 4,  world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 55, 70, Blocks.STONE);//siderite
		}

		if(BiomeDictionary.isBiomeOfType(biome, Type.SWAMP)){
			addNewOre(ModContents.ironOres, 5, world, random, pos, 16, 16, ironMinVein, ironMaxVein * 2, ironFrequency, world.getSeaLevel() - 8, world.getSeaLevel() + 8, Blocks.DIRT);//bog
		}

		if(BiomeDictionary.isBiomeOfType(biome, Type.HILLS) || BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN)){
			addNewOre(ModContents.ironOres, 7, world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 80, 150, Blocks.STONE);//bif
		}

		addNewOre(ModContents.ironOres, 6, world, random, pos, 16, 16, ironMinVein, ironMaxVein, ironFrequency, 70, 90, Blocks.STONE);//taconite
		addNewOre(ModContents.ironOres, 6, world, random, pos, 16, 16, ironMinVein, ironMinVein + 1, ironFrequency / 4, 100, 200, Blocks.STONE);//taconite
	}

	private void addNewOre(Block block, int metadata, World world, Random random, BlockPos pos, int maxX, int maxZ, int minVeinSize, int maxVeinSize, int chanceToSpawn, int minY, int maxY, Block generateIn) {
		if (minY < 0 || maxY > 256 || minY > maxY) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		int coalVeinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
		for (int i = 0; i < chanceToSpawn; i++) {
			int x = pos.getX() + random.nextInt(maxX);
			int y = minY + random.nextInt(maxY - minY);
			int z = pos.getZ() + random.nextInt(maxZ);
			BlockPos blockpos = new BlockPos(x, y, z);
            		IBlockState state = block.getStateFromMeta(metadata);
			WorldGenMinable mine = new WorldGenMinable(state, coalVeinSize, BlockMatcher.forBlock(generateIn));
			mine.generate(world, random, new BlockPos(x, y, z));
		}
	}

}
