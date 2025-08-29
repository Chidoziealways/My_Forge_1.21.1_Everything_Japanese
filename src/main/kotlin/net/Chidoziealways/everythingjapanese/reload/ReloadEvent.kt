package net.Chidoziealways.everythingjapanese.reload

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.kanji.KanjiEffectLoader
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent
import net.neoforged.neoforge.event.AddServerReloadListenersEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object ReloadEvent {

    @SubscribeEvent
    fun onReload(e: AddServerReloadListenersEvent) {
        println("Firing AddReloadlistenerEVent")
        e.addListener(ResourceLocation.fromNamespaceAndPath(MOD_ID, "quest_reload"), QuestReloadListener(e.registryAccess))
        e.addListener(ResourceLocation.fromNamespaceAndPath(MOD_ID, "ej_lua_scripts"), KanjiEffectLoader::reload)
    }

    @SubscribeEvent
    fun onReloadClient(e: AddClientReloadListenersEvent) {
    }
}