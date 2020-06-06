package com.kc.asne.planetsapi.register;

import com.kc.asne.planetsapi.base.AsnePlanetBiome;
import com.kc.asne.planetsapi.base.AsnePlanetModDimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class ModPlanetsRegister {

    private final DeferredRegister<ModDimension> DIMENSIONS;
    private final DeferredRegister<Biome> BIOMES;
    private final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS;

    public ModPlanetsRegister(String modId) {
        DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, modId);
        BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, modId);
        SURFACE_BUILDERS = new DeferredRegister<>(ForgeRegistries.SURFACE_BUILDERS, modId);
    }

    public RegistryObject<ModDimension> registerPlanet(String id, Supplier<AsnePlanetModDimension> factory) {
        return DIMENSIONS.register(id, factory);
    }
    public RegistryObject<Biome> registerBiome(String id, Supplier<AsnePlanetBiome> factory) {
        return BIOMES.register(id, factory);
    }
    public <T extends SurfaceBuilderConfig> RegistryObject<SurfaceBuilder<T>> registerSurfaceBuilder(String id, Supplier<SurfaceBuilder<T>> factory) {
        return SURFACE_BUILDERS.register(id, factory);
    }

    public DeferredRegister<ModDimension> getDimensionsRegister() {
        return DIMENSIONS;
    }
    public DeferredRegister<Biome> getBiomesRegister(){
        return BIOMES;
    }
    public DeferredRegister<SurfaceBuilder<?>> getSurfaceBuilderRegister() { return SURFACE_BUILDERS; }
}
