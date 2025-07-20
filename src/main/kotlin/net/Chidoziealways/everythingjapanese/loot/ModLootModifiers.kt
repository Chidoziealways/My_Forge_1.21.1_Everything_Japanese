package net.Chidoziealways.everythingjapanese.loot

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.function.Supplier

object ModLootModifiers {
    val LOOT_MODIFIER_SERIALIZERS: DeferredRegister<MapCodec<out IGlobalLootModifier?>?> =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID)

    val ADD_ITEM = LOOT_MODIFIER_SERIALIZERS.registerObject(
            "add_item")
            { AddItemModifier.Companion.CODEC }


    fun register(eventBus: BusGroup?) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus)
    }
}
