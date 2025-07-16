package net.Chidoziealways.everythingjapanese.chakra

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.network.CustomPayloadEvent

@JvmRecord
data class ChakraSyncPacket(@JvmField val chakra: Float, val maxChakra: Int) {
    companion object {
        @JvmStatic
        fun encode(msg: ChakraSyncPacket?, buffer: FriendlyByteBuf?) {
            buffer!!.writeFloat(msg!!.chakra)
            buffer.writeInt(msg.maxChakra)
        }

        @JvmStatic
        fun decode(buffer: FriendlyByteBuf?): ChakraSyncPacket {
            return ChakraSyncPacket(buffer!!.readFloat(), buffer.readInt())
        }

        fun handle(packet: ChakraSyncPacket, context: CustomPayloadEvent.Context) {
            context.enqueueWork(Runnable {
                checkNotNull(Minecraft.getInstance().player)
                Minecraft.getInstance().player!!.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
                    .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                        iChakra!!.chakra = packet.chakra
                        iChakra.setMaxChakra(packet.maxChakra)
                    })
            })
            context.setPacketHandled(true)
        }
    }
}
