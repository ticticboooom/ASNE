package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.item.AsneItem;
import com.kc.asne.asne.item.AsneReusableCraftingItem;
import com.kc.asne.asne.item.RocketeersHammer;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTypes {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, AsneConstants.MOD_ID);

    public static final RegistryObject<Item> ROCKETEERS_HAMMER = ITEMS.register("rocketeers_hammer", RocketeersHammer::new);
    public static final RegistryObject<Item> ROCKETEERS_CRAFTING_TABLE = ITEMS.register("rocketeers_crafting_table", () -> new BlockItem(BlockTypes.ROCKETEERS_CRAFTING_TABLE.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
    public static final RegistryObject<Item> MANUAL_PRESS_CONTROLLLER = ITEMS.register("manual_press_controller", () -> new BlockItem(BlockTypes.MANUAL_PRESS_CONTROLLER.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
    public static final RegistryObject<Item> STEAM_GENERATOR = ITEMS.register("steam_generator_controller", () -> new BlockItem(BlockTypes.STEAM_GENERATOR_CONTROLLER.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
    public static final RegistryObject<Item> MACHINE_STRUCTURE = ITEMS.register("machine_structure", () -> new BlockItem(BlockTypes.MACHINE_STRUCTURE.get(),  new Item.Properties().group(ItemGroupTypes.BLOCKS_TAB)));
    public static final RegistryObject<Item> RAW_SILICON = ITEMS.register("raw_silicon", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BASIC_PROCESSOR = ITEMS.register("raw_basic_processor", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> UNPRESSED_BASIC_PROCESSOR = ITEMS.register("unpressed_basic_processor", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> BASIC_PROCESSOR = ITEMS.register("basic_processor", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> PRESSED_IRON_SHEET = ITEMS.register("pressed_iron_sheet", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> PRESSED_GOLD_SHEET = ITEMS.register("pressed_gold_sheet", () -> new AsneItem(new Item.Properties()));
    public static final RegistryObject<Item> WIRE_CUTTER = ITEMS.register("wire_cutter", () -> new AsneReusableCraftingItem(new Item.Properties(), 100));
    public static final RegistryObject<Item> GOLD_WIRE = ITEMS.register("gold_wire", () -> new AsneItem(new Item.Properties()));
}
