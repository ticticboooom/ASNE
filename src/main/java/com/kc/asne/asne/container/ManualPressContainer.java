package com.kc.asne.asne.container;

import com.kc.asne.asne.container.slot.manualpress.*;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ContainerTypes;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.container.AsneMachineContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class ManualPressContainer extends AsneMachineContainer<ManualPressTileEntity> {
    public ManualPressOutputSlot outputSlot;
    public ManualPressInputSlot inputSlot;
    public ManualPressFuelInputSlot fuelInputSlot;

    public ManualPressContainer(final int windowId, final PlayerInventory playerInventory, final ManualPressTileEntity tileEntity) {
        super(windowId, playerInventory, tileEntity, ContainerTypes.MANUAL_PRESS.get(), BlockTypes.MANUAL_PRESS_CONTROLLER.get());
        inputSlot = new ManualPressInputSlot(tileEntity, 0, 50, 18);
        this.addSlot(inputSlot);
        outputSlot = new ManualPressOutputSlot(playerInventory.player, tileEntity, 2, 117, 35);
        this.addSlot(outputSlot);
        fuelInputSlot = new ManualPressFuelInputSlot(tileEntity, 3, 50, 52);
        this.addSlot(fuelInputSlot);

        this.progressSlots.add(new ManualPressProcessProgressSlot(tileEntity, 79, 34));
        this.progressSlots.add(new ManualPressFuelSlot(tileEntity, 51, 36));
    }

    @Override
    protected int getPlayerInventoryStartY() {
        return 84;
    }

    public ManualPressContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (ManualPressTileEntity) playerInventory.player.world.getTileEntity(data.readBlockPos()));
    }


}
