package com.kc.asne.asne.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class AsneReusableCraftingItem extends AsneItem {
    public AsneReusableCraftingItem(Properties properties, int maxDamage) {
        super(properties.maxStackSize(1).maxDamage(maxDamage));
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack damaged = new ItemStack(itemStack.getItem(), 1);
        damaged.setDamage(itemStack.getDamage() + 1);
        return itemStack.getDamage() < itemStack.getMaxDamage() ? damaged : ItemStack.EMPTY;

    }
}
