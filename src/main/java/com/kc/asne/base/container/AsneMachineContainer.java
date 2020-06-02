package com.kc.asne.base.container;

import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.container.slot.IBasicProgressSlot;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import com.kc.asne.base.tileentity.AsneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.ArrayList;
import java.util.Objects;

public class AsneMachineContainer<CONTAINER extends AsneMachineTileEntity> extends Container {
    public final AsneMachineTileEntity tileEntity;
    public ArrayList<IBasicProgressSlot> progressSlots = new ArrayList<>();

    private final IWorldPosCallable canInteractWithCallable;
    private final Block block;

    protected AsneMachineContainer(final int windowId, final PlayerInventory playerInventory, final AsneMachineTileEntity tileEntity, ContainerType<?> containerType, Block block) {
        super(containerType, windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        this.block = block;

        int slotSizePlus2 = 18;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, this.getPlayerInventoryStartX() + (column * slotSizePlus2), this.getPlayerInventoryStartY() + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hatBarY = this.getPlayerInventoryStartY() + 58;
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, this.getPlayerInventoryStartX() + (column * slotSizePlus2), hatBarY));
        }
    }


    protected int getPlayerInventoryStartY() {
        return 140;
    }

    protected int getPlayerInventoryStartX(){
        return 8;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, block);
    }



    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if (index < this.tileEntity.getSizeInventory()) {
                if (!this.mergeItemStack(itemStack1, this.tileEntity.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, this.tileEntity.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
