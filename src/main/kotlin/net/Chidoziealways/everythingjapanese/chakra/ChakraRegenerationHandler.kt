package net.Chidoziealways.everythingjapanese.chakra

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.minecraft.core.particles.ParticleTypes
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.tick.ServerTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object ChakraRegenerationHandler {
    @SubscribeEvent
    fun onServerTick(event: ServerTickEvent.Post) {
        // Run regeneration logic during the tick event (server-side only)
        for (player in event.server.playerList.players) {
            val chakra = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
            val currentChakra = chakra!!.getCurrentChakra()
            val maxChakra = chakra.getMaxChakra()
            chakra.updateMaxChakraBasedOnXP(player.experienceLevel, player)
            if (currentChakra < maxChakra) {
                chakra.addChakra(0.01f, player) // Regenerate chakra gradually
                player.level().addParticle(
                    ParticleTypes.ENCHANT,
                    player.x,
                    player.y,
                    player.z,
                    0.5,
                    0.5,
                    0.5
                )
            }
        }
    }
}
