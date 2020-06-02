package com.kc.asne.asne.container.slot.manualpress;

import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.container.slot.ProcessProgressSlot;

public class ManualPressProcessProgressSlot extends ProcessProgressSlot<ManualPressTileEntity> {

    public ManualPressProcessProgressSlot(ManualPressTileEntity inventory, int xPosition, int yPosition) {
        super(inventory, xPosition, yPosition);
    }

    @Override
    public float getNormalisedPercentage() {
        return getInventory().getPercentageOfPressingRemaining();
    }
}
