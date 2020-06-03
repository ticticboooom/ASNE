package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.asne.container.RocketeersCraftingTableContainer;
import com.kc.asne.asne.container.SteamGeneratorContainer;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, AsneConstants.MOD_ID);

    public static final RegistryObject<ContainerType<RocketeersCraftingTableContainer>> ROCKETEERS_CRAFTING_TABLE = CONTAINER_TYPES.register("rocketeers_crafting_table", () -> IForgeContainerType.create(RocketeersCraftingTableContainer::new));
    public static final RegistryObject<ContainerType<ManualPressContainer>> MANUAL_PRESS = CONTAINER_TYPES.register("manual_press", () -> IForgeContainerType.create(ManualPressContainer::new));
    public static final RegistryObject<ContainerType<SteamGeneratorContainer>> STEAM_GENERATOR = CONTAINER_TYPES.register("steam_generator", () -> IForgeContainerType.create(SteamGeneratorContainer::new));
}
