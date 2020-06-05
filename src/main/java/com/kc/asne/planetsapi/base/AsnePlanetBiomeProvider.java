package com.kc.asne.planetsapi.base;

import com.kc.asne.planetsapi.register.IAsnePlanetRegistryItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Set;

public abstract class AsnePlanetBiomeProvider extends BiomeProvider {

    protected AsnePlanetBiomeProvider(Set<Biome> biomesIn) {
        super(biomesIn);
    }
}
