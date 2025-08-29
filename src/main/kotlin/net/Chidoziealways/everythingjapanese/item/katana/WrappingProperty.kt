package net.Chidoziealways.everythingjapanese.item.katana

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class WrappingProperty: SelectItemModelProperty<Wrapping> {
    companion object {
        val CODEC = Wrapping.CODEC
        val TYPE = SelectItemModelProperty.Type.create(MapCodec.unit(WrappingProperty()), CODEC)
    }

    override fun get(
        stack: ItemStack,
        level: ClientLevel?,
        entity: LivingEntity?,
        seed: Int,
        displayContext: ItemDisplayContext
    ): Wrapping? {
        return stack.get(ModDataComponentTypes.WRAPPING.get())
    }

    override fun valueCodec(): Codec<Wrapping> = CODEC

    override fun type(): SelectItemModelProperty.Type<out SelectItemModelProperty<Wrapping>, Wrapping> = TYPE
}