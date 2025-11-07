package net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation

data class Design(val location: ResourceLocation) {
    override fun toString(): String {
        return location.toString()
    }

    companion object {
        val CODEC: Codec<Design> = RecordCodecBuilder.create{ instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("location").forGetter { it.location }
            ).apply(instance, ::Design)
        }

        val STREAM_CODEC: StreamCodec<FriendlyByteBuf, Design> = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, Design::location,
            ::Design
        )
    }
}
