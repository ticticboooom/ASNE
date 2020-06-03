package com.kc.asne.asne.block;


import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.asne.tileentity.SteamGeneratorTileEntity;
import com.kc.asne.base.block.AsneMultiBlockControllerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class SteamGeneratorControllerBlock extends AsneMultiBlockControllerBlock {
    public SteamGeneratorControllerBlock() {
        super(Block.Properties.create(Material.IRON));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected TileEntityType<?> getTileEntityType() {
        return TileEntityTypes.STEAM_GENERATOR.get();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
}
