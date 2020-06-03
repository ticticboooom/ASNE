package com.kc.asne.asne.capability.fluid;

import com.kc.asne.base.capability.AsneFluidTank;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;

public class SteamGeneratorWaterTank extends AsneFluidTank {
    public SteamGeneratorWaterTank() {
        super(10000);
    }
}
