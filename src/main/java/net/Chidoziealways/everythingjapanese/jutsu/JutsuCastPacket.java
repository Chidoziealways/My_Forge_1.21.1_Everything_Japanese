package net.Chidoziealways.everythingjapanese.jutsu;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket;
import net.Chidoziealways.everythingjapanese.network.ModNetwork;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record JutsuCastPacket() {
    private static final Logger log = LogManager.getLogger(JutsuCastPacket.class);

    public void encode(FriendlyByteBuf buf) {
    }

    public static JutsuCastPacket decode(FriendlyByteBuf buf) {
        return new JutsuCastPacket();
    }

    public static void handle(JutsuCastPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                player.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsuCapability -> {
                    String jutsuPath = iJutsuCapability.getSelectedJutsu();
                    ResourceLocation jutsuId = ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, jutsuPath);
                    if (iJutsuCapability.hasLearnedJutsu(jutsuId.getPath())) {
                        Jutsu jutsu = ModJutsus.getJutsu(jutsuId);
                        log.debug("Learnt Jutsu: {}, Selected Jutsu: {}", iJutsuCapability.getLearnedJutsus(), iJutsuCapability.getSelectedJutsu());
                        if (jutsu != null) {
                            player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> {
                                float cost = jutsu.getChakraCost();
                                if (iChakra.getChakra() >= cost) {
                                    iChakra.subtractChakra(cost);
                                    jutsu.cast(player);

                                    ModNetwork.CHANNEL.send(
                                            new ChakraSyncPacket(iChakra.getChakra(), iChakra.getMaxChakra()),
                                            PacketDistributor.PLAYER.with(player)
                                    );
                                } else {
                                    player.sendSystemMessage(Component.literal("Not Enough Chakra to Cast this Jutsu!"));
                                }
                            });
                        }
                    } else {
                        player.sendSystemMessage(Component.literal("You haven't learned this Jutsu Yet!!"));
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }
}
