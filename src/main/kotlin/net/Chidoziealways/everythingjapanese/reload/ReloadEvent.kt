package net.Chidoziealways.everythingjapanese.reload

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraftforge.event.AddReloadListenerEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object ReloadEvent {

    @SubscribeEvent
    fun onReload(e: AddReloadListenerEvent) {
        println("Firing AddReloadlistenerEVent")
        e.addListener(QuestReloadListener(e.registries))
    }
}