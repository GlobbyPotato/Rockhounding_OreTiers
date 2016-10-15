package com.globbypotato.rockhounding_oretiers.handlers;

import com.globbypotato.rockhounding_oretiers.ModContents;
import com.globbypotato.rockhounding_oretiers.machines.container.*;
import com.globbypotato.rockhounding_oretiers.machines.gui.*;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID) {
			default: return null;
			case ModContents.peatDrierID:
				if (entity != null && entity instanceof TileEntityPeatDrier){return new ContainerPeatDrier(player.inventory, (TileEntityPeatDrier) entity);}
			case ModContents.coalRefinerID:
				if (entity != null && entity instanceof TileEntityCoalRefiner){return new ContainerCoalRefiner(player.inventory, (TileEntityCoalRefiner) entity);}
		}
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID) {
			default: return null;
			case ModContents.peatDrierID:
				if (entity != null && entity instanceof TileEntityPeatDrier) {return new GuiPeatDrier(player.inventory, (TileEntityPeatDrier) entity);}
			case ModContents.coalRefinerID:
				if (entity != null && entity instanceof TileEntityCoalRefiner) {return new GuiCoalRefiner(player.inventory, (TileEntityCoalRefiner) entity);}
		}
        return null;
	}

}
