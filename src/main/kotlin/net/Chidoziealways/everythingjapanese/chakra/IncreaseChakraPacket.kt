package net.Chidoziealways.everythingjapanese.chakra

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.handling.IPayloadContext

data class IncreaseChakraPacket(val toBeAddedChakra: Float): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "increase_chakra")
        val TYPE = CustomPacketPayload.Type<IncreaseChakraPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, IncreaseChakraPacket> = StreamCodec.of(
            { buf, value -> buf.writeFloat(value.toBeAddedChakra) },
            { buf -> IncreaseChakraPacket(buf.readFloat()) }
        )

        fun handle(packet: IncreaseChakraPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player() as ServerPlayer
                val chakra = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                chakra?.addChakra(packet.toBeAddedChakra, player)
            }
        }
    }
}
