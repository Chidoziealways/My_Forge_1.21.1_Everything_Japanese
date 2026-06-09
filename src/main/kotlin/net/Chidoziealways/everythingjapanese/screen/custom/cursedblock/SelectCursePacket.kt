package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.neoforged.neoforge.network.handling.IPayloadContext

data class SelectCursePacket(val curseID: Identifier): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "curse_select_curse")
        val TYPE = CustomPacketPayload.Type<SelectCursePacket>(ID)

        val CODEC: StreamCodec<RegistryFriendlyByteBuf, SelectCursePacket> = StreamCodec.composite(
            Identifier.STREAM_CODEC, { it.curseID },
            ::SelectCursePacket
        )

        fun handle(packet: SelectCursePacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player()
                val menu = player.containerMenu
                if (menu is CursedBlockMenu) {
                    val curse = ModRegistries.CURSES.getOptional(packet.curseID)
                    if (curse.isPresent) {
                        menu.setCurse(curse.get()) // will rebuild resultContainer on server
                        menu.broadcastChanges()
                    }
                }
            }
        }
    }
}