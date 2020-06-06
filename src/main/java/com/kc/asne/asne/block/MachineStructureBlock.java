package com.kc.asne.asne.block;


import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.MachineStructureTileEntity;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MachineStructureBlock extends Block {
    public MachineStructureBlock() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f, 6f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.MACHINE_STRUCTURE.get().create();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity te = worldIn.getTileEntity(pos);
            BlockPos controllerPos = ((MachineStructureTileEntity) te).controllerPos;
            if (controllerPos != null) {
                TileEntity te1 = worldIn.getTileEntity(controllerPos);
                if (te1 instanceof AsneMultiBlockMachineTileEntity) {
                    ((AsneMultiBlockMachineTileEntity) te1).canConstruct = false;
                    ((AsneMultiBlockMachineTileEntity) te1).isConstructed = false;

                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
