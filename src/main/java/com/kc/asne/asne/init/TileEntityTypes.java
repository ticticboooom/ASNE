package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.tileentity.RubyBlockTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Asne.MOD_ID);

    public static final RegistryObject<TileEntityType<RubyBlockTileEntity>> RUBY_BLOCK_TILE_ENTITY = TILE_ENTITIES.register("ruby_block", () -> TileEntityType.Builder.create(RubyBlockTileEntity::new, BlockTypes.RUBY_BLOCK.get()).build(null));
}
