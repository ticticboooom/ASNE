package com.kc.asne.asne.net;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.net.packets.ManualPressTileSyncPacket;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(AsneConstants.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        int disc = 0;

        HANDLER.registerMessage(disc++, ManualPressTileSyncPacket.class, ManualPressTileSyncPacket::encode, ManualPressTileSyncPacket::decode, ManualPressTileSyncPacket.Handler::handle);

    }

    public static void sendTo(Object msg, ServerPlayerEntity player) {
        HANDLER.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }
}
