package com.kc.asne.asne.multiblock;

import com.kc.asne.asne.block.ManualPressControllerBlock;
import com.kc.asne.asne.tileentity.MachineStructureTileEntity;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.asne.util.parser.*;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import com.kc.asne.base.tileentity.AsneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MultiBlockHelper {
    public static BlockPos rayTraceFromPlayerEye(PlayerEntity playerIn, World worldIn) {
        Vec3d forward = playerIn.getForward();
        Vec3d playerPos = playerIn.getEyePosition(0);
        RayTraceContext rayTraceContext = new RayTraceContext(playerPos, new Vec3d(playerPos.x + forward.x * 6, playerPos.y + forward.y * 6, playerPos.z + forward.z * 6), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, playerIn);
        BlockRayTraceResult result = worldIn.rayTraceBlocks(rayTraceContext);
        return result.getPos();
    }

    public static void scanAndFlagMultiBlock(BlockPos pos, PlayerEntity playerIn, World worldIn, Block block) {
        boolean canCreate = scanForMultiBlock(pos, playerIn, worldIn, block);
        if (canCreate) {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1.0f, worldIn.rand.nextFloat());
            TileEntity tile = worldIn.getTileEntity(pos);
            AsneMultiBlockMachineTileEntity tileEntity = (AsneMultiBlockMachineTileEntity) tile;
            if (tileEntity != null) {
                tileEntity.canConstruct = true;
            }
        }
    }

    public static boolean scanForMultiBlock(BlockPos pos, PlayerEntity playerIn, World worldIn, Block block) {
        boolean canCreate = true;
        ArrayList<MachineStructureTileEntity> machineStructures = new ArrayList<>();
        MultiBlock mb = CustomParser.multiBlocks.get(block.getRegistryName().toString());
        for (int formationIndex = 0; formationIndex < mb.formations.size(); formationIndex++) {
            canCreate = true;
            MultiBlocksFormation formation = mb.formations.get(formationIndex);
            blockLoop:
            for (int blockIndex = 0; blockIndex < formation.blocks.size(); blockIndex++) {
                MultiBlockFormationBlock formationBlock = formation.blocks.get(blockIndex);
                for (int positionIndex = 0; positionIndex < formationBlock.positions.size(); positionIndex++) {
                    Position position = formationBlock.positions.get(positionIndex);
                    BlockPos blockPos = pos.add(new Vec3i(position.x, position.y, position.z));
                    BlockState currentBlockState = worldIn.getBlockState(blockPos);
                    String regName = currentBlockState.getBlock().getRegistryName().toString();
                    TileEntity tile = worldIn.getTileEntity(blockPos);
                    if (tile instanceof  MachineStructureTileEntity){
                        machineStructures.add((MachineStructureTileEntity) tile);
                    }
                    if (!regName.equals(formationBlock.type)) {
                        canCreate = false;
                        machineStructures.clear();
                        break blockLoop;
                    }
                }
            }
        }
        if (canCreate){
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof AsneMultiBlockMachineTileEntity){
                ((AsneMultiBlockMachineTileEntity) te).canConstruct = true;
                ((AsneMultiBlockMachineTileEntity) te).isConstructed = true;
            }
            machineStructures.forEach(m -> {
                m.controllerPos = pos;
            });
        }
        return canCreate;
    }
}