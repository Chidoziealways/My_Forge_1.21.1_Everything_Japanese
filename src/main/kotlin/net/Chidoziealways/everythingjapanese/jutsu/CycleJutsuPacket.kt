package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.network.CustomPayloadEvent

class CycleJutsuPacket {
    fun encode(buf: FriendlyByteBuf?) {
    }

    companion object {
        @JvmStatic
        fun decode(buf: FriendlyByteBuf?): CycleJutsuPacket {
            return CycleJutsuPacket()
        }

        @JvmStatic
        fun handle(msg: CycleJutsuPacket?, ctx: CustomPayloadEvent.Context?) {
            ctx!!.enqueueWork(Runnable {
                val player = ctx.getSender()
                if (player != null) {
                    player.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
                        .ifPresent(NonNullConsumer { iJutsuCapability: IJutsuCapability? ->
                            iJutsuCapability!!.cycleJutsu()
                            player.displayClientMessage(
                                Component.literal("Selected Jutsu: " + iJutsuCapability.getSelectedJutsu()),
                                true
                            )
                        })
                }
            })
            ctx.setPacketHandled(true)
        }
    }
}
