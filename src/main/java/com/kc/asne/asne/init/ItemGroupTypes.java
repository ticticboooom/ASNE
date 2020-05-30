package com.kc.asne.asne.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemGroupTypes {
    public static final ItemGroup BLOCKS_TAB = new ItemGroup("asne_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemTypes.ROCKETEERS_CRAFTING_TABLE.get());
        }
    };
    public static final ItemGroup ITEMS_TAB = new ItemGroup("asne_items") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemTypes.ROCKETEERS_HAMMER.get());
        }
    };
}
