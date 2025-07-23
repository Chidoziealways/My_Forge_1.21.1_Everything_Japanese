package net.Chidoziealways.everythingjapanese.chakra

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.minecraft.core.particles.ParticleTypes
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.TickEvent.ServerTickEvent
import net.minecraftforge.network.PacketDistributor

object ChakraRegenerationHandler {
    fun onServerTick(event: ServerTickEvent.Post) {
        // Run regeneration logic during the tick event (server-side only)
        for (player in event.server.playerList.players) {
            player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                .ifPresent(NonNullConsumer { chakra: IChakra? ->
                    val currentChakra = chakra!!.chakra
                    val maxChakra = chakra.getMaxChakra()
                    chakra.updateMaxChakraBasedOnXP(player.experienceLevel)
                    if (currentChakra < maxChakra) {
                        chakra.addChakra(0.01f) // Regenerate chakra gradually
                        player.level().addParticle(
                            ParticleTypes.ENCHANT,
                            player.x,
                            player.y,
                            player.z,
                            0.5,
                            0.5,
                            0.5
                        )

                        // ✅ Send Chakra Sync Packet to Update Client UI
                        ModNetwork.CHANNEL.send(
                            ChakraSyncPacket(chakra.chakra, chakra.getMaxChakra()),
                            PacketDistributor.PLAYER.with(player)
                        )
                    }
                })
        }
    }
}
