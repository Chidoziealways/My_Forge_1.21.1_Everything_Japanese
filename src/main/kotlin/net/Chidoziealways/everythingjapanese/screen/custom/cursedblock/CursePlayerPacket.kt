package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.neoforged.neoforge.network.handling.IPayloadContext

class CursePlayerPacket: CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "curse_select_curse")
        val TYPE = CustomPacketPayload.Type<CursePlayerPacket>(ID)

        val CODEC: StreamCodec<RegistryFriendlyByteBuf, CursePlayerPacket> = StreamCodec.unit(CursePlayerPacket())

        fun handle(packet: CursePlayerPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player()
                val menu = player.containerMenu
                if (menu is CursedBlockMenu) {
                    menu.curse()
                    menu.broadcastChanges()
                }
            }
        }
    }
}