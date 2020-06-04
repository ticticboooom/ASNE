package com.kc.asne.base.container.slot;

import com.kc.asne.base.tileentity.AsneMachineTileEntity;

public class EnergySlot implements IBasicProgressSlot {

    protected final AsneMachineTileEntity tileEntity;
    protected final int xPosition;
    protected final int yPosition;

    public EnergySlot(AsneMachineTileEntity tileEntity, int xPosition, int yPosition) {

        this.tileEntity = tileEntity;
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
