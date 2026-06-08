package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.network.handling.IPayloadContext

data class SetPlayerNamePacket(val playerName: String): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "curse_player_name")
        val TYPE = CustomPacketPayload.Type<SetPlayerNamePacket>(ID)

        val CODEC: StreamCodec<RegistryFriendlyByteBuf, SetPlayerNamePacket> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, { it.playerName },
            ::SetPlayerNamePacket
        )

        fun handle(packet: SetPlayerNamePacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player()
                val menu = player.containerMenu
                if (menu is CursedBlockMenu) {
                    menu.setPlayerName(packet.playerName) // will rebuild resultContainer on server
                    menu.broadcastChanges()
                }
            }
        }
    }
}