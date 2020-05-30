package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.tileentity.RocketeersCraftingTableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Asne.MOD_ID);

    public static final RegistryObject<TileEntityType<RocketeersCraftingTableTileEntity>> ROCKETEERS_CRAFTING_TABLE = TILE_ENTITIES.register("rocketeers_crafting_table", () -> TileEntityType.Builder.create(RocketeersCraftingTableTileEntity::new, BlockTypes.ROCKETEERS_CRAFTING_TABLE.get()).build(null));
}
