package com.kc.asne.asne.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import java.util.function.Function;
import java.util.function.IntFunction;

public class RocketeersCraftingTableIngredientSlot extends Slot {
    private final Slot result;
    private final Function<Slot, Slot> func;

    public RocketeersCraftingTableIngredientSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, Slot result, Function<Slot, Slot> func) {
        super(inventoryIn, index, xPosition, yPosition);
        this.result = result;
        this.func = func;
    }

    @Override
    public void putStack(ItemStack stack) {
        super.putStack(stack);
        func.apply(this);
        this.result.onSlotChanged();
    }
}
