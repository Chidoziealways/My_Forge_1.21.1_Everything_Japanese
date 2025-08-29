package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.Chidoziealways.everythingjapanese.quest.events.StageFinishedEvent
import net.Chidoziealways.everythingjapanese.quest.events.StageStartedEvent
import net.Chidoziealways.everythingjapanese.quest.packets.FinishQuestPacket
import net.Chidoziealways.everythingjapanese.quest.packets.UpdateStagePacket
import net.minecraft.client.Minecraft
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.tick.ServerTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object QuestStageProgressionHandler {
    @SubscribeEvent
    fun onServerTick(event: ServerTickEvent.Post) {
        for (player in event.server.playerList.players) {
            val cap = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
            val currentQuestId = cap?.currentQuestId ?: return
            val progress = cap.getQuestProgress()[currentQuestId] ?: return

            val quest = progress.getQuest()
            if (progress.stageIndex >= quest.stages.size) return

            val stage = quest.stages[progress.stageIndex]

            val parsed = ParsedCondition.parse(stage.completeWhen)
            val condition = QuestConditionRegistry.get(parsed.type)

            if (condition?.isComplete(player, quest, stage) == true) {
                NeoForge.EVENT_BUS.post(StageFinishedEvent(player, quest, stage))
                progress.stageIndex++
                PacketDistributor.sendToPlayer(player, UpdateStagePacket(progress.stageIndex))

                // Check if there's a next stage
                if (progress.stageIndex < quest.stages.size) {
                    val nextStage = quest.stages[progress.stageIndex]
                    NeoForge.EVENT_BUS.post(StageStartedEvent(player, quest, nextStage))
                } else {
                    // Quest finished
                    cap.finishQuest(currentQuestId, player)
                    PacketDistributor.sendToPlayer(player, FinishQuestPacket(currentQuestId))
                }
            }
        }
    }
}