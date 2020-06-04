package com.kc.asne.asne.net.packets;

import com.kc.asne.base.tileentity.AsneMultiBlockMachineTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MachineTileSyncPacket implements IPacket {
    private CompoundNBT tag;

    public MachineTileSyncPacket(CompoundNBT tag) {
        this.tag = tag;
    }

    public static void encode(MachineTileSyncPacket pkt, PacketBuffer buf) {
        buf.writeCompoundTag(pkt.tag);
    }

    public static MachineTileSyncPacket decode(PacketBuffer buf) {
        return new MachineTileSyncPacket(buf.readCompoundTag());
    }

    public static class Handler {
        public static void handle(final MachineTileSyncPacket pkt, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                CompoundNBT nbt = pkt.tag;
                int x = nbt.getInt("x");
                int y = nbt.getInt("y");
                int z = nbt.getInt("z");
                TileEntity te = Minecraft.getInstance().world.getTileEntity(new BlockPos(x, y, z));
                if (te instanceof AsneMultiBlockMachineTileEntity) {
                    AsneMultiBlockMachineTileEntity multiBlockMachineTileEntity = (AsneMultiBlockMachineTileEntity) te;
                    multiBlockMachineTileEntity.read(nbt);

                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
