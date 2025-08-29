package net.Chidoziealways.everythingjapanese.enchantment

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.enchantment.custom.LightningStrikerEnchantmentEffect
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModEnchantmentEffects {
    val ENTITY_ENCHANTMENT_EFFECT = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MOD_ID)

    val LIGHTNING_STRIKER by ENTITY_ENCHANTMENT_EFFECT.register(
            "lightning_striker")
            { -> LightningStrikerEnchantmentEffect.Companion.CODEC }

    fun register(eventBus: IEventBus) {
        ENTITY_ENCHANTMENT_EFFECT.register(eventBus)
    }
}
