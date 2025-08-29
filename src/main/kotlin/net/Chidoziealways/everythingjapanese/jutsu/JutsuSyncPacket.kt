package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.readSet
import net.Chidoziealways.everythingjapanese.writeSet
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.network.handling.IPayloadContext

data class JutsuSyncPacket(val learnedJutsus: MutableSet<String>, val jutsuMastery: MutableMap<String, Float>, val selectedJutsu: String): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu_sync")
        val TYPE = CustomPacketPayload.Type<JutsuSyncPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, JutsuSyncPacket> = StreamCodec.of(
            { buf, value ->
                buf.writeSet(value.learnedJutsus) {buf, string -> buf.writeUtf(string)}
                buf.writeMap<String, Float>(value.jutsuMastery, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeFloat)
                buf.writeUtf(value.selectedJutsu)
            },
            { buf -> JutsuSyncPacket(buf.readSet{ buf -> buf.readUtf() }, buf.readMap(FriendlyByteBuf::readUtf,
                FriendlyByteBuf::readFloat), buf.readUtf()) }
        )

        fun handle(packet: JutsuSyncPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = Minecraft.getInstance().player

                val jutsu = player?.getCapability(ModCapabilities.JUTSU_CAPABILITY)
                jutsu?.setLearnedJutsus(packet.learnedJutsus)
                jutsu?.setMastery(packet.jutsuMastery)
                jutsu?.setSelectedJutsu(packet.selectedJutsu)
            }
        }
    }
}