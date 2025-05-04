package net.Chidoziealways.everythingjapanese.network;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket;
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket;
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.*;

public class ModNetwork {
    private static final int PROTOCOL_VERSION = 1;

    public static final SimpleChannel CHANNEL = ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "network"))
            .clientAcceptedVersions((status, version) -> true)
            .serverAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(PROTOCOL_VERSION)
            .simpleChannel();

    public static void registerPackets() {
        int id = 0;

        CHANNEL.messageBuilder(ChakraSyncPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ChakraSyncPacket::encode)
                .decoder(ChakraSyncPacket::decode)
                .consumer((chakraSyncPacket, context) -> {
                   context.enqueueWork(() -> {
                       Minecraft mc = Minecraft.getInstance();
                       assert mc.player != null;
                       mc.player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                               .ifPresent(iChakra -> {
                                   iChakra.setChakra(chakraSyncPacket.chakra());
                               });
                   });
                   context.setPacketHandled(true);
                })
                .add();

        CHANNEL.messageBuilder(JutsuCastPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(JutsuCastPacket::encode)
                .decoder(JutsuCastPacket::decode)
                .consumer(JutsuCastPacket::handle)
                .add();

        CHANNEL.messageBuilder(CycleJutsuPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(CycleJutsuPacket::encode)
                .decoder(CycleJutsuPacket::decode)
                .consumer(CycleJutsuPacket::handle)
                .add();
    }
}
