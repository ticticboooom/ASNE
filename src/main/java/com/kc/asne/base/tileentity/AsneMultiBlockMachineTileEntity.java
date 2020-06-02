package com.kc.asne.base.tileentity;

import com.kc.asne.asne.net.PacketHandler;
import com.kc.asne.asne.net.packets.IPacket;
import com.kc.asne.asne.net.packets.ManualPressTileSyncPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public abstract class AsneMultiBlockMachineTileEntity extends AsneMachineTileEntity implements ITickableTileEntity {
    public boolean canConstruct = false;
    public boolean isConstructed = false;

    public AsneMultiBlockMachineTileEntity(TileEntityType<?> typeIn, ContainerType<?> containerType) {
        super(typeIn, containerType);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.isConstructed = compound.getBoolean("asne_is_constructed");
        this.canConstruct = compound.getBoolean("asne_can_construct");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("asne_is_constructed", this.isConstructed);
        compound.putBoolean("asne_can_construct", this.canConstruct);
        return super.write(compound);
    }

    public void sync(IPacket msg) {
        if (this.world instanceof ServerWorld) {
            ServerWorld worldServer = (ServerWorld) world;
            List<ServerPlayerEntity> players = worldServer.getPlayers();
            for (final ServerPlayerEntity player : players) {
                PacketHandler.sendTo(msg, player);
            }
        }
    }

    @Override
    public void tick() {
        sync(new ManualPressTileSyncPacket(write(new CompoundNBT())));
    }
}