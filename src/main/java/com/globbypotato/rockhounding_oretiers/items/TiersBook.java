package com.globbypotato.rockhounding_oretiers.items;

import java.util.List;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TiersBook extends ItemWrittenBook {
	
	public TiersBook(String name) {
		super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(Reference.RockhoundingTiers);
        setMaxStackSize(1);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){

			// Create the book 
			itemStackIn = new ItemStack(Items.WRITTEN_BOOK);
			// Create NBT data and add to the book

			NBTTagList bookPages = new NBTTagList();
            ITextComponent string;

            bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(
            			"INTRODUCTION\n" 
					  + "Ore Tiers aims to make a less cheaper and more challenging access to the main resources. Vanilla ores are replaced by tiers of the same ores depending on level, biome, quantity and quality of their product. It takes then exploration and caving for supplies"))));
            bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(
            			"and some planning for the most efficient use. More efficient ores can be usually found deeper than less efficient ones. However a config file will allow to tweak some parameters of the minable resources."))));
	
            bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(
            			"COAL TIERS\n"
					  + "Coal Tiers replace the vanilla coal ore with 5 tiers of coal with a different occurrency and efficiency. Charcoal is replaced as well. Any of the coal types can craft blocks, a different amount of vanilla torches and pellets worth one furnace smelting."))));
            bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(
            			"Anthracite:\n"
            		  + "It can be found in deeper levels (15/33) in any biome excluded dead biomes and rarely at very high heights (120/200). It is the most efficient coal type. It allows 16 furnace smelts, 16 coal pellets and can produce 8 torches.\n"))));
            bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(
            			"Bituminous:\n"
					  + "It can be found in mid-low levels (29/44) in any biome excluded the wet biomes. It allows 12 furnace smelts, 12 coal pellets and can produce 6 torches.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Sub-Bituminous:\n"
					  + "It can be found in levels around the surface (40/70) in any biome, though rarely in wet biomes. This is the vanilla coal. It allows 8 furnace smelts, 8 coal pellets and can produce 4 torches.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Lignite:\n"
					  + "It can be found from the surface to higher levels (60/150) in any biome excluded sandy biomes. It is a reasonably young and weak coal. It allows 5 furnace smelts, 5 coal pellets and can produce 3 torches.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Peat:\n"
					  + "It can be massively found in dirt around the sea level (55/70) in wet biomes only. It is a weak moist coal, so the mined chunk needs to be dried on a Drying Pallet to get the worth dried coal. It allows 2 furnace smelts, 2 coal pellet and can produce 1 torch.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Coal seam fire:\n"
					  + "Under the surface is possible to find clouds of smouldering coal. These are ignited coal deposits constantly burning. They are insidious blocks because can set the player on fire on impact/walking and spread fire around when mining them, so it"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
					   "is not recommended to place them down in sensible areas. Nonenthless, once washed in a filled cauldron they will estinguish into coal Blocks\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Charcoal Lumps:\n"
					  + "It is the charcoal introduced by the mod by smelting woods and has a third of the vanilla charcoal power. It allows 3 furnace smelts, 3 coal pellets and can produce 2 torches.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Peat Drying Pallet:\n"
					  + "This is the required tool where to lay the mined peat chunks for drying. It takes no power to work and has a gui where to place the chunks. Working time is 2400 ticks. Output can be picked from below with an hopper, any other side will supply the input.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Basic Coal Refiner:\n"
					  + "This device improves the efficiency of a coal type by pre-combustion. Only Lignite and Vanilla coal can be improved to their next coal tier. It takes no power to work but requires a torch beneath it for heating and has a gui to handle the items.\n"))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"NOTES\n"
				      + "- Coal should not spawn under the ocean."
		      		  + "- The vanilla charcoal is craftable from charcoal lumps in case other recipes require this item."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"If under the Refiner there is an extracting hopper, the torch can be placed right under the hopper.\n"))));

			
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"IRON TIERS\n"
					  + "Iron tiers replace the vanilla iron ore with 8 tiers of ores depending on occurrency and quantity of smeltable iron. They can smelt into ingots, pellets or lumps."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Magnetite:\n"
					  + "It can be found in deeper levels (20/42) in any biome excluded hot biomes. It is an high bearing iron ore. It smelts into 3 pellets."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Hematite:\n"
					  + "It can be found in deeper levels (14/34) only in hot biomes. It is an high bearing iron ore. It smelts into 3 pellets."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Goethite:\n"
					  + "It can be found in mid-low levels (40/55) in any biome excluded the sandy biomes. It is equal to a vanilla ore. It smelts into 1 ingot."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Limonite:\n"
					  + "It can be found around the surface (50/70) in sandy biomes only. It is equal to a vanilla ore. It smelts into 1 ingot."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Siderite:\n"
					  + "It can be found around the surface (55/70) in any biome excluded wet biomes. It is a moderate bearing iron ore. It smelts into 5 lumps."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Bog Iron:\n"
					  + "It can be found in the dirt around the surface (55/70) in swamp biomes only. It is a reasonaly low bearing iron ore. It smelts into 4 lumps."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Taconite:\n"
					  + "It can be found in levels above the surface (70/90) and rarely at higher heights (100/200) in any biome. It is a low bearing iron ore. It smelts into 3 lumps."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"Banded iron formation:\n"
					  + "It can be found at high levels (80/150) in hills and mountains only. It is a very low bearing iron ore. It smelts into 2 lumps."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
						"NOTES\n"
					  + "- Crafting ingots from pellets gives a little profit, saving one nugget per craft."
					  + "- Limonite and Goethite can be converted into the vanilla iron ore in case this ore is required by other recipes."))));
			bookPages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString( 
					    "- Iron Lumps may equal to nuggets though intentionally not compatible.\n"))));

			// Add detals
			itemStackIn.setTagInfo("pages", bookPages);
			itemStackIn.setTagInfo("author", new NBTTagString("GlobbyPotato"));
			itemStackIn.setTagInfo("title", new NBTTagString("Ore Tiers Book"));
		
        playerIn.openBook(itemStackIn, hand);
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){

    	if (stack.hasTagCompound()){
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            String a = nbttagcompound.getString("author");

            if (!StringUtils.isNullOrEmpty(a)){
                tooltip.add(TextFormatting.GRAY + "by GlobbyPotato");
            }
            tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("book.generation." + nbttagcompound.getInteger("generation")));
        }else{
            stack.setTagCompound(new NBTTagCompound());
            stack.setTagInfo("author", new NBTTagString("GlobbyPotato"));
        }
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack){
        return true;
    }

}