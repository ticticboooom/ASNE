package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.MachineFluidPortTileEntity;
import com.kc.asne.asne.tileentity.MachineItemPortTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class MachineItemPortBlock extends MachineStructureBlock {
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.MACHINE_ITEM_PORT.get().create();
    }
}
