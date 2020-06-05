package com.kc.asne.planetsapi.base;

import com.kc.asne.planetsapi.register.IAsnePlanetRegistryItem;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public abstract class AsnePlanetModDimension extends ModDimension {

    @Override
    public abstract BiFunction<World, DimensionType, ? extends Dimension> getFactory();
}
