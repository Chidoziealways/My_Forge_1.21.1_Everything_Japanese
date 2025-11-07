package net.Chidoziealways.everythingjapanese.component

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.Design
import net.Chidoziealways.everythingjapanese.codec.STRING_SET_CODEC
import net.Chidoziealways.everythingjapanese.codec.STRING_SET_STREAM_CODEC
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import java.util.function.UnaryOperator

object ModDataComponentTypes {
    val DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, JAPANESE_MOD_ID)

    val COORDINATES = register(
        "coordinates"
    ) { builder -> builder.persistent(BlockPos.CODEC)}

    val BLADE = register(
        "blade"
    ) { builder -> builder.persistent(BladeType.CODEC) }

    val EDITABLE_TEXT = register(
        "editable_text"
    ) { builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8) }

    val WRAPPING = register(
        "wrapping"
    ) { builder -> builder.persistent(Wrapping.CODEC) }

    val TALISMAN_KANJI = register("talisman_kanji") { builder -> builder.persistent(KanjiType.CODEC).networkSynchronized(
        KanjiType.STREAM_CODEC) }

    val DESIGN = register("design") { builder -> builder.persistent(Design.CODEC).networkSynchronized(Design.STREAM_CODEC) }

    val MORPHS = register("morphs") { builder -> builder.persistent(STRING_SET_CODEC).networkSynchronized(
        STRING_SET_STREAM_CODEC
    ) }

    val CURRENT_MORPH = register("current_morph") { it.persistent(Codec.STRING).networkSynchronized(
        ByteBufCodecs.STRING_UTF8)}

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
