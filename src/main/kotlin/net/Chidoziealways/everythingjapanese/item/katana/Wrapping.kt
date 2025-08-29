package net.Chidoziealways.everythingjapanese.item.katana

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation

enum class Wrapping(val id: String, val speedBonus: Float, val overlay: ResourceLocation) {
    WHITE("white", 0.0f, ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/wrap_white")),
    RED("red", 0.2f, ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/wrap_red")),
    BLACK("black", -0.1f, ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/wrap_black"));

    companion object {
        val CODEC: Codec<Wrapping> = Codec.STRING.xmap(
            { id -> entries.find { it.id == id } ?: WHITE },
            { it.id }
        )

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, Wrapping> = StreamCodec.of(
            { buf, wrap -> buf.writeUtf(wrap.id) },
            { buf -> entries.find { it.id == buf.readUtf() } ?: WHITE }
        )
    }
}
