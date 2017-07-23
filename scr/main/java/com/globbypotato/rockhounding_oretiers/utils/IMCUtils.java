package com.globbypotato.rockhounding_oretiers.utils;

import java.util.List;

import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;

public class IMCUtils {
	public static String BLOOMERY_KEY = "addToBloomery";
	public static String BLOOMERY_KEY_REMOVER = "removeFromBloomery";
	public static String REFINERY_KEY = "addToRefinery";
	public static String REFINERY_KEY_REMOVER = "removeFromRefinery";
	public static String PALLET_KEY = "addToPallet";
	public static String PALLET_KEY_REMOVER = "removeFromPallet";
	static ItemStack input;
	static FluidStack molten;
	static ItemStack output;

	public static void extraRecipes(List<IMCMessage> messages) {
		for(IMCMessage message : messages) {
			if(message.isNBTMessage()){
				try {
		    		NBTTagCompound tag = message.getNBTValue();
		    		/**
		    		 * REMOVE RECIPES
		    		 */
		    		if(message.key.equalsIgnoreCase(BLOOMERY_KEY_REMOVER)){
		    			ItemStack casted = null;
		        		if(tag.hasKey("Input")){
		        			casted = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(casted != null){
		        			for(int x = 0; x < MachineRecipes.bloomeryRecipe.size(); x++){
		        				if(MachineRecipes.bloomeryRecipe.get(x).getInput().isItemEqual(casted)){
		        					MachineRecipes.bloomeryRecipe.remove(x);
		        				}
		        			}
		        		}
		    		}else if(message.key.equalsIgnoreCase(REFINERY_KEY_REMOVER)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(input != null){
		        			for(int x = 0; x < MachineRecipes.refinerRecipe.size(); x++){
		        				if(MachineRecipes.refinerRecipe.get(x).getInput().isItemEqual(input)){
		        					MachineRecipes.refinerRecipe.remove(x);
		        				}
		        			}
		        		}
		    		}else if(message.key.equalsIgnoreCase(PALLET_KEY_REMOVER)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(input != null){
		        			for(int x = 0; x < MachineRecipes.drierRecipe.size(); x++){
		        				if(MachineRecipes.drierRecipe.get(x).getInput().isItemEqual(input)){
		        					MachineRecipes.drierRecipe.remove(x);
		        				}
		        			}
		        		}

		    		/**
		    		 * ADD RECIPES
		    		 */
		    		}else if(message.key.equalsIgnoreCase(BLOOMERY_KEY)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(tag.hasKey("Molten")){
		        			molten = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("Molten"));
		        		}
		        		if(tag.hasKey("Output")){
		        			output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
		        		}
		        		if(input != null && molten != null && output != null){
		        			MachineRecipes.bloomeryRecipe.add(new BloomeryRecipes(input, molten, output));
		        		}
		    		}else if(message.key.equalsIgnoreCase(REFINERY_KEY)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(tag.hasKey("Output")){
		        			output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
		        		}
		        		if(input != null && output != null){
		        			MachineRecipes.refinerRecipe.add(new RefinerRecipes(input, output));
		        		}
		    		}else if(message.key.equalsIgnoreCase(PALLET_KEY)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(tag.hasKey("Output")){
		        			output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
		        		}
		        		if(input != null && output != null){
		        			MachineRecipes.drierRecipe.add(new DrierRecipes(input, output));
		        		}
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}