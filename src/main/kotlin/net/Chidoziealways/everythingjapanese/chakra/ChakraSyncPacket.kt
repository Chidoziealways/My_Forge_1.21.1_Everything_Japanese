package net.Chidoziealways.everythingjapanese.chakra

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier

data class ChakraSyncPacket(@JvmField val chakra: Float, val maxChakra: Int): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return TYPE
    }

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chakra_sync")
        val TYPE = CustomPacketPayload.Type<ChakraSyncPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, ChakraSyncPacket> =
            StreamCodec.of(
                { buf, value ->
                    buf.writeFloat(value.chakra)
                    buf.writeInt(value.maxChakra)
                },
                { buf -> ChakraSyncPacket(buf.readFloat(), buf.readInt()) }
            )
    }
}
