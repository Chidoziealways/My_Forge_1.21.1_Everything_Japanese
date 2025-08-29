package net.Chidoziealways.everythingjapanese.item.katana

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class BladeTypeProperty: SelectItemModelProperty<BladeType> {
    companion object {
        val VALUE_CODEC = BladeType.CODEC
        val TYPE = SelectItemModelProperty.Type.create(MapCodec.unit(BladeTypeProperty()), VALUE_CODEC)
    }

    override fun get(
        stack: ItemStack,
        level: ClientLevel?,
        entity: LivingEntity?,
        seed: Int,
        displayContext: ItemDisplayContext
    ): BladeType? {
        return stack.get(ModDataComponentTypes.BLADE.get())
    }

    override fun valueCodec(): Codec<BladeType> = VALUE_CODEC

    override fun type(): SelectItemModelProperty.Type<out SelectItemModelProperty<BladeType>, BladeType> = TYPE
}
