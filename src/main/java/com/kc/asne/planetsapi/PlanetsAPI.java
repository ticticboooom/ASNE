package com.kc.asne.planetsapi;

import com.kc.asne.planetsapi.register.ModPlanetsRegister;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;

public class PlanetsAPI {
    public static final ArrayList<ModPlanetsRegister> REGISTERS = new ArrayList<>();

    public static ModPlanetsRegister createRegister(String modId) {
        if (!ModList.get().isLoaded("asne")){
            return null;
        }
        ModPlanetsRegister register = new ModPlanetsRegister(modId);
        REGISTERS.add(register);
        return register;
    }
}
