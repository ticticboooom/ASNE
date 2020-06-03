package com.kc.asne.base.block;

import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AsneMultiBlockControllerBlock extends DirectionalBlock {

    public AsneMultiBlockControllerBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    protected TileEntityType<?> getTileEntityType() {
        return null;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEntityType<?> teType = getTileEntityType();
        if (teType != null){
            return teType.create();
        }
        return null;
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof AsneMultiBlockMachineTileEntity) {
                if (((AsneMultiBlockMachineTileEntity)tile).isConstructed) {
                    NetworkHooks.openGui((ServerPlayerEntity)player, (AsneMultiBlockMachineTileEntity)tile, buf -> buf.writeBlockPos(pos));
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
            if (te instanceof AsneMultiBlockMachineTileEntity) {
                AsneMultiBlockMachineTileEntity asneMultiBlockMachineTileEntity = (AsneMultiBlockMachineTileEntity)te;
                InventoryHelper.dropItems(worldIn, pos, asneMultiBlockMachineTileEntity.getItems());
                worldIn.removeTileEntity(pos);
            }
        }
    }
}
