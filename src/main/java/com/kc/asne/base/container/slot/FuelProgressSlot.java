package com.kc.asne.base.container.slot;

import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import com.kc.asne.base.tileentity.AsneTileEntity;
import net.minecraft.inventory.container.Container;

import java.awt.*;

public class FuelProgressSlot implements IBasicProgressSlot {
    private final AsneMachineTileEntity inventory;
    private final int xPosition;
    private final int yPosition;

    public FuelProgressSlot(AsneMachineTileEntity inventory, int xPosition, int yPosition) {

        this.inventory = inventory;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    @Override
    public float getNormalisedPercentage() {
        return 0;
    }

    @Override
    public int getXPosition() {
        return this.xPosition;
    }

    @Override
    public int getYPosition() {
        return this.yPosition;
    }
}
