package com.kc.asne.planetsapi.base;

import com.google.common.collect.ImmutableSet;
import com.kc.asne.planetsapi.init.BiomeInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Random;
import java.util.Set;

public abstract class AsnePlanetBiomeProvider extends BiomeProvider {

    protected AsnePlanetBiomeProvider(Set<Biome> biomesIn) {
        super(biomesIn);
    }
}
