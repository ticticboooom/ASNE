package com.kc.asne.asne.container.slot.manualpress;

import com.kc.asne.base.container.slot.BasicSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ManualPressFuelInputSlot extends BasicSlot {
    public ManualPressFuelInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.isItemEqual(new ItemStack(Items.COAL));
    }
}
