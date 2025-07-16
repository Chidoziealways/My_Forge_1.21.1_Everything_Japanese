package net.Chidoziealways.everythingjapanese.network

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.network.CustomPayloadEvent
import net.minecraftforge.network.Channel.VersionTest
import net.minecraftforge.network.ChannelBuilder
import net.minecraftforge.network.NetworkDirection
import net.minecraftforge.network.SimpleChannel
import java.util.function.BiConsumer
import java.util.function.Function

object ModNetwork {
    private const val PROTOCOL_VERSION = 1

    val CHANNEL: SimpleChannel = ChannelBuilder
        .named(ResourceLocation.fromNamespaceAndPath(MOD_ID, "network"))
        .clientAcceptedVersions(VersionTest { status: VersionTest.Status?, version: Int -> true })
        .serverAcceptedVersions(VersionTest { status: VersionTest.Status?, version: Int -> true })
        .networkProtocolVersion(PROTOCOL_VERSION)
        .simpleChannel()

    fun registerPackets() {
        var id = 0

        CHANNEL.messageBuilder<ChakraSyncPacket?, RegistryFriendlyByteBuf?>(
            ChakraSyncPacket::class.java,
            id++,
            NetworkDirection.PLAY_TO_CLIENT
        )
            .encoder(BiConsumer { obj: ChakraSyncPacket?, msg: RegistryFriendlyByteBuf? ->
                ChakraSyncPacket.Companion.encode(
                    obj,
                    msg
                )
            })
            .decoder(Function { obj: RegistryFriendlyByteBuf? -> ChakraSyncPacket.Companion.decode(obj) })
            .consumer(BiConsumer { chakraSyncPacket: ChakraSyncPacket?, context: CustomPayloadEvent.Context? ->
                context!!.enqueueWork(Runnable {
                    val mc = Minecraft.getInstance()
                    checkNotNull(mc.player)
                    mc.player!!.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY!!)
                        .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                            iChakra!!.chakra = chakraSyncPacket!!.chakra
                        })
                })
                context.setPacketHandled(true)
            })
            .add()

        CHANNEL.messageBuilder<JutsuCastPacket?, RegistryFriendlyByteBuf?>(
            JutsuCastPacket::class.java,
            id++,
            NetworkDirection.PLAY_TO_SERVER
        )
            .encoder { obj: JutsuCastPacket?, buf: RegistryFriendlyByteBuf? -> obj!!.encode(buf) }
            .decoder { obj: RegistryFriendlyByteBuf? -> JutsuCastPacket.Companion.decode(obj) }
            .consumer { obj: JutsuCastPacket?, packet: CustomPayloadEvent.Context? ->
                JutsuCastPacket.Companion.handle(
                    obj,
                    packet
                )
            }
            .add()

        CHANNEL.messageBuilder<CycleJutsuPacket?, RegistryFriendlyByteBuf?>(
            CycleJutsuPacket::class.java,
            id++,
            NetworkDirection.PLAY_TO_SERVER
        )
            .encoder(BiConsumer { obj: CycleJutsuPacket?, buf: RegistryFriendlyByteBuf? -> obj!!.encode(buf) })
            .decoder(Function { obj: RegistryFriendlyByteBuf? -> CycleJutsuPacket.Companion.decode(obj) })
            .consumer(BiConsumer { obj: CycleJutsuPacket?, msg: CustomPayloadEvent.Context? ->
                CycleJutsuPacket.Companion.handle(
                    obj,
                    msg
                )
            })
            .add()
    }
}
