package com.globbypotato.rockhounding_oretiers.compat.top;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_oretiers.handlers.Reference;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityBloomery;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityCoalRefiner;
import com.globbypotato.rockhounding_oretiers.machines.tileentity.TileEntityPeatDrier;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeConfig;
import mcjty.theoneprobe.api.IProbeConfigProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.NumberFormat;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TopBloomery implements IProbeInfoProvider{

	@Override
	public String getID() {
        return Reference.MODID + ":" + "bloomery";
	}

	public static class EnergyInfo implements IProbeConfigProvider{
		@Override
		public void getProbeConfig(IProbeConfig config, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data) {
			//
		}

		@Override
		public void getProbeConfig(IProbeConfig config, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
	        final TileEntity te = world.getTileEntity(data.getPos());
			if(te instanceof TileEntityBloomery || te instanceof TileEntityCoalRefiner || te instanceof TileEntityPeatDrier
			){
				config.setRFMode(0);
			}			
		}
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        final TileEntity te = world.getTileEntity(data.getPos());
		if(te != null){
			if(te instanceof TileEntityBloomery){
				TileEntityBloomery tank = (TileEntityBloomery)te;
				if(tank.getPower() > 0){
					probeInfo.progress(tank.getPower(), tank.getPowerMax(), probeInfo.defaultProgressStyle().suffix(" ticks").filledColor(0xFFFFB400).alternateFilledColor(0xFFFF7200).borderColor(0x000000).numberFormat(NumberFormat.FULL));
				}
				if(tank.bloomTank.getFluidAmount() > 0){
					probeInfo.progress(tank.bloomTank.getFluidAmount(), tank.bloomTank.getCapacity(), probeInfo.defaultProgressStyle()
																										.suffix(" mB")
																										.filledColor(0xFFFF7608)
																										.alternateFilledColor(0xFFFF0808)
																										.borderColor(0x000000)
																										.numberFormat(NumberFormat.FULL));
				}
			}
		}
	}

    public static class getTOP implements Function<ITheOneProbe, Void> {
        public static ITheOneProbe top;

        @Nullable
        @Override
        public Void apply (ITheOneProbe theOneProbe) {

        	top = theOneProbe;
            top.registerProvider(new TopBloomery());
            top.registerProbeConfigProvider(new EnergyInfo());
            return null;
        }
    }

}