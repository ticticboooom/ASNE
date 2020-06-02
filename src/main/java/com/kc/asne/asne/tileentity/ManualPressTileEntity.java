package com.kc.asne.asne.tileentity;

import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.asne.init.ContainerTypes;
import com.kc.asne.asne.init.TileEntityTypes;
import com.kc.asne.asne.util.parser.CustomParser;
import com.kc.asne.asne.util.parser.PressRecipe;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

public class ManualPressTileEntity extends AsneMachineTileEntity implements ITickableTileEntity {
    public ManualPressContainer cont = null;
    public boolean isConstructed = false;
    private static final int COAL_BURN_TIME = 800;
    private float processingProgress = 0;
    private float fuelProgress = 0;
    public ManualPressTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, ContainerTypes.MANUAL_PRESS.get());
    }

    @Override
    protected IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    public ManualPressTileEntity() {
        this(TileEntityTypes.MANUAL_PRESS.get());
    }


    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ManualPressContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return 4;
    }

    public float getPercentageOfPressingRemaining() {
        return processingProgress;
    }

    public float getPercentageOfFuelRemaining() {
        return fuelProgress / COAL_BURN_TIME;
    }

    public void incrementProgress() {

        if ((this.invContents.get(3).getCount() > 0 && this.invContents.get(3).isItemEqual(new ItemStack(Items.COAL))) || fuelProgress > 0) {
            if (fuelProgress > 0) {
                fuelProgress--;
            } else if (this.invContents.get(3).getCount() > 0 && this.invContents.get(0).getCount() > 0) {
                this.invContents.get(3).setCount(this.invContents.get(3).getCount() - 1);
                fuelProgress = COAL_BURN_TIME;
            }
        } else {
            return;
        }
        PressRecipe recipe = CustomParser.pressRecipes.getOrDefault(this.invContents.get(0).getItem().getRegistryName().toString(), null);
        if ((this.invContents.get(0).isEmpty() && processingProgress <= 0) || recipe == null) {
            return;
        }
        processingProgress += 1.f / (float) recipe.ticks;
        processingProgress = MathHelper.clamp(processingProgress, 0, 1);
        if (processingProgress >= 1) {
            processingProgress = 0;
            ItemStack inputStack = this.invContents.get(0).copy();
            inputStack.setCount(this.invContents.get(0).getCount() - 1);
            this.invContents.set(0, inputStack);

            ItemStack resultingStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.out)), 1);

            if (this.invContents.get(2).isItemEqual(resultingStack)) {
                ItemStack stack = this.invContents.get(2).copy();
                stack.setCount(this.invContents.get(2).getCount() + 1);
                this.invContents.set(2, stack);
            }
            if (this.invContents.get(2).isEmpty()) {
                this.invContents.set(2, resultingStack.copy());
            }
        }
    }

    public void resetProcessingProgress(){
        processingProgress = 0;
    }

    @Override
    public void tick() {
        incrementProgress();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putFloat("asne_fuel_progress", this.fuelProgress);
        compound.putFloat("asne_processing_progress", this.processingProgress);
        compound.putBoolean("asne_is_constructed", this.isConstructed);
        ItemStackHelper.saveAllItems(compound, this.invContents);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        this.fuelProgress = compound.getFloat("asne_fuel_progress");
        this.processingProgress = compound.getFloat("asne_processing_progress");
        this.isConstructed = compound.getBoolean("asne_is_constructed");
        ItemStackHelper.loadAllItems(compound, this.invContents);
        super.read(compound);
    }
}