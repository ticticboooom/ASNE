package com.kc.asne.asne.container;

import com.kc.asne.asne.container.slot.manualpress.ManualPressFuelSlot;
import com.kc.asne.asne.container.slot.manualpress.ManualPressInputSlot;
import com.kc.asne.asne.container.slot.steamgenerator.SteamGeneratorFuelInputSlot;
import com.kc.asne.asne.container.slot.steamgenerator.SteamGeneratorWaterSlot;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ContainerTypes;
import com.kc.asne.asne.tileentity.SteamGeneratorTileEntity;
import com.kc.asne.base.container.AsneMachineContainer;
import com.kc.asne.base.container.slot.BasicSlot;
import com.kc.asne.base.container.slot.FluidSlot;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class SteamGeneratorContainer extends AsneMachineContainer<SteamGeneratorTileEntity> {
    private final SteamGeneratorFuelInputSlot inputSlot;

    public SteamGeneratorContainer(int windowId, PlayerInventory playerInventory, SteamGeneratorTileEntity tileEntity) {
        super(windowId, playerInventory, tileEntity, ContainerTypes.STEAM_GENERATOR.get(), BlockTypes.STEAM_GENERATOR_CONTROLLER.get());
        inputSlot = new SteamGeneratorFuelInputSlot(tileEntity, 0, 50, 35);
        this.addSlot(inputSlot);

        this.progressSlots.add(new SteamGeneratorWaterSlot(tileEntity, 10, 17));
    }

    public SteamGeneratorContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (SteamGeneratorTileEntity) playerInventory.player.world.getTileEntity(data.readBlockPos()));
    }

    @Override
    protected int getPlayerInventoryStartY() {
        return 84;
    }
}
