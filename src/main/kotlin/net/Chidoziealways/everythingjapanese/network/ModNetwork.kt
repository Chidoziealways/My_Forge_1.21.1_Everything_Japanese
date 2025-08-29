package net.Chidoziealways.everythingjapanese.network

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraSyncPacket
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.chakra.IncreaseChakraPacket
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket
import net.Chidoziealways.everythingjapanese.jutsu.JutsuSyncPacket
import net.Chidoziealways.everythingjapanese.quest.packets.FinishQuestPacket
import net.Chidoziealways.everythingjapanese.quest.packets.StartQuestPacket
import net.Chidoziealways.everythingjapanese.quest.packets.UpdateStagePacket
import net.Chidoziealways.everythingjapanese.stamina.packets.StaminaDecreasePacket
import net.Chidoziealways.everythingjapanese.stamina.packets.StaminaIncreasePacket
import net.minecraft.client.Minecraft
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object ModNetwork {
    @SubscribeEvent
    fun registerPackets(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1.0.0")

        registrar.playToClient(ChakraSyncPacket.TYPE, ChakraSyncPacket.CODEC) { message, context ->
            context.enqueueWork {
                checkNotNull(Minecraft.getInstance().player)
                val chakra = Minecraft.getInstance().player!!.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
                chakra!!.setCurrentChakra(message.chakra)
                chakra.setMaxChakra(message.maxChakra)
            }
        }

        registrar.playToServer(JutsuCastPacket.TYPE, JutsuCastPacket.STREAM_CODEC) { message, context ->
            context.enqueueWork { JutsuCastPacket.handle(message, context) }
        }

        registrar.playToClient(StartQuestPacket.TYPE, StartQuestPacket.CODEC) { message, context ->
            context.enqueueWork {
                StartQuestPacket.handle(message, context)
            }
        }

        registrar.playToClient(FinishQuestPacket.TYPE, FinishQuestPacket.CODEC) { message, context ->
            context.enqueueWork {
                FinishQuestPacket.handle(message, context)
            }
        }

        registrar.playToClient(JutsuSyncPacket.TYPE, JutsuSyncPacket.CODEC) { message, context ->
            context.enqueueWork {
                JutsuSyncPacket.handle(message, context)
            }
        }

        registrar.playToClient(UpdateStagePacket.TYPE, UpdateStagePacket.CODEC) { message, context ->
            context.enqueueWork {
                UpdateStagePacket.handle(message, context)
            }
        }

        registrar.playToServer(CycleJutsuPacket.TYPE, CycleJutsuPacket.CODEC) { message, context ->
            context.enqueueWork {
                CycleJutsuPacket.handle(message, context)
            }
        }

        registrar.playToServer(IncreaseChakraPacket.TYPE, IncreaseChakraPacket.CODEC) { message, context -> context.enqueueWork {
                IncreaseChakraPacket.handle(message, context) }
        }

        registrar.playToClient(StaminaIncreasePacket.TYPE, StaminaIncreasePacket.CODEC) { message, context -> context.enqueueWork {
            StaminaIncreasePacket.handle(message, context)
        } }

        registrar.playToClient(StaminaDecreasePacket.TYPE, StaminaDecreasePacket.CODEC, StaminaDecreasePacket::handle)
    }
}
