package com.kc.asne.asne.init;

import com.kc.asne.asne.tileentity.*;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, AsneConstants.MOD_ID);

    public static final RegistryObject<TileEntityType<RocketeersCraftingTableTileEntity>> ROCKETEERS_CRAFTING_TABLE = TILE_ENTITIES.register("rocketeers_crafting_table", () -> TileEntityType.Builder.create(RocketeersCraftingTableTileEntity::new, BlockTypes.ROCKETEERS_CRAFTING_TABLE.get()).build(null));
    public static final RegistryObject<TileEntityType<ManualPressTileEntity>> MANUAL_PRESS = TILE_ENTITIES.register("manual_press", () -> TileEntityType.Builder.create(ManualPressTileEntity::new, BlockTypes.MANUAL_PRESS_CONTROLLER.get()).build(null));
    public static final RegistryObject<TileEntityType<MachineStructureTileEntity>> MACHINE_STRUCTURE = TILE_ENTITIES.register("machine_structure", () -> TileEntityType.Builder.create(MachineStructureTileEntity::new, BlockTypes.MACHINE_STRUCTURE.get()).build(null));
    public static final RegistryObject<TileEntityType<SteamGeneratorTileEntity>> STEAM_GENERATOR = TILE_ENTITIES.register("steam_generator", () -> TileEntityType.Builder.create(SteamGeneratorTileEntity::new, BlockTypes.STEAM_GENERATOR_CONTROLLER.get()).build(null));
    public static final RegistryObject<TileEntityType<MachineEnergyPortTileEntity>> MACHINE_ENERGY_PORT = TILE_ENTITIES.register("machine_energy_port", () -> TileEntityType.Builder.create(MachineEnergyPortTileEntity::new, BlockTypes.MACHINE_ENERGY_PORT.get()).build(null));
    public static final RegistryObject<TileEntityType<MachineFluidPortTileEntity>> MACHINE_FLUID_PORT = TILE_ENTITIES.register("machine_fluid_port", () -> TileEntityType.Builder.create(MachineFluidPortTileEntity::new, BlockTypes.MACHINE_FLUID_PORT.get()).build(null));
    public static final RegistryObject<TileEntityType<MachineItemPortTileEntity>> MACHINE_ITEM_PORT = TILE_ENTITIES.register("machine_item_port", () -> TileEntityType.Builder.create(MachineItemPortTileEntity::new, BlockTypes.MACHINE_ITEM_PORT.get()).build(null));
}
