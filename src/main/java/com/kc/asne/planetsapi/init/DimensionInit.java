package com.kc.asne.planetsapi.init;

import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, AsneConstants.MOD_ID);


}
