package com.globbypotato.rockhounding_oretiers.utils;

import java.util.List;

import com.globbypotato.rockhounding_oretiers.handlers.ModConfig;
import com.globbypotato.rockhounding_oretiers.machines.recipes.BloomeryRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.DrierRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.MachineRecipes;
import com.globbypotato.rockhounding_oretiers.machines.recipes.RefinerRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import rockhounding.api.IReciperBase;

public class IMCUtils extends IReciperBase{
	private static ItemStack input = ItemStack.EMPTY;
	private static ItemStack output = ItemStack.EMPTY;
	private static FluidStack bloom = null;

	public static void extraRecipes(List<IMCMessage> messages) {
		for(IMCMessage message : messages) {
			if(message.isNBTMessage()){
				try {
		    		NBTTagCompound tag = message.getNBTValue();
		    		if(message.key.equalsIgnoreCase(add_bloomery_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		if(tag.hasKey(tagSolvent)){
		        			bloom = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag(tagSolvent));
		        		}
		        		if(tag.hasKey(tagOutput)){
		        			output = new ItemStack(tag.getCompoundTag("Output"));
		        		}
	        			MachineRecipes.bloomeryRecipe.add(new BloomeryRecipes(input, bloom, output));
		    		}else if(message.key.equalsIgnoreCase(add_coal_refiner_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		boolean oredict = false;
		        		if(tag.hasKey(tagOredict)){
		        			oredict = tag.getBoolean(tagOredict);
		        		}
		        		if(tag.hasKey(tagOutput)){
		        			output = new ItemStack(tag.getCompoundTag(tagOutput));
		        		}
		        		int refining = ModConfig.refiningSpeed;
		        		if(tag.hasKey(tagWeights)){
		        			refining = tag.getInteger(tagWeights);
		        		}
	        			MachineRecipes.refinerRecipe.add(new RefinerRecipes(input, output, refining));
		    		}else if(message.key.equalsIgnoreCase(add_drying_pallet_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		boolean oredict = false;
		        		if(tag.hasKey(tagOredict)){
		        			oredict = tag.getBoolean(tagOredict);
		        		}
		        		if(tag.hasKey(tagOutput)){
		        			output = new ItemStack(tag.getCompoundTag(tagOutput));
		        		}
		        		int refining = ModConfig.dryingSpeed;
		        		if(tag.hasKey(tagWeights)){
		        			refining = tag.getInteger(tagWeights);
		        		}
	        			MachineRecipes.drierRecipe.add(new DrierRecipes(input, output, refining));
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}