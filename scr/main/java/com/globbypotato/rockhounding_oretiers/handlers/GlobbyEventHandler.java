package com.globbypotato.rockhounding_oretiers.handlers;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class GlobbyEventHandler {

    @SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleTooltip(ItemTooltipEvent event){
		ItemStack itemstack = event.getItemStack();
		if(!itemstack.isEmpty()){
			ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(itemstack));
			for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
				if(	(!recipe.getType() && !recipe.getInput().isEmpty() && recipe.getInput().isItemEqual(itemstack)) 
				 || (recipe.getType() && !inputOreIDs.isEmpty() && inputOreIDs.contains(OreDictionary.getOreID(recipe.getOredict()))) ){
					event.getToolTip().add(TextFormatting.DARK_GRAY + "Melts into: " + TextFormatting.GOLD + recipe.getMolten().amount + " mB of " + recipe.getMolten().getLocalizedName());
					event.getToolTip().add(TextFormatting.DARK_GRAY + "Casts into: " + TextFormatting.WHITE + recipe.getOutput().getDisplayName());
				}
			}
		}
	}
}