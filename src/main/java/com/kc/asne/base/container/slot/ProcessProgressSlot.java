package com.kc.asne.base.container.slot;

import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.inventory.IInventory;

public class ProcessProgressSlot<TILE extends AsneMachineTileEntity> implements IBasicProgressSlot {
    private final TILE inventory;
    private final int xPosition;
    private final int yPosition;

    public ProcessProgressSlot(TILE inventory, int xPosition, int yPosition) {
        this.inventory = inventory;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public TILE getInventory() {
        return inventory;
    }


    public float getNormalisedPercentage() {
        return 0;
    }
}
