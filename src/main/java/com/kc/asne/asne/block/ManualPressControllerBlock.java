package com.kc.asne.asne.block;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.block.AsneMultiBlockControllerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;


public class ManualPressControllerBlock extends AsneMultiBlockControllerBlock {
    public ManualPressControllerBlock() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f, 6f));
    }

    @Override
    protected TileEntityType<?> getTileEntityType() {
        return TileEntityTypes.MANUAL_PRESS.get();
    }
}