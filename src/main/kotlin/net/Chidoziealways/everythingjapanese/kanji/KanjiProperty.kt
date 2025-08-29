package net.Chidoziealways.everythingjapanese.kanji

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class KanjiProperty: SelectItemModelProperty<ResourceKey<KanjiType>> {
    companion object {
        val VALUE_CODEC: Codec<ResourceKey<KanjiType>> = ResourceKey.codec(ModRegistries.KANJI)
        val TYPE: SelectItemModelProperty.Type<KanjiProperty, ResourceKey<KanjiType>> =
            SelectItemModelProperty.Type.create(MapCodec.unit(KanjiProperty()), VALUE_CODEC)
    }

    override fun get(
        stack: ItemStack,
        level: ClientLevel?,
        entity: LivingEntity?,
        seed: Int,
        displayContext: ItemDisplayContext
    ): ResourceKey<KanjiType>? {
        val kanjiType = stack.get(ModDataComponentTypes.TALISMAN_KANJI.get())
        return kanjiType?.unwrapKey()?.orElse(null) // unwrap to Holder instead of ResourceKey
    }

    override fun valueCodec(): Codec<ResourceKey<KanjiType>> = VALUE_CODEC

    override fun type(): SelectItemModelProperty.Type<out SelectItemModelProperty<ResourceKey<KanjiType>>, ResourceKey<KanjiType>> = TYPE
}
