package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable.CalligraphyTableMenu
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.network.handling.IPayloadContext

data class SetScrollTextPacket(val text: String, val stack: ItemStack): CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

    companion object {
        val ID = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "scroll_text")
        val TYPE = CustomPacketPayload.Type<SetScrollTextPacket>(ID)

        val CODEC: StreamCodec<RegistryFriendlyByteBuf, SetScrollTextPacket> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, { it.text },
            ItemStack.OPTIONAL_STREAM_CODEC, {it.stack},
            ::SetScrollTextPacket
        )

        fun handle(packet: SetScrollTextPacket, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player()
                val menu = player.containerMenu
                if (menu is CalligraphyTableMenu) {
                    menu.setItemText(packet.text) // will rebuild resultContainer on server
                    menu.broadcastChanges()
                }
            }
        }
    }
}