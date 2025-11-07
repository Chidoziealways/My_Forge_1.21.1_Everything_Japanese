package net.Chidoziealways.everythingjapanese.loot

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModLootModifiers {
    val LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, JAPANESE_MOD_ID)

    val ADD_ITEM by LOOT_MODIFIER_SERIALIZERS.register(
            "add_item")
            { -> AddItemModifier.Companion.CODEC }


    fun register(eventBus: IEventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus)
    }
}
