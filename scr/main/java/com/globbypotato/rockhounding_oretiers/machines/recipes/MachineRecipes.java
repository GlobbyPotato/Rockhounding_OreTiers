package com.globbypotato.rockhounding_oretiers.machines.recipes;

import java.util.ArrayList;

import com.globbypotato.rockhounding_oretiers.ModBlocks;
import com.globbypotato.rockhounding_oretiers.ModItems;
import com.globbypotato.rockhounding_oretiers.fluids.ModFluids;
import com.globbypotato.rockhounding_oretiers.utils.BaseRecipes;
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
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 0), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_MAGNTITE), 	new ItemStack(Items.IRON_INGOT))); //magnetite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 1), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_HEMATITE), 	new ItemStack(Items.IRON_INGOT))); //hematite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 2),		new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), 	new ItemStack(Items.IRON_INGOT))); //goethite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 3), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_LIMONITE), 	new ItemStack(Items.IRON_INGOT))); //limonite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 4), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_SIDERITE), 	new ItemStack(Items.IRON_INGOT))); //siderite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 5), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BOG_IRON), 	new ItemStack(Items.IRON_INGOT))); //bog iron
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 6), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_TACONITE), 	new ItemStack(Items.IRON_INGOT))); //taconite
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(ModBlocks.ironOres, 1, 7), 	new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_BANDED_IRON), 	new ItemStack(Items.IRON_INGOT))); //banded iron
		bloomeryRecipe.add(new BloomeryRecipes(new ItemStack(Blocks.IRON_ORE), 				new FluidStack(ModFluids.BLOOM, TiersGenerator.VALUE_GOETHITE), 	new ItemStack(Items.IRON_INGOT))); //vanilla Iron

		drierRecipe.add(new DrierRecipes(new ItemStack(ModItems.tiersItems, 1, 3), 			new ItemStack(ModItems.tiersItems, 1, 6))); //peat+

		refinerRecipe.add(new RefinerRecipes(new ItemStack(ModItems.tiersItems, 1, 2), 		new ItemStack(Items.COAL))); //lignite+
		refinerRecipe.add(new RefinerRecipes(new ItemStack(Items.COAL),				 		new ItemStack(ModItems.tiersItems, 1, 1))); //coal+
	}
}