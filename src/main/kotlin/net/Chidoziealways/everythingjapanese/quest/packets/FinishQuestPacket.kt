package net.Chidoziealways.everythingjapanese.quest.packets

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class FinishQuestPacket(val quest: ResourceLocation): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "quest_finish")
        val TYPE = CustomPacketPayload.Type<FinishQuestPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, FinishQuestPacket> = StreamCodec.of(
            { buf, value -> buf.writeResourceLocation(value.quest) },
            { buf -> FinishQuestPacket(buf.readResourceLocation()) }
        )

        fun handle(packet: FinishQuestPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                val quests = player?.getCapability(ModCapabilities.QUEST_CAPABILITY)
                quests?.finishQuest(packet.quest, player)
            }
        }
    }
}