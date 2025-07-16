package net.Chidoziealways.everythingjapanese.enchantment

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.enchantment.custom.LightningStrikerEnchantmentEffect
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModEnchantmentEffects {
    val ENTITY_ENCHANTMENT_EFFECT: DeferredRegister<MapCodec<out EnchantmentEntityEffect?>?> =
        DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MOD_ID)

    val LIGHTNING_STRIKER: RegistryObject<MapCodec<out EnchantmentEntityEffect?>?>? =
        ENTITY_ENCHANTMENT_EFFECT.register<MapCodec<out EnchantmentEntityEffect?>?>(
            "lightning_striker",
            Supplier { LightningStrikerEnchantmentEffect.Companion.CODEC })

    fun register(eventBus: BusGroup?) {
        ENTITY_ENCHANTMENT_EFFECT.register(eventBus)
    }
}
