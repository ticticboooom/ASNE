package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class MachineFluidPortTileEntity extends MachineStructureTileEntity implements ITickableTileEntity {

    public MachineFluidPortTileEntity() {
        super(TileEntityTypes.MACHINE_FLUID_PORT.get());
    }

    public LazyOptional<IFluidHandler> controllerFluidStorageHandler = LazyOptional.empty();
    @Override
    public void tick() {
        if (this.controllerPos != null) {
            TileEntity te = world.getTileEntity(this.controllerPos);
            if (te instanceof AsneMachineTileEntity) {
                controllerFluidStorageHandler = ((AsneMachineTileEntity)te).getAvailableCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return controllerFluidStorageHandler.cast();
        }
        return super.getCapability(cap, side);
    }
}
