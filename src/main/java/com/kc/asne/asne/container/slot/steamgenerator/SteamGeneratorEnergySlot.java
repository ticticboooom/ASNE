package com.kc.asne.asne.container.slot.steamgenerator;

import com.kc.asne.asne.tileentity.SteamGeneratorTileEntity;
import com.kc.asne.base.container.slot.EnergySlot;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;

public class SteamGeneratorEnergySlot extends EnergySlot {

    private final SteamGeneratorTileEntity tileEntity;

    public SteamGeneratorEnergySlot(SteamGeneratorTileEntity tileEntity, int xPosition, int yPosition) {
        super(tileEntity, xPosition, yPosition);
        this.tileEntity = tileEntity;
    }

    @Override
    public float getNormalisedPercentage() {
        return this.tileEntity.getEnergyContentsPercentage();
    }
}
