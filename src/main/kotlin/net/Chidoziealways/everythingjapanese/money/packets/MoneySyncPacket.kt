package net.Chidoziealways.everythingjapanese.money.packets

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class MoneySyncPacket(val balance: Int): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "money_sync_entity")
        val TYPE = CustomPacketPayload.Type<MoneySyncPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, MoneySyncPacket> = StreamCodec.of(
            { buf, value -> buf.writeInt(value.balance)},
            {buf -> MoneySyncPacket(buf.readInt())}
        )

        fun handle(packet: MoneySyncPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player ?: return@enqueueWork
                val money = player.getCapability(ModCapabilities.MONEY_CAPABILITY_ENTITY)
                money?.setMoney(packet.balance)
            }
        }
    }
}