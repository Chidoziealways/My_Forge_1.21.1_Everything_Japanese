package net.Chidoziealways.everythingjapanese.stamina.packets

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class StaminaDecreasePacket(val amount: Float): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "stamina_decrease")
        val TYPE = CustomPacketPayload.Type<StaminaDecreasePacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, StaminaDecreasePacket> = StreamCodec.of(
            { buf, value ->
                buf.writeFloat(value.amount)
            },
            { buf -> StaminaDecreasePacket(buf.readFloat()) }
        )

        fun handle(packet: StaminaDecreasePacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                val stamina = player?.getCapability(ModCapabilities.STAMINA_CAPABILITY)
                stamina?.decreaseStamina(packet.amount, player)
            }
        }
    }
}
