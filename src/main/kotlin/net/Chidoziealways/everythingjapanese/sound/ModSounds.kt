package net.Chidoziealways.everythingjapanese.sound

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.JukeboxSong
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.util.DeferredSoundType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModSounds {
    val SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, JAPANESE_MOD_ID)

    val CHISEL_USE = registerSoundEvent("chisel_use")

    val MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break")
    val MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step")
    val MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place")
    val MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit")
    val MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall")

    val MAGIC_BLOCK_SOUNDS = DeferredSoundType(
        1f, 1f,
        MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE,
        MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL
    )

    val AO_TO_NATSU = registerSoundEvent("ao_to_natsu")
    val AO_TO_NATSU_KEY: ResourceKey<JukeboxSong> = ResourceKey.create<JukeboxSong>(
        Registries.JUKEBOX_SONG,
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "ao_to_natsu")
    )


    private fun registerSoundEvent(name: String): DeferredHolder<SoundEvent, SoundEvent> {
        return SOUND_EVENTS.register(
            name,
            Supplier {
                SoundEvent.createVariableRangeEvent(
                    Identifier.fromNamespaceAndPath(
                        JAPANESE_MOD_ID,
                        name
                    )
                )
            })
    }

    fun register(eventBus: IEventBus) {
        SOUND_EVENTS.register(eventBus)
    }
}
