package net.Chidoziealways.everythingjapanese.stamina.packets

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class StaminaIncreasePacket(val amount: Float): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "stamina_increase")
        val TYPE = CustomPacketPayload.Type<StaminaIncreasePacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, StaminaIncreasePacket> = StreamCodec.of(
            { buf, value ->
                buf.writeFloat(value.amount)
            },
            { buf -> StaminaIncreasePacket(buf.readFloat()) }
        )

        fun handle(packet: StaminaIncreasePacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                val stamina = player?.getCapability(ModCapabilities.STAMINA_CAPABILITY)
                stamina?.increaseStamina(packet.amount, player)
            }
        }
    }
}
