package com.kc.asne.base.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class BasicSlot extends Slot {
    public BasicSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
}
