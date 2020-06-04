package com.kc.asne.base.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class AsneEnergyStorage extends EnergyStorage {
    public AsneEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("capacity", this.capacity);
        compound.putInt("energy", this.energy);
        compound.putInt("maxExtract", this.maxExtract);
        compound.putInt("maxReceive", this.maxReceive);
        return compound;
    }

    public void read(CompoundNBT compound) {
        this.capacity = compound.getInt("capacity");
        this.energy = compound.getInt("energy");
        this.maxExtract = compound.getInt("maxExtract");
        this.maxReceive = compound.getInt("maxReceive");
    }
}
