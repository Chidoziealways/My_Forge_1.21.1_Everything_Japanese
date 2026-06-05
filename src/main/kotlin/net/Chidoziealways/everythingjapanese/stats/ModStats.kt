package net.Chidoziealways.everythingjapanese.stats

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModStats {
    val STATS = DeferredRegister.create(Registries.CUSTOM_STAT, JAPANESE_MOD_ID)

    val INTERACT_WITH_CALLIGRAPHY_TABLE by STATS.register("interact_with_calligraphy_table") { ->
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "interact_with_calligraphy_table")
    }

    fun register(bus: IEventBus) {
        STATS.register(bus)
    }
}