package com.kc.asne.asne.item;

import com.kc.asne.asne.block.ManualPressControllerBlock;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ItemGroupTypes;
import com.kc.asne.asne.multiblock.MultiBlockHelper;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.asne.util.parser.*;
import com.kc.asne.base.block.AsneMultiBlockControllerBlock;
import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;

public class RocketeersHammer extends Item {
    public RocketeersHammer() {
        super(new Item.Properties().group(ItemGroupTypes.ITEMS_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        BlockPos pos = MultiBlockHelper.rayTraceFromPlayerEye(playerIn, worldIn);
        BlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        if (block.getRegistryName().toString().equals("minecraft:iron_block")) {
            worldIn.removeBlock(pos, false);
            worldIn.setBlockState(pos, BlockTypes.ROCKETEERS_CRAFTING_TABLE.get().getDefaultState());
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1.0f, worldIn.rand.nextFloat());
        }
        if (block instanceof AsneMultiBlockControllerBlock) {
            MultiBlockHelper.scanAndFlagMultiBlock(pos, playerIn, worldIn, block);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
