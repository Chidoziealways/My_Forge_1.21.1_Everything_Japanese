package net.Chidoziealways.everythingjapanese.quest.packets

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class StartQuestPacket(val quest: ResourceLocation): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "quest_start")
        val TYPE = CustomPacketPayload.Type<StartQuestPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, StartQuestPacket> =
            StreamCodec.of(
                { buf, msg -> buf.writeResourceLocation(msg.quest) },
                { buf -> StartQuestPacket(buf.readResourceLocation()) }
            )


        fun handle(packet: StartQuestPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                val quests = player?.getCapability(ModCapabilities.QUEST_CAPABILITY)
                quests?.giveQuest(packet.quest, player)
            }
        }
    }
}