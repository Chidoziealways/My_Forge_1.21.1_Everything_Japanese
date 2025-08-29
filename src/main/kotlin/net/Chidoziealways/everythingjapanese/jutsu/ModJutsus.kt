package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.ChiretsuShōJutsu
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.FireballJutsu
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.SmallWindballJutsu
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModJutsus {
    val JUTSUS = DeferredRegister.create(ModRegistries.JUTSU_KEY, MOD_ID)
    val CHIRETSU_SHO_JUTSU by JUTSUS.register("chiretsu_sho_jutsu") { -> ChiretsuShōJutsu() }
    val SMALL_WINDBALL by JUTSUS.register("small_windball") { -> SmallWindballJutsu() }
    val FIREBALL by JUTSUS.register("fireball") { -> FireballJutsu() }

    fun register(eventBus: IEventBus) {
        JUTSUS.register(eventBus)
    }
}
