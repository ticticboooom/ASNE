package com.kc.asne.asne.container.slot.steamgenerator;

import com.kc.asne.asne.tileentity.SteamGeneratorTileEntity;
import com.kc.asne.base.container.slot.FuelProgressSlot;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;

public class SteamGeneratorFuelProgressSlot extends FuelProgressSlot {
    private final SteamGeneratorTileEntity inventory;

    public SteamGeneratorFuelProgressSlot(SteamGeneratorTileEntity inventory, int xPosition, int yPosition) {
        super(inventory, xPosition, yPosition);
        this.inventory = inventory;
    }

    @Override
    public float getNormalisedPercentage() {
        return this.inventory.getFuelBurnPercentage();
    }
}
