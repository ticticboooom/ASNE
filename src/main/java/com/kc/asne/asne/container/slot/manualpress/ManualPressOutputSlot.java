package com.kc.asne.asne.container.slot.manualpress;

import com.kc.asne.base.container.slot.BasicSlot;
import com.kc.asne.base.container.slot.LargeOutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ManualPressOutputSlot extends LargeOutputSlot {
    private final PlayerEntity player;
    private int removeCount;

    public ManualPressOutputSlot(PlayerEntity player, IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
