package net.Chidoziealways.everythingjapanese.block

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.chat.Component
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object BlockEvents {
    @SubscribeEvent
    fun onRightClickBlock(event: PlayerInteractEvent.RightClickBlock) {
        val level = event.level
        val pos = event.pos
        val player = event.entity

        val lock = level.getCapability(ModCapabilities.LOCK_CAPABILITY, pos) ?: return
        if (lock.isLocked() && lock.getOwner() != player.uuid) {
            player.sendOverlayMessage(Component.literal("This block is sealed by another player!"))
            event.isCanceled = true
        }
    }
}