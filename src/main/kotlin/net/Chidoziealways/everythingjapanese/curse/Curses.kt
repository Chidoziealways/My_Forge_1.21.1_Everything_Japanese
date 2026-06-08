package net.Chidoziealways.everythingjapanese.curse

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object Curses {
    val CURSES = DeferredRegister.create(ModRegistries.CURSES_KEY, JAPANESE_MOD_ID)
    val POISON by CURSES.register("poison") { ->
        Curse("Poison")
    }
    fun register(eventBus: IEventBus) {
        CURSES.register(eventBus)
    }
}