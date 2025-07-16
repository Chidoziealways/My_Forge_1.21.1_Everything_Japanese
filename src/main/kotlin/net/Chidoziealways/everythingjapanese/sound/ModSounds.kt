package net.Chidoziealways.everythingjapanese.sound

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.JukeboxSong
import net.minecraftforge.common.util.ForgeSoundType
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModSounds {
    val SOUND_EVENTS: DeferredRegister<SoundEvent?> =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID)

    val CHISEL_USE: RegistryObject<SoundEvent?>? = registerSoundEvent("chisel_use")

    val MAGIC_BLOCK_BREAK: RegistryObject<SoundEvent?>? = registerSoundEvent("magic_block_break")
    val MAGIC_BLOCK_STEP: RegistryObject<SoundEvent?>? = registerSoundEvent("magic_block_step")
    val MAGIC_BLOCK_PLACE: RegistryObject<SoundEvent?>? = registerSoundEvent("magic_block_place")
    val MAGIC_BLOCK_HIT: RegistryObject<SoundEvent?>? = registerSoundEvent("magic_block_hit")
    val MAGIC_BLOCK_FALL: RegistryObject<SoundEvent?>? = registerSoundEvent("magic_block_fall")

    val MAGIC_BLOCK_SOUNDS: ForgeSoundType = ForgeSoundType(
        1f, 1f,
        MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE,
        MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL
    )

    val AO_TO_NATSU: RegistryObject<SoundEvent?>? = registerSoundEvent("ao_to_natsu")
    val AO_TO_NATSU_KEY: ResourceKey<JukeboxSong?> = ResourceKey.create<JukeboxSong?>(
        Registries.JUKEBOX_SONG,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ao_to_natsu")
    )


    private fun registerSoundEvent(name: String): RegistryObject<SoundEvent?>? {
        return SOUND_EVENTS.register<SoundEvent?>(
            name,
            Supplier {
                SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(
                        MOD_ID,
                        name
                    )
                )
            })
    }

    fun register(eventBus: BusGroup?) {
        SOUND_EVENTS.register(eventBus)
    }
}
