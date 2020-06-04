package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class MachineEnergyPortBlock extends MachineStructureBlock {
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.MACHINE_ENERGY_PORT.get().create();
    }
}
