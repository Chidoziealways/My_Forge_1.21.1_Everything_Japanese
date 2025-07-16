package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.NewRegistryEvent
import net.minecraftforge.registries.RegistryBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.MOD)
object ModRegistryEvents {

    private val log: Logger = LoggerFactory.getLogger(ModRegistryEvents::class.java)
    private val marker: Marker? = MarkerFactory.getMarker("MODREGISTRYEVENTS")

    private const val MAX_VARINT = Int.Companion.MAX_VALUE - 1

    @JvmStatic
    @SubscribeEvent
    fun onNewRegistries(event: NewRegistryEvent) {
        log.info(marker, "Registering Custom Registries")
        event.create(
            RegistryBuilder<Jutsu>()
                .setName(ModRegistries.JUTSU.location())
                .setDefaultKey(ModRegistries.JUTSU.location())
                .setMaxID(MAX_VARINT)
        )
        log.info(marker, "Finished Registering Jutsu")
    }
}
