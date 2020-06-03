package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.RocketeersCraftingTableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
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
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class RocketeersCraftingTableBlock extends Block {

    public RocketeersCraftingTableBlock() {
        super(Block.Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.ROCKETEERS_CRAFTING_TABLE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof RocketeersCraftingTableTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity)player, (RocketeersCraftingTableTileEntity)tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof RocketeersCraftingTableTileEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((RocketeersCraftingTableTileEntity)te).getItems());
            }
        }
    }


}
