package com.kc.asne.asne.item;

import com.kc.asne.asne.block.RocketeersCraftingTableBlock;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ItemGroupTypes;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
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
        } else if (block.getRegistryName().toString().equals("asne:manual_press_controller")) {
            BlockState rightStone = worldIn.getBlockState(result.getPos().add(new Vec3i(1, 0, 0)));
            BlockState leftStone = worldIn.getBlockState(result.getPos().add(new Vec3i(-1, 0, 0)));
            BlockState rightMidStone = worldIn.getBlockState(result.getPos().add(new Vec3i(1, 1, 0)));
            BlockState leftMidStone = worldIn.getBlockState(result.getPos().add(new Vec3i(-1, 1, 0)));
            BlockState rightTopStone = worldIn.getBlockState(result.getPos().add(new Vec3i(1, 2, 0)));
            BlockState leftTopStone = worldIn.getBlockState(result.getPos().add(new Vec3i(-1, 2, 0)));
            BlockState topPiston = worldIn.getBlockState(result.getPos().add(new Vec3i(0, 2, 0)));
            if (getRegistryName(rightStone).equals("minecraft:stone") &&
                    getRegistryName(leftStone).equals("minecraft:stone") &&
                    getRegistryName(topPiston).equals("minecraft:piston") &&
                    getRegistryName(rightMidStone).equals("minecraft:stone") &&
                    getRegistryName(leftMidStone).equals("minecraft:stone") &&
                    getRegistryName(leftTopStone).equals("minecraft:stone") &&
                    getRegistryName(rightTopStone).equals("minecraft:stone")){
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1.0f, worldIn.rand.nextFloat());
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private String getRegistryName(BlockState state) {
        return state.getBlock().getRegistryName().toString();
    }

}
