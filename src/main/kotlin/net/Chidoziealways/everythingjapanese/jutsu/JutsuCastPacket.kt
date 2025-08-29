package net.Chidoziealways.everythingjapanese.jutsu

import kotlinx.coroutines.CompletableDeferred
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.handling.IPayloadContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object JutsuCastPacket: CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> {
        return TYPE
    }

        val ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu_cast")
        val TYPE = CustomPacketPayload.Type<JutsuCastPacket>(ID)

        val STREAM_CODEC: StreamCodec<FriendlyByteBuf, JutsuCastPacket> = StreamCodec.unit(JutsuCastPacket)

        val log: Logger = LogManager.getLogger(JutsuCastPacket::class.java)

        fun handle(packet: JutsuCastPacket?, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player() as ServerPlayer
                val jutsuCap = player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
                if (jutsuCap != null) {
                    println(jutsuCap)
                    val jutsuPath = jutsuCap.getSelectedJutsu()
                    val learnedJutsus = jutsuCap.getLearnedJutsus()
                    if (jutsuPath != "") {
                        val jutsuId = ResourceLocation.fromNamespaceAndPath(MOD_ID, jutsuPath)
                        if (jutsuCap.hasLearnedJutsu(jutsuId.path)) {
                            val jutsu = ModRegistries.JUTSU.getValue(jutsuId)
                            println("Jutsu learned: ${jutsuId.path}")
                            log.debug(
                                "Learnt Jutsu: {}, Selected Jutsu: {}",
                                jutsuCap.getLearnedJutsus(),
                                jutsuCap.getSelectedJutsu()
                            )
                            if (jutsu != null) {
                                val chakra = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                                val stamina = player.getCapability(ModCapabilities.STAMINA_CAPABILITY)
                                val cost = jutsu.getChakraCost()
                                if (chakra!!.getCurrentChakra() >= cost && stamina!!.getStamina() >= 5f) {
                                    if (jutsu.cast(player)) {
                                        if (!player.isCreative) {
                                            chakra.subtractChakra(cost, player)
                                            stamina.decreaseStamina(2F, player)
                                        }
                                    }
                                } else {
                                    player.sendSystemMessage(Component.literal("Not Enough Chakra or Stamina, or Both to Cast this Jutsu!"))
                                }
                            }
                        } else {
                            player.sendSystemMessage(Component.literal("You haven't learned this Jutsu Yet!!"))
                        }
                    } else if (learnedJutsus.isEmpty()) {
                        player.sendSystemMessage(Component.literal("You haven't learnt any jutsus"))
                    }
                }
            }
        }
    }
