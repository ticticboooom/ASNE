package com.kc.asne.planetsapi.base;

import com.kc.asne.planetsapi.register.IAsnePlanetRegistryItem;
import net.minecraft.world.biome.Biome;

public abstract class AsnePlanetBiome extends Biome {
    protected AsnePlanetBiome(Builder biomeBuilder) {
        super(biomeBuilder);
    }
}

