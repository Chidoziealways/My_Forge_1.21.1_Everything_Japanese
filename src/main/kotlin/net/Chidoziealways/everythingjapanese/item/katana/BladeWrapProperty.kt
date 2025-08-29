package net.Chidoziealways.everythingjapanese.item.katana

import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class BladeWrapProperty : SelectItemModelProperty<Pair<BladeType, Wrapping>> {
    companion object {
        val VALUE_CODEC: Codec<Pair<BladeType, Wrapping>> = Codec.STRING.xmap(
            { s: String ->
                val parts = s.split("_")
                val blade = BladeType.entries.find { it.id == parts.getOrNull(0) } ?: BladeType.STEEL
                val wrap = Wrapping.entries.find { it.id == parts.getOrNull(1) } ?: Wrapping.WHITE
                blade to wrap
            },
            { (blade, wrap) -> "${blade.id}_${wrap.id}" }
        )


        val TYPE = SelectItemModelProperty.Type.create(MapCodec.unit(BladeWrapProperty()), VALUE_CODEC)
    }

    override fun get(
        stack: ItemStack,
        level: ClientLevel?,
        entity: LivingEntity?,
        seed: Int,
        displayContext: ItemDisplayContext
    ): Pair<BladeType, Wrapping>? {
        val blade = stack.get(ModDataComponentTypes.BLADE.get()) ?: BladeType.STEEL
        val wrap = stack.get(ModDataComponentTypes.WRAPPING.get()) ?: Wrapping.WHITE
        return blade to wrap
    }

    override fun valueCodec(): Codec<Pair<BladeType, Wrapping>> = VALUE_CODEC

    override fun type(): SelectItemModelProperty.Type<out SelectItemModelProperty<Pair<BladeType, Wrapping>>, Pair<BladeType, Wrapping>>
            = TYPE
}
