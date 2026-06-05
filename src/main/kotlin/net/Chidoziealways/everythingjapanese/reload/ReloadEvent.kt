package net.Chidoziealways.everythingjapanese.reload

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.kanji.KanjiEffectLoader
import net.minecraft.resources.Identifier
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent
import net.neoforged.neoforge.event.AddServerReloadListenersEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object ReloadEvent {

    @SubscribeEvent
    fun onReload(e: AddServerReloadListenersEvent) {
        println("Firing AddReloadlistenerEVent")
        e.addListener(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "ej_lua_scripts"), KanjiEffectLoader::reload)
    }

    @SubscribeEvent
    fun onReloadClient(e: AddClientReloadListenersEvent) {
    }
}