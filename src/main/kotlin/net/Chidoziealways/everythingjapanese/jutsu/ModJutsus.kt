package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.LargeFireballJutsuLvel1
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.SmallFireballJutsu
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.SmallWindballJutsu
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate
import thedarkcolour.kotlinforforge.forge.registerObject

object ModJutsus {
    val JUTSUS: DeferredRegister<Jutsu> = DeferredRegister.create(ModRegistries.JUTSU, MOD_ID)
    val SMALL_FIREBALL = JUTSUS.registerObject("small_fireball") { SmallFireballJutsu() }
    val SMALL_WINDBALL = JUTSUS.registerObject("small_windball") { SmallWindballJutsu() }
    val LARGE_FIREBALL = JUTSUS.registerObject("large_fireball") { LargeFireballJutsuLvel1() }

    fun register(eventBus: BusGroup?) {
        JUTSUS.register(eventBus)
    }

    private val LOOKUP: MutableMap<ResourceLocation?, Jutsu?> = HashMap()

    // This should be called after the registry has been fired.
    fun buildLookup() {
        LOOKUP[SMALL_FIREBALL.registryObject.id] = SMALL_FIREBALL.get()
        LOOKUP[SMALL_WINDBALL.registryObject.id] = SMALL_WINDBALL.get()
        LOOKUP[LARGE_FIREBALL.registryObject.id] = LARGE_FIREBALL.get()
    }

    // Lookup method used in our packet.
    fun getJutsu(id: ResourceLocation?): Jutsu? {
        return LOOKUP[id]
    }

    fun isValidJutsu(id: ResourceLocation?): Boolean {
        return LOOKUP.containsKey(id)
    }

    fun getHolder(id: ResourceLocation?): Holder<Jutsu?>? {
        return JUTSUS.getEntries().stream()
            .filter { entry: RegistryObject<Jutsu?>? -> entry!!.getKey()!!.location() == id }
            .map { entry: RegistryObject<Jutsu?>? -> entry!!.getHolder().orElseThrow() }
            .findFirst()
            .orElse(null)
    }
}
