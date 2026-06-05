package net.Chidoziealways.everythingjapanese.karma

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket
import net.Chidoziealways.everythingjapanese.chakra.IncreaseChakraPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.neoforged.neoforge.network.handling.IPayloadContext

data class KarmaSync(val karmaAmount: Int): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "karma_sync")
        val TYPE = CustomPacketPayload.Type<KarmaSync>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, KarmaSync> =
            StreamCodec.of(
                { buf, value ->
                    buf.writeInt(value.karmaAmount)
                },
                { buf -> KarmaSync(buf.readInt()) }
            )

        fun handle(packet: KarmaSync, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player ?: return@enqueueWork
                val karma = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return@enqueueWork
                karma.setKarmaAmount(packet.karmaAmount)
            }
        }
    }
}