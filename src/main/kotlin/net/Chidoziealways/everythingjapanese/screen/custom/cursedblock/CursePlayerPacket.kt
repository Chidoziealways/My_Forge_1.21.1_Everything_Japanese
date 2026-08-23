package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.curse.CurseDialogManager
import net.Chidoziealways.everythingjapanese.dialog.JModDialog
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.server.dialog.Dialog
import net.minecraft.server.dialog.Dialogs
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.client.network.ClientPacketDistributor
import net.neoforged.neoforge.network.handling.IPayloadContext

data class CursePlayerPacket(val playerName: String, val curseID: Identifier): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val ID = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "curse_player")
        val TYPE = CustomPacketPayload.Type<CursePlayerPacket>(ID)

        val CODEC: StreamCodec<RegistryFriendlyByteBuf, CursePlayerPacket> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, {it.playerName},
            Identifier.STREAM_CODEC, {it.curseID},
            ::CursePlayerPacket
        )

        fun handle(packet: CursePlayerPacket, context: IPayloadContext) {
            context.enqueueWork {
                println("Packet Handling!")
                val player = context.player() as? ServerPlayer ?: return@enqueueWork
                println("Gotten Player!")
                val level = player.level()
                val dialog = player.registryAccess().lookupOrThrow(Registries.DIALOG).getOrThrow(JModDialog.CURSE_CONFIRM_DIALOG)
                level.players().firstOrNull {it.plainTextName.equals(packet.playerName, true)} ?: return@enqueueWork
                println("To Curse Player exists!")
                CurseDialogManager.add(
                    player,
                    CurseDialogManager.PendingCurse(
                        packet.playerName,
                        packet.curseID
                    )
                )
                player.openDialog(dialog)
                println("Opened Dialog!")
            }
        }
    }
}