package net.Chidoziealways.everythingjapanese.potion

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModPotions {
    val POTIONS = DeferredRegister.create(Registries.POTION, MOD_ID)

    val ADRENALINE_POTION = POTIONS.register(
        "adrenaline_potion",
        Supplier {
            Potion(
                "adrenaline_potion",
                MobEffectInstance(ModEffects.ADRENALINE_EFFECT.delegate, 200, 0)
            )
        })

    fun register(eventBus: IEventBus) {
        POTIONS.register(eventBus)
    }
}
