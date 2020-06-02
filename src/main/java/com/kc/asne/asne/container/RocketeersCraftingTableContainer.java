package com.kc.asne.asne.container;

import com.kc.asne.asne.container.slot.RocketeersCraftingTableIngredientSlot;
import com.kc.asne.asne.container.slot.RocketeersCraftingTableResultSlot;
import com.kc.asne.asne.init.BlockTypes;
import com.kc.asne.asne.init.ContainerTypes;
import com.kc.asne.asne.tileentity.RocketeersCraftingTableTileEntity;
import com.kc.asne.asne.util.parser.RocketeersCraftingRecipe;
import com.kc.asne.asne.util.parser.CustomParser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Objects;

public class RocketeersCraftingTableContainer extends Container {
    public final RocketeersCraftingTableTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private Slot outPutSlot;

    public RocketeersCraftingTableContainer(final int windowId, final PlayerInventory playerInventory, final RocketeersCraftingTableTileEntity tileEntity) {
        super(ContainerTypes.ROCKETEERS_CRAFTING_TABLE.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        ArrayList<Slot> craftingSlots = new ArrayList<>();

        // Out Put Grid
        outPutSlot = new RocketeersCraftingTableResultSlot(tileEntity, 25, 135, 63, craftingSlots);

        this.addSlot(outPutSlot);

        // Crafting Grid
        int startX = 8;
        int startY = 17;
        int slotSizeWithGap = 23;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Slot slot = new RocketeersCraftingTableIngredientSlot(tileEntity, (row * 5) + col, startX + (row * slotSizeWithGap), startY + (col * slotSizeWithGap),outPutSlot, (i) -> {computeRecipes(); return null;});
                this.addSlot(slot);
                craftingSlots.add(slot);
            }
        }



        // Main Player Inventory
        int slotSizePlus2 = 18;

        int playerStartY = 140;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2), playerStartY + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hatBarY = 198;
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hatBarY));
        }
    }

    private void computeRecipes() {
        NonNullList<ItemStack> items = tileEntity.getItems();
        RocketeersCraftingRecipe chosenRecipe = null;
        for (int r = 0; r < CustomParser.recipes.size(); r++) {
            RocketeersCraftingRecipe configRecipe = CustomParser.recipes.get(r);
            boolean isCorrect = true;
            for (int i = 0; i < 25; i++) {
                String itemResourceName = items.get(i).getItem().getRegistryName().toString();
                if (!configRecipe.items.get(i).equals(itemResourceName)) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                chosenRecipe = configRecipe;
            }
        }

        if (chosenRecipe != null) {
            outPutSlot.putStack(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(chosenRecipe.result))));
        } else {
            outPutSlot.putStack(new ItemStack(Items.AIR));
        }
    }

    private static RocketeersCraftingTableTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof RocketeersCraftingTableTileEntity) {
            return (RocketeersCraftingTableTileEntity) tileAtPos;
        }

        throw new IllegalStateException("Tile entity is not correct" + tileAtPos);
    }

    public RocketeersCraftingTableContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockTypes.ROCKETEERS_CRAFTING_TABLE.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if (index < 26) {
                if (!this.mergeItemStack(itemStack1, 26, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, 26, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
