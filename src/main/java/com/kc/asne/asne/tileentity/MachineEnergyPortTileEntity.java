package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MachineEnergyPortTileEntity extends MachineStructureTileEntity implements ITickableTileEntity {

    public MachineEnergyPortTileEntity() {
        super(TileEntityTypes.MACHINE_ENERGY_PORT.get());
    }

    public LazyOptional<IEnergyStorage> controllerEnergyStorageHandler = LazyOptional.empty();

    @Override
    public void tick() {
        if (this.controllerPos != null) {
            TileEntity te = world.getTileEntity(this.controllerPos);
            if (te instanceof AsneMachineTileEntity) {
                controllerEnergyStorageHandler = ((AsneMachineTileEntity) te).getAvailableCapability(CapabilityEnergy.ENERGY);
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return controllerEnergyStorageHandler.cast();
        }
        return super.getCapability(cap, side);
    }
}
