package com.globbypotato.rockhounding_oretiers;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_core.blocks.itemblocks.PoweredIB;
import com.globbypotato.rockhounding_core.handlers.RegistryHandler;
import com.globbypotato.rockhounding_oretiers.blocks.CoalBlocks;
import com.globbypotato.rockhounding_oretiers.blocks.CoalOres;
import com.globbypotato.rockhounding_oretiers.blocks.IronOres;
import com.globbypotato.rockhounding_oretiers.blocks.SeamFire;
import com.globbypotato.rockhounding_oretiers.blocks.itemblocks.SeamFireIB;
import com.globbypotato.rockhounding_oretiers.blocks.itemblocks.TierMetaIB;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalBlocks;
import com.globbypotato.rockhounding_oretiers.enums.EnumCoalOres;
import com.globbypotato.rockhounding_oretiers.enums.EnumIronOres;
import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.Bloomery;
import com.globbypotato.rockhounding_oretiers.machines.CoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.PeatDrier;
import com.globbypotato.rockhounding_oretiers.utils.ToolUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	public static final Block COAL_ORES = new CoalOres("coal_ores");
	public static final Block COAL_BLOCKS = new CoalBlocks("coal_blocks");
	public static final Block IRON_ORES = new IronOres("iron_ores");
	public static final Block SEAM_FIRE = new SeamFire("seam_fire");

	public static final Block PEAT_DRIER = new PeatDrier("peat_drier");
	public static final Block COAL_REFINER = new CoalRefiner("coal_refiner");
	public static final Block BLOOMERY = new Bloomery("bloomery");

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		// register the block block
		@SubscribeEvent
		public static void registerBlock(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.register(COAL_ORES);
			registry.register(COAL_BLOCKS);
			if(ToolUtils.registerAsOres()){
				registry.register(IRON_ORES);
			}
			registry.register(SEAM_FIRE);
			registry.register(PEAT_DRIER);
			registry.register(COAL_REFINER);
			registry.register(BLOOMERY);
		}

		// register the itemblock
		@SubscribeEvent
		public static void registerItemBlock(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registry.register(new BaseMetaIB(COAL_ORES, EnumCoalOres.getNames()).setRegistryName(COAL_ORES.getRegistryName()));
			registry.register(new TierMetaIB(COAL_BLOCKS, EnumCoalBlocks.getNames()).setRegistryName(COAL_BLOCKS.getRegistryName()));
			if(ToolUtils.registerAsOres()){
				registry.register(new BaseMetaIB(IRON_ORES, EnumIronOres.getNames()).setRegistryName(IRON_ORES.getRegistryName()));
			}
			registry.register(new SeamFireIB(SEAM_FIRE).setRegistryName(SEAM_FIRE.getRegistryName()));
			registry.register(new ItemBlock(PEAT_DRIER).setRegistryName(PEAT_DRIER.getRegistryName()));
			registry.register(new ItemBlock(COAL_REFINER).setRegistryName(COAL_REFINER.getRegistryName()));
			registry.register(new PoweredIB(BLOOMERY).setRegistryName(BLOOMERY.getRegistryName()));
		}

		// register the item model
		/**
		 * @param event  
		 */
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event){
			RegistryHandler.registerMetaModel(COAL_ORES, EnumCoalOres.getNames());
			RegistryHandler.registerMetaModel(COAL_BLOCKS, EnumCoalBlocks.getNames());
			if(ToolUtils.registerAsOres()){
				RegistryHandler.registerMetaModel(IRON_ORES, EnumIronOres.getNames());
			}
			RegistryHandler.registerSingleModel(SEAM_FIRE);
			RegistryHandler.registerSingleModel(PEAT_DRIER);
			RegistryHandler.registerSingleModel(COAL_REFINER);
			RegistryHandler.registerSingleModel(BLOOMERY);
		}
	}


}