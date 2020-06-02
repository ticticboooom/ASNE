package com.kc.asne.asne.item;

import com.kc.asne.asne.init.ItemGroupTypes;
import net.minecraft.item.Item;

public class AsneItem extends Item {
    public AsneItem(Properties properties) {
        super(properties.group(ItemGroupTypes.ITEMS_TAB));
    }
}
