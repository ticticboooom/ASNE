package com.kc.asne.asne.item;

import com.kc.asne.asne.block.ManualPressControllerBlock;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ItemGroupTypes;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.asne.util.parser.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class RocketeersHammer extends Item {
    public RocketeersHammer() {
        super(new Item.Properties().group(ItemGroupTypes.ITEMS_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Vec3d forward = playerIn.getForward();
        Vec3d playerPos = playerIn.getEyePosition(0);
        RayTraceContext rayTraceContext = new RayTraceContext(playerPos, new Vec3d(playerPos.x + forward.x * 6, playerPos.y + forward.y * 6, playerPos.z + forward.z * 6), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, playerIn);
        BlockRayTraceResult result = worldIn.rayTraceBlocks(rayTraceContext);
        BlockState state = worldIn.getBlockState(result.getPos());
        Block block = state.getBlock();
        if (block.getRegistryName().toString().equals("minecraft:iron_block")) {
            worldIn.removeBlock(result.getPos(), false);
            worldIn.setBlockState(result.getPos(), BlockTypes.ROCKETEERS_CRAFTING_TABLE.get().getDefaultState());
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1.0f, worldIn.rand.nextFloat());
        }
        if (block instanceof ManualPressControllerBlock) {
            boolean canCreate = true;
            MultiBlock mb = CustomParser.multiBlocks.get(block.getRegistryName().toString());
            for (int formationIndex = 0; formationIndex < mb.formations.size(); formationIndex++) {
                canCreate = true;
                MultiBlocksFormation formation = mb.formations.get(formationIndex);
                blockLoop:
                for (int blockIndex = 0; blockIndex < formation.blocks.size(); blockIndex++) {
                    MultiBlockFormationBlock formationBlock = formation.blocks.get(blockIndex);
                    for (int positionIndex = 0; positionIndex < formationBlock.positions.size(); positionIndex++) {
                        Position position = formationBlock.positions.get(positionIndex);
                        BlockPos blockPos = result.getPos().add(new Vec3i(position.x, position.y, position.z));
                        String regName = worldIn.getBlockState(blockPos).getBlock().getRegistryName().toString();
                        if (!regName.equals(formationBlock.type)) {
                            canCreate = false;
                            break blockLoop;
                        }
                    }
                }
            }
            if (canCreate) {
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1.0f, worldIn.rand.nextFloat());
                TileEntity tile = worldIn.getTileEntity(result.getPos());
                ManualPressTileEntity manualPressTileEntity = (ManualPressTileEntity)tile;
                manualPressTileEntity.isConstructed = true;
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
