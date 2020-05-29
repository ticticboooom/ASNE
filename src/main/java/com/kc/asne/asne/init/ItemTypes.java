package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTypes {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Asne.MOD_ID);

    public static final RegistryObject<Item> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(BlockTypes.RUBY_BLOCK.get(), new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));

}
