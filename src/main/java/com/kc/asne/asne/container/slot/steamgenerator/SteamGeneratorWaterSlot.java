package com.kc.asne.asne.container.slot.steamgenerator;

import com.kc.asne.asne.tileentity.SteamGeneratorTileEntity;
import com.kc.asne.base.container.slot.FluidSlot;

public class SteamGeneratorWaterSlot extends FluidSlot {
    private final SteamGeneratorTileEntity inventory;

    public SteamGeneratorWaterSlot(SteamGeneratorTileEntity inventory, int xPosition, int yPosition) {
        super(inventory, xPosition, yPosition);
        this.inventory = inventory;
    }

    @Override
    public float getNormalisedPercentage() {
        return inventory.getWaterContentsPercentage();
    }
}
