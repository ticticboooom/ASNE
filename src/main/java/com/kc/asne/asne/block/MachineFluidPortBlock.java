package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.MachineEnergyPortTileEntity;
import com.kc.asne.asne.tileentity.MachineFluidPortTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class MachineFluidPortBlock extends MachineStructureBlock {
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.MACHINE_FLUID_PORT.get().create();
    }
}
