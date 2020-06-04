package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class MachineItemPortTileEntity extends MachineStructureTileEntity implements ITickableTileEntity {

    public MachineItemPortTileEntity() {
        super(TileEntityTypes.MACHINE_ITEM_PORT.get());
    }

    public LazyOptional<IItemHandler> controllerItemStorageHandler = LazyOptional.empty();
    @Override
    public void tick() {
        if (this.controllerPos != null) {
            TileEntity te = world.getTileEntity(this.controllerPos);
            if (te instanceof AsneMachineTileEntity) {
                controllerItemStorageHandler = ((AsneMachineTileEntity)te).getAvailableCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return controllerItemStorageHandler.cast();
        }
        return super.getCapability(cap, side);
    }
}
