package com.kc.asne.base.tileentity;

import com.kc.asne.asne.capability.fluid.SteamGeneratorWaterTank;
import com.kc.asne.base.block.AsneBlock;
import com.kc.asne.base.capability.AsneEnergyStorage;
import com.kc.asne.base.capability.AsneFluidTank;
import com.kc.asne.base.container.AsneMachineContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.ItemFluidContainer;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public abstract class AsneMachineTileEntity extends LockableLootTileEntity {
    protected final ContainerType<?> containerType;
    protected NonNullList<ItemStack> invContents;
    protected IItemHandlerModifiable items = createHandler();
    protected AsneFluidTank fluids = createFluidHandler();
    protected AsneEnergyStorage energy = createEnergyHandler();


    protected boolean hasFluidTank;
    protected boolean hasEnergyStorage;


    protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    protected LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> fluids);
    protected LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);
    protected int numPlayersUsing;

    public AsneMachineTileEntity(final TileEntityType<?> typeIn, final ContainerType<?> containerType) {
        super(typeIn);
        this.containerType = containerType;
        invContents = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
    }


    protected IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    protected abstract AsneFluidTank createFluidHandler();

    protected abstract AsneEnergyStorage createEnergyHandler();

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.invContents;
    }


    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.invContents = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.asne." + getType().getRegistryName().getPath());
    }


    private void playSound(SoundEvent sound) {
        double dx = (double) this.pos.getX() + 0.5D;
        double dy = (double) this.pos.getY() + 0.5D;
        double dz = (double) this.pos.getZ() + 0.5D;
        this.world.playSound((PlayerEntity) null, dx, dy, dz, sound, SoundCategory.BLOCKS, 0.5f,
                this.world.rand.nextFloat() * 0.1f + 0.9f);
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(this.pos, block);
    }

    public static int getPlayersUsing(IBlockReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos);
        if (blockstate.hasTileEntity()) {
            TileEntity tileentity = reader.getTileEntity(pos);
            return ((AsneMachineTileEntity) tileentity).numPlayersUsing;
        }
        return 0;
    }

    public static void swapContents(AsneMachineTileEntity te, AsneMachineTileEntity otherTe) {
        NonNullList<ItemStack> list = te.getItems();
        te.setItems(otherTe.getItems());
        otherTe.setItems(list);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.itemHandler != null) {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }


    public <T> LazyOptional<T> getAvailableCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && hasFluidTank) {
            return fluidHandler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY && hasEnergyStorage) {
            return energyHandler.cast();
        }
        return super.getCapability(cap);
    }


    @Override
    public void remove() {
        super.remove();
        if (itemHandler != null) {
            itemHandler.invalidate();
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
        super.onDataPacket(net, pkt);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("x", this.pos.getX());
        compound.putInt("y", this.pos.getY());
        compound.putInt("z", this.pos.getZ());
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.saveAllItems(compound, this.invContents);
        }
        fluids.write(compound);
        energy.write(compound);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.invContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.invContents);
        }
        fluids.read(compound);
        energy.read(compound);
    }
}
