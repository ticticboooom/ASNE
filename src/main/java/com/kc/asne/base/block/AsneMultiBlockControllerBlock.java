package com.kc.asne.base.block;

import com.kc.asne.asne.multiblock.MultiBlockHelper;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AsneMultiBlockControllerBlock extends Block {
    public AsneMultiBlockControllerBlock(Properties properties) {
        super(properties);
    }

    protected boolean getCanOpenGui(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, TileEntity tile) {
        boolean isConstructed = MultiBlockHelper.scanForMultiBlock(pos, player, worldIn, state.getBlock());
        AsneMultiBlockMachineTileEntity tileEntity = (ManualPressTileEntity)tile;
        if (tileEntity.canConstruct) {
            tileEntity.isConstructed = isConstructed;
        } else {
            tileEntity.isConstructed = false;
        }
        return tileEntity.isConstructed;
    }
}
