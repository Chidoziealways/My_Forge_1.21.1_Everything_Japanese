package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.network.CustomPayloadEvent
import net.minecraftforge.network.PacketDistributor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class JutsuCastPacket {
    fun encode(buf: FriendlyByteBuf?) {
    }

    companion object {
        private val log: Logger = LogManager.getLogger(JutsuCastPacket::class.java)

        @JvmStatic
        fun decode(buf: FriendlyByteBuf?): JutsuCastPacket {
            return JutsuCastPacket()
        }

        @JvmStatic
        fun handle(packet: JutsuCastPacket?, context: CustomPayloadEvent.Context?) {
            context!!.enqueueWork(Runnable {
                val player = context.getSender()
                if (player != null) {
                    player.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
                        .ifPresent(NonNullConsumer { iJutsuCapability: IJutsuCapability? ->
                            val jutsuPath = iJutsuCapability!!.getSelectedJutsu()
                            val jutsuId = ResourceLocation.fromNamespaceAndPath(MOD_ID, jutsuPath)
                            if (iJutsuCapability.hasLearnedJutsu(jutsuId.getPath())) {
                                val jutsu = ModJutsus.getJutsu(jutsuId)
                                log.debug(
                                    "Learnt Jutsu: {}, Selected Jutsu: {}",
                                    iJutsuCapability.getLearnedJutsus(),
                                    iJutsuCapability.getSelectedJutsu()
                                )
                                if (jutsu != null) {
                                    player.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
                                        .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                                            val cost = jutsu.getChakraCost()
                                            if (iChakra!!.chakra >= cost) {
                                                iChakra.subtractChakra(cost)
                                                jutsu.cast(player)

                                                ModNetwork.CHANNEL.send(
                                                    ChakraSyncPacket(iChakra.chakra, iChakra.getMaxChakra()),
                                                    PacketDistributor.PLAYER.with(player)
                                                )
                                            } else {
                                                player.sendSystemMessage(Component.literal("Not Enough Chakra to Cast this Jutsu!"))
                                            }
                                        })
                                }
                            } else {
                                player.sendSystemMessage(Component.literal("You haven't learned this Jutsu Yet!!"))
                            }
                        })
                }
            })
            context.setPacketHandled(true)
        }
    }
}
