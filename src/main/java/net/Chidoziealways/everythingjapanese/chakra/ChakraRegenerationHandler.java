package net.Chidoziealways.everythingjapanese.chakra;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.network.ModNetwork;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChakraRegenerationHandler {

    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // Run regeneration logic during the tick event (server-side only)
        if (event.phase == TickEvent.Phase.END) {
            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(chakra -> {
                    float currentChakra = chakra.getChakra();
                    int maxChakra = chakra.getMaxChakra();
                    chakra.updateMaxChakraBasedOnXP(player.experienceLevel);

                    if (currentChakra < maxChakra) {
                        chakra.addChakra(0.01f); // Regenerate chakra gradually
                        player.level().addParticle(ParticleTypes.ENCHANT, player.getX(), player.getY(), player.getZ(), 0.5, 0.5, 0.5);

                        // ✅ Send Chakra Sync Packet to Update Client UI
                        ModNetwork.CHANNEL.send(
                                new ChakraSyncPacket(chakra.getChakra(), chakra.getMaxChakra()),
                                PacketDistributor.PLAYER.with(player)
                        );
                    }
                });
            }
        }
    }
}
