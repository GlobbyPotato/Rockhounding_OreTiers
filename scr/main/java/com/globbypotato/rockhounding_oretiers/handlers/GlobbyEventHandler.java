package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GlobbyEventHandler {

	
    @SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleTooltip(ItemTooltipEvent event){
		ItemStack itemstack = event.getItemStack();
		for(BloomeryRecipes recipe: MachineRecipes.bloomeryRecipe){
			if(!recipe.getInput().isEmpty() && !itemstack.isEmpty() && ItemStack.areItemsEqual(recipe.getInput().copy(), itemstack)){
				event.getToolTip().add(TextFormatting.DARK_GRAY + "Melts into: " + TextFormatting.GOLD + recipe.getMolten().amount + " mB of " + recipe.getMolten().getLocalizedName());
				event.getToolTip().add(TextFormatting.DARK_GRAY + "Casts into: " + TextFormatting.WHITE + recipe.getOutput().getDisplayName());
			}
		}
	}
}