package com.globbypotato.rockhounding_oretiers.machines.recipes;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.utils.BaseRecipes;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;
import com.globbypotato.rockhounding_oretiers.world.TiersGenerator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class MachineRecipes extends BaseRecipes{
	public static final ArrayList<DrierRecipes> drierRecipe = new ArrayList<DrierRecipes>();
	public static final ArrayList<RefinerRecipes> refinerRecipe = new ArrayList<RefinerRecipes>();
	public static final ArrayList<BloomeryRecipes> bloomeryRecipe = new ArrayList<BloomeryRecipes>();

	public static void machinesRecipes() {
		if(ToolUtils.registerAsOres()){
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 0), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_MAGNTITE), 	new ItemStack(Items.IRON_INGOT))); //magnetite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 1), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_HEMATITE), 	new ItemStack(Items.IRON_INGOT))); //hematite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 2),	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), 	new ItemStack(Items.IRON_INGOT))); //goethite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 3), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_LIMONITE), 	new ItemStack(Items.IRON_INGOT))); //limonite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 4), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_SIDERITE), 	new ItemStack(Items.IRON_INGOT))); //siderite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 5), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BOG_IRON), 	new ItemStack(Items.IRON_INGOT))); //bog iron
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 6), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_TACONITE), 	new ItemStack(Items.IRON_INGOT))); //taconite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.IRON_ORES, 1, 7), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BANDED_IRON), 	new ItemStack(Items.IRON_INGOT))); //banded iron
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(Blocks.IRON_ORE), 				new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), 	new ItemStack(Items.IRON_INGOT))); //vanilla Iron
		}
		if(ToolUtils.registerAsPebbles()){
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 0), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_MAGNTITE/4), 		new ItemStack(Items.IRON_INGOT))); //magnetite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 1), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_HEMATITE/4), 		new ItemStack(Items.IRON_INGOT))); //hematite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 2),	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE/4), 		new ItemStack(Items.IRON_INGOT))); //goethite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 3), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_LIMONITE/4), 		new ItemStack(Items.IRON_INGOT))); //limonite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 4), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_SIDERITE/4), 		new ItemStack(Items.IRON_INGOT))); //siderite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 5), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BOG_IRON/4), 		new ItemStack(Items.IRON_INGOT))); //bog iron
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 6), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_TACONITE/4), 		new ItemStack(Items.IRON_INGOT))); //taconite
			bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModItems.IRON_PEBBLES, 1, 7), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BANDED_IRON/4),	new ItemStack(Items.IRON_INGOT))); //banded iron
		}

		drierRecipe.add(new DrierRecipes(new ItemStack(ModItems.TIER_ITEMS, 1, 3), 			false,	new ItemStack(ModItems.TIER_ITEMS, 1, 6),	4000)); //peat+

		refinerRecipe.add(new RefinerRecipes(new ItemStack(ModItems.TIER_ITEMS, 1, 2), 		false,	new ItemStack(Items.COAL),					1500)); //lignite+
		refinerRecipe.add(new RefinerRecipes(new ItemStack(Items.COAL),				 		false,	new ItemStack(ModItems.TIER_ITEMS, 1, 1),	2000)); //coal+
	}
}