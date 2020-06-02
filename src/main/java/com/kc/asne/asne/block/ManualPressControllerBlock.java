package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.block.AsneBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


public class ManualPressControllerBlock extends AsneBlock {
    public ManualPressControllerBlock() {
        super(Block.Properties.create(Material.IRON));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.MANUAL_PRESS.get().create();
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof ManualPressTileEntity) {
                ManualPressTileEntity manualPressTileEntity = (ManualPressTileEntity)tile;
                if (manualPressTileEntity.isConstructed) {
                    NetworkHooks.openGui((ServerPlayerEntity)player, manualPressTileEntity, buf -> buf.writeBlockPos(pos));
                    return ActionResultType.SUCCESS;
                }
            }


        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ManualPressTileEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((ManualPressTileEntity)te).getItems());
            }
        }
    }
}