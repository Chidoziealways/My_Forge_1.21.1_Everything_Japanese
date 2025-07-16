package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.ComputeFovModifierEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE, value = [Dist.CLIENT])
object ModClientEvents {
    @JvmStatic
    @SubscribeEvent
    fun onComputerFovModifierEvent(event: ComputeFovModifierEvent) {
        if (event.player.isUsingItem && event.player.getUseItem().item === ModItems.DAIKYU!!.get()) {
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
