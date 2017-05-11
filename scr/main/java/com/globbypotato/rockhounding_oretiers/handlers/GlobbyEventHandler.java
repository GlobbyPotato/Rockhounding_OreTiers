package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GlobbyEventHandler {

	@SubscribeEvent
	public void handleTooltip(ItemTooltipEvent event){
		if(ModConfig.enableBloomery){
			ItemStack itemstack = event.getItemStack();
			for(BloomeryRecipes recipe: ModRecipes.bloomeryRecipe){
				if(recipe.getInput() != null && itemstack != null && ItemStack.areItemsEqual(recipe.getInput().copy(), itemstack)){
					event.getToolTip().add(TextFormatting.DARK_GRAY + "Melts into: " + TextFormatting.GOLD + recipe.getMolten().amount + " mB of " + recipe.getMolten().getLocalizedName());
					event.getToolTip().add(TextFormatting.DARK_GRAY + "Casts into: " + TextFormatting.WHITE + recipe.getOutput().getDisplayName());
				}
			}
		}
	}
}