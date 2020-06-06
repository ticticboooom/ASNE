package com.kc.asne.planetsapi;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.general.constants.AsneConstants;
import com.kc.asne.planetsapi.base.AsnePlanetModDimension;
import com.kc.asne.planetsapi.register.ModPlanetsRegister;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AsneConstants.MOD_ID)
public class PlanetsEventBusSubscriber {

    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        for (ModPlanetsRegister register : PlanetsAPI.REGISTERS){
            for (final RegistryObject<ModDimension> modDim : register.getDimensionsRegister().getEntries()) {
                if (DimensionType.byName(modDim.getId()) == null) {
                    DimensionManager.registerDimension(modDim.getId(), modDim.get(), null, ((AsnePlanetModDimension)modDim.get()).hasSkyLight());
                }
            }
        }
    }
}
