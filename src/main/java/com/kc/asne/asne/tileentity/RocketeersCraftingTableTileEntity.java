package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.container.RocketeersCraftingTableContainer;
import com.kc.asne.asne.init.TileEntityTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class RocketeersCraftingTableTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(26, ItemStack.EMPTY);
    protected int numPlayersUsing;
    private IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public RocketeersCraftingTableTileEntity() {
        this(TileEntityTypes.ROCKETEERS_CRAFTING_TABLE.get());
    }

    public RocketeersCraftingTableTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestContents = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.asne.rocketeers_crafting_table");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new RocketeersCraftingTableContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return 26;
    }
}
