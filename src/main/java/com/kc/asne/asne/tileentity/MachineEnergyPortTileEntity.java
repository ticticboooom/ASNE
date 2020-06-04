package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.block.MachineEnergyPortBlock;
import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.capability.AsneEnergyStorage;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
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
            boolean out = false;
            if (te instanceof AsneMachineTileEntity) {
                controllerEnergyStorageHandler = ((AsneMachineTileEntity) te).getAvailableCapability(CapabilityEnergy.ENERGY);
                out = ((AsneMachineTileEntity) te).getEnergyOutput();
            }
            if (out) {

                BlockPos up = new BlockPos(0, 1, 0).add(this.pos);
                BlockPos down = new BlockPos(0, -1, 0).add(this.pos);
                BlockPos north = new BlockPos(0, 0, 1).add(this.pos);
                BlockPos south = new BlockPos(0, 0, -1).add(this.pos);
                BlockPos east = new BlockPos(1, 0, 0).add(this.pos);
                BlockPos west = new BlockPos(-1, 0, 0).add(this.pos);

                BlockPos[] positions = new BlockPos[]{up, down, north, east, south, west};
                for (final BlockPos pos : positions) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile == null || tile instanceof MachineEnergyPortTileEntity) {
                        continue;
                    }

                    Vec3i facingVector = pos.subtract(new Vec3i(this.pos.getX(), this.pos.getY(), this.pos.getZ()));
                    Direction direction = Direction.getFacingFromVector(facingVector.getX(), facingVector.getY(), facingVector.getZ()).getOpposite();
                    AsneEnergyStorage energyStorage = (AsneEnergyStorage) this.controllerEnergyStorageHandler.orElse(null);
                    IEnergyStorage handler = tile.getCapability(CapabilityEnergy.ENERGY, direction).orElse(null);
                    if (handler != null && energyStorage != null) {
                        Asne.LOGGER.debug("#1");
                        int toOffer = energyStorage.extractEnergy(100, false);
                        Asne.LOGGER.debug("toOffer: " + toOffer);

                        int accepted = handler.receiveEnergy(toOffer, false);
                        Asne.LOGGER.debug("accepted:" + accepted);
                        int remainder = toOffer - accepted;
                        Asne.LOGGER.debug("remainder:" + remainder);

                        if (remainder > 0) {
                            energyStorage.internalReceiveEnergy(remainder);
                        }
                        Asne.LOGGER.debug("#final");

                    }
                }
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
