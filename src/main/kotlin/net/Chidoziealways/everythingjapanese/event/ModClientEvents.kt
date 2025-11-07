package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object ModClientEvents {
    @SubscribeEvent
    fun onComputerFovModifierEvent(event: ComputeFovModifierEvent) {
        if (event.player.isUsingItem && event.player.getUseItem().item === JModItems.DAIKYU) {
            var fovModifier = 1f
            val ticksUsingItem = event.player.ticksUsingItem
            var deltaTicks = ticksUsingItem.toFloat() / 20f
            if (deltaTicks > 1f) {
                deltaTicks = 1f
            } else {
                deltaTicks *= deltaTicks
            }
            fovModifier *= 1f - deltaTicks * 0.15f
            event.newFovModifier = fovModifier
        }
    }
}
