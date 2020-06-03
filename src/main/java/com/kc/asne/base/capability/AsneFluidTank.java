package com.kc.asne.base.capability;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;

public abstract class AsneFluidTank implements IFluidHandler, IFluidTank {
    private int capacity;
    private FluidStack fluid = FluidStack.EMPTY;


    public AsneFluidTank(int capacity) {

        this.capacity = capacity;
    }

    public IFluidTank setCapacity(int capacity)
    {
        this.capacity = capacity;
        return this;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return true;
    }

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return fluid;
    }

    @Override
    public int getFluidAmount() {
        return fluid.getAmount();
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return getFluid();
    }

    @Override
    public int getTankCapacity(int tank) {
        return getCapacity();
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return isFluidValid(stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !isFluidValid(resource))
        {
            return 0;
        }
        if (action.simulate())
        {
            if (fluid.isEmpty())
            {
                return Math.min(capacity, resource.getAmount());
            }
            if (!fluid.isFluidEqual(resource))
            {
                return 0;
            }
            return Math.min(capacity - fluid.getAmount(), resource.getAmount());
        }
        if (fluid.isEmpty())
        {
            fluid = new FluidStack(resource, Math.min(capacity, resource.getAmount()));
            onContentsChanged();
            return fluid.getAmount();
        }
        int filled = capacity - fluid.getAmount();

        if (resource.getAmount() < filled)
        {
            fluid.grow(resource.getAmount());
            filled = resource.getAmount();
        }
        else
        {
            fluid.setAmount(capacity);
        }
        if (filled > 0)
            onContentsChanged();
        return filled;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !resource.isFluidEqual(fluid))
        {
            return FluidStack.EMPTY;
        }
        return drain(resource.getAmount(), action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        int drained = maxDrain;
        if (fluid.getAmount() < drained)
        {
            drained = fluid.getAmount();
        }
        FluidStack stack = new FluidStack(fluid, drained);
        if (action.execute() && drained > 0)
        {
            fluid.shrink(drained);
            onContentsChanged();
        }
        return stack;
    }

    public void setFluid(FluidStack stack) {
       this.fluid = stack;
    }

    public boolean isEmpty() {
        return fluid.isEmpty();
    }

    public int getSpace() {
        return Math.min(0, capacity - fluid.getAmount());
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("fluid_capacity", capacity);
        compound.putInt("fluid_amount", fluid.getAmount());
        compound.putString("fluid_type", fluid.getFluid().getRegistryName().toString());
        return compound;
    }

    public void read(CompoundNBT compound) {
        capacity = compound.getInt("fluid_capacity");
        fluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(compound.getString("fluid_type"))), compound.getInt("fluid_amount"));
    }

    protected void onContentsChanged() {};
}
