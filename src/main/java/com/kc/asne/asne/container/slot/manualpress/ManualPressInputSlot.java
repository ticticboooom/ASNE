package com.kc.asne.asne.container.slot.manualpress;

import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.container.slot.BasicSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ManualPressInputSlot extends BasicSlot {
    private final ManualPressTileEntity inventoryIn;

    public ManualPressInputSlot(ManualPressTileEntity inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventoryIn = inventoryIn;
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        inventoryIn.resetProcessingProgress();
        return super.onTake(thePlayer, stack);
    }
}
