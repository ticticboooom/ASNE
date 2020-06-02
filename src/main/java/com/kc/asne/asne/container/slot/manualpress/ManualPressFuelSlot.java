package com.kc.asne.asne.container.slot.manualpress;

import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.container.slot.FuelProgressSlot;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.inventory.container.Container;

public class ManualPressFuelSlot extends FuelProgressSlot {
    protected ManualPressTileEntity inventory;
    public ManualPressFuelSlot(ManualPressTileEntity inventory, int xPosition, int yPosition) {
        super(inventory, xPosition, yPosition);
        this.inventory = inventory;
    }

    @Override
    public float getNormalisedPercentage() {
        return inventory.getPercentageOfFuelRemaining();
    }
}
