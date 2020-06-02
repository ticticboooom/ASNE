package com.kc.asne.asne.net.packets;

import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManualPressTileSyncPacket implements IPacket {
    private CompoundNBT tag;

    public ManualPressTileSyncPacket(CompoundNBT tag) {
        this.tag = tag;
    }

    public static void encode(ManualPressTileSyncPacket pkt, PacketBuffer buf) {
        buf.writeCompoundTag(pkt.tag);
    }

    public static ManualPressTileSyncPacket decode(PacketBuffer buf) {
        return new ManualPressTileSyncPacket(buf.readCompoundTag());
    }

    public static class Handler {
        public static void handle(final ManualPressTileSyncPacket pkt, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                CompoundNBT nbt = pkt.tag;
                int x = nbt.getInt("x");
                int y = nbt.getInt("y");
                int z = nbt.getInt("z");
                TileEntity te = Minecraft.getInstance().world.getTileEntity(new BlockPos(x, y, z));
                if (te instanceof ManualPressTileEntity) {
                    ManualPressTileEntity manualPressTileEntity = (ManualPressTileEntity) te;
                    manualPressTileEntity.read(nbt);

                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
