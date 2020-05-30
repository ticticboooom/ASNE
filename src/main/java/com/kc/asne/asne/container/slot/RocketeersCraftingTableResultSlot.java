package com.kc.asne.asne.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.List;
import java.util.function.Function;

public class RocketeersCraftingTableResultSlot extends Slot {

    private final IInventory inventoryIn;
    private final List<Slot> craftingSlots;

    public RocketeersCraftingTableResultSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, List<Slot> craftingSlots) {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventoryIn = inventoryIn;
        this.craftingSlots = craftingSlots;

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack getStack() {
        return super.getStack();
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        List<Slot> items = craftingSlots;
        for (int i = 0; i < 25; i++) {
            if (items.get(i).getStack().getCount() == 1) {
                items.get(i).putStack(new ItemStack(Items.AIR));
            } else if (items.get(i).getStack().getCount() > 1) {
                items.get(i).getStack().setCount(items.get(i).getStack().getCount() - 1);
            }
        }
        this.onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }
}
