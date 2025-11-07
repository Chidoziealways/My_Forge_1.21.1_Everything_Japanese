package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.Design
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import net.neoforged.neoforge.registries.NewRegistryEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object ModRegistryEvents {

    private val log: Logger = LoggerFactory.getLogger(ModRegistryEvents::class.java)
    private val marker: Marker? = MarkerFactory.getMarker("MODREGISTRYEVENTS")

    private const val MAX_VARINT = Int.Companion.MAX_VALUE - 1

    @SubscribeEvent
    fun onNewRegistries(event: NewRegistryEvent) {
        log.info(marker, "Registering Custom Registries")
        event.register(ModRegistries.JUTSU)
        event.register(ModRegistries.MORPHS)

        log.info(marker, "Finished Registering Registries")
    }

    @SubscribeEvent
    fun onNewDatapackRegistries(event: DataPackRegistryEvent.NewRegistry) {
        log.info(marker, "Registering Custom Datapack Registries")

        event.dataPackRegistry(ModRegistries.KANJI, KanjiType.DIRECT_CODEC, KanjiType.DIRECT_CODEC) {builder -> builder.maxId(MAX_VARINT)}
        event.dataPackRegistry(ModRegistries.DESIGN, Design.CODEC, Design.CODEC) { builder -> builder.maxId(MAX_VARINT)}

        log.info(marker, "Finished Registering Datapack Registries")
    }
}
