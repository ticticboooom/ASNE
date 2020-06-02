package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.base.tileentity.AsneTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.system.CallbackI;

public class MachineStructureTileEntity extends AsneTileEntity {
    public BlockPos controllerPos = null;
    public MachineStructureTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MachineStructureTileEntity() {
        this(TileEntityTypes.MACHINE_STRUCTURE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (controllerPos != null) {
            compound.putInt("anse_controller_pos_x", controllerPos.getX());
            compound.putInt("anse_controller_pos_y", controllerPos.getY());
            compound.putInt("anse_controller_pos_z", controllerPos.getZ());
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        controllerPos = new BlockPos(compound.getInt("anse_controller_pos_x"), compound.getInt("anse_controller_pos_y"), compound.getInt("anse_controller_pos_z"));

        super.read(compound);
    }
}
