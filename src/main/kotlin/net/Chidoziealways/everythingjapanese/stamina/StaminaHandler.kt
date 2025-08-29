package net.Chidoziealways.everythingjapanese.stamina

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.tick.PlayerTickEvent
import net.neoforged.neoforge.event.tick.ServerTickEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object StaminaHandler {
    @SubscribeEvent
    fun onPlayerTick(event: PlayerTickEvent.Post) {
        val player = event.entity as? ServerPlayer ?: return
        if (player.level().isClientSide) return

        val stamina = player.getCapability(ModCapabilities.STAMINA_CAPABILITY) ?: return

        if (player.isSprinting && !player.isCreative) {
            if (stamina.getStamina() > 0) {
                stamina.decreaseStamina(0.005F, player) // tweak rate
            } else {
                player.isSprinting = false
            }
        } else {
            if (stamina.getStamina() < stamina.getMaxStamina()) {
                stamina.increaseStamina(0.1F, player) // regen when NOT sprinting
            }
        }
    }

}