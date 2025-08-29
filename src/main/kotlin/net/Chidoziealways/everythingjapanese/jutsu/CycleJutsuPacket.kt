package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.handling.IPayloadContext

object CycleJutsuPacket: CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> = TYPE

        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu_cycle")
        val TYPE = CustomPacketPayload.Type<CycleJutsuPacket>(ID)

        val CODEC: StreamCodec<FriendlyByteBuf, CycleJutsuPacket> = StreamCodec.unit(CycleJutsuPacket)

        fun handle(msg: CycleJutsuPacket?, ctx: IPayloadContext) {
            ctx.enqueueWork(Runnable {
                val player = ctx.player()
                if (player is ServerPlayer) {
                    val jutsu = player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
                    jutsu!!.cycleJutsu(player)
                    player.displayClientMessage(
                        Component.literal("Selected Jutsu: " + jutsu.getSelectedJutsu()),
                        true
                    )
                }
            })
        }
    }
