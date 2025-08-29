package net.Chidoziealways.everythingjapanese.component

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import java.util.function.UnaryOperator

object ModDataComponentTypes {
    val DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MOD_ID)

    val COORDINATES = register(
        "coordinates"
    ) { builder -> builder.persistent(BlockPos.CODEC)}

    val BLADE = register(
        "blade"
    ) { builder -> builder.persistent(BladeType.CODEC) }

    val WRAPPING = register(
        "wrapping"
    ) { builder -> builder.persistent(Wrapping.CODEC) }

    val TALISMAN_KANJI = register("talisman_kanji") { builder -> builder.persistent(KanjiType.CODEC).networkSynchronized(
        KanjiType.STREAM_CODEC) }

    private fun <I> register(
        name: String,
        builderOperator: UnaryOperator<DataComponentType.Builder<I>>
    ): DeferredHolder<DataComponentType<*>, DataComponentType<I>> {
        return DATA_COMPONENT_TYPES.register(
            name,
            Supplier { builderOperator.apply(DataComponentType.builder()).build() })
    }

    fun register(eventBus: IEventBus) {
        DATA_COMPONENT_TYPES.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE DATA COMPONENT IN MODDATACOMPONENTTYPES")
    }
}
