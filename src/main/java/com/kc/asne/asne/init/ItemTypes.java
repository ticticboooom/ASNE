package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.block.RocketeersCraftingTableBlock;
import com.kc.asne.asne.item.RocketeersHammer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTypes {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Asne.MOD_ID);

    public static final RegistryObject<Item> ROCKETEERS_HAMMER = ITEMS.register("rocketeers_hammer", RocketeersHammer::new);
    public static final RegistryObject<Item> ROCKETEERS_CRAFTING_TABLE = ITEMS.register("rocketeers_crafting_table", () -> new BlockItem(BlockTypes.ROCKETEERS_CRAFTING_TABLE.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
    public static final RegistryObject<Item> MANUAL_PRESS_CONTROLLLER = ITEMS.register("manual_press_controller", () -> new BlockItem(BlockTypes.MANUAL_PRESS_CONTROLLER.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
}
