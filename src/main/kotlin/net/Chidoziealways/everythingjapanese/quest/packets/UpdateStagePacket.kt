package net.Chidoziealways.everythingjapanese.quest.packets

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class UpdateStagePacket(val index: Int): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "stage_update")
        val TYPE = CustomPacketPayload.Type<UpdateStagePacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, UpdateStagePacket> = StreamCodec.of(
            { buf, msg -> buf.writeInt(msg.index) },
            { buf -> UpdateStagePacket(buf.readInt()) }
        )

        fun handle(packet: UpdateStagePacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                println("Calling UpdateStagePacket")

                val quests = player?.getCapability(ModCapabilities.QUEST_CAPABILITY)
                quests?.getQuestProgress()[quests.currentQuestId]?.stageIndex = packet.index
            }
        }
    }
}