package com.kc.asne.planetsapi.base;

import net.minecraft.world.biome.Biome;

public abstract class AsnePlanetBiome extends Biome {
    protected AsnePlanetBiome(Builder biomeBuilder) {
        super(biomeBuilder);
    }
}
