package com.kc.asne.asne.net.packets;

import com.kc.asne.asne.net.PacketHandler;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.asne.util.parser.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManualPressProcessPacket {
    private BlockPos pos;
    private boolean shouldIncrement;

    public ManualPressProcessPacket(BlockPos pos, boolean shouldIncrement) {
        this.pos = pos;
        this.shouldIncrement = shouldIncrement;
    }

    public static void encode(ManualPressProcessPacket pkt, PacketBuffer buf) {
        buf.writeBlockPos(pkt.pos);
        buf.writeBoolean(pkt.shouldIncrement);
    }

    public static ManualPressProcessPacket decode(PacketBuffer buf) {
        return new ManualPressProcessPacket(buf.readBlockPos(), buf.readBoolean());
    }

    public static class Handler {
        public static void handle(final ManualPressProcessPacket pkt, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                TileEntity te = Minecraft.getInstance().world.getTileEntity(pkt.pos);
                if (te instanceof ManualPressTileEntity) {
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
