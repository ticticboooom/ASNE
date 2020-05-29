package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.container.RubyBlockContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Asne.MOD_ID);

    public static final RegistryObject<ContainerType<RubyBlockContainer>> RUBY_BLOCK_CONTAINER = CONTAINER_TYPES.register("ruby_block", () -> IForgeContainerType.create(RubyBlockContainer::new));
}
