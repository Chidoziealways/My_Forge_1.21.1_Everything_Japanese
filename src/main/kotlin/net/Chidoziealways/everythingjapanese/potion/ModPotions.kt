package net.Chidoziealways.everythingjapanese.potion

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModPotions {
    val POTIONS: DeferredRegister<Potion?> =
        DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID)

    val ADRENALINE_POTION: RegistryObject<Potion?>? = POTIONS.register<Potion?>(
        "adrenaline_potion",
        Supplier {
            Potion(
                "adrenaline_potion",
                MobEffectInstance(ModEffects.ADRENALINE_EFFECT!!.registryObject.holder.get(), 200, 0)
            )
        })

    fun register(eventBus: BusGroup?) {
        POTIONS.register(eventBus)
    }
}
