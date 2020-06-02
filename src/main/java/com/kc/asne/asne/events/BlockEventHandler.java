package com.kc.asne.asne.events;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.tileentity.MachineStructureTileEntity;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Asne.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockEventHandler {
    @SubscribeEvent
    public static void onBreakEvent(BlockEvent.BreakEvent event) {
        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if (te != null) {
            if (te instanceof MachineStructureTileEntity) {
                BlockPos cpos = ((MachineStructureTileEntity)te).controllerPos;
                TileEntity tile = event.getWorld().getTileEntity(cpos);
                if (tile instanceof AsneMultiBlockMachineTileEntity) {
                    ((AsneMultiBlockMachineTileEntity)tile).canConstruct = false;
                }

            }
        }

    }
}
