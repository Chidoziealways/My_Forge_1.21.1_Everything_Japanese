package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.chat.Component
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

object QuestStageProgressionHandler {
    fun onServerTick(event: TickEvent.ServerTickEvent.Post) {
        for (player in event.server.playerList.players) {
            player.getCapability(ModCapabilities.QUEST_CAPABILITY).ifPresent { cap ->
                val currentQuestId = cap.currentQuestId ?: return@ifPresent
                val progress = cap.getQuestProgress()[currentQuestId] ?: return@ifPresent

                val quest = progress.quest
                if (progress.stageIndex >= quest.stages.size) return@ifPresent

                val stage = quest.stages[progress.stageIndex]

                val parsed = ParsedCondition.parse(stage.completeWhen)
                val condition = QuestConditionRegistry.get(parsed.type)

                if (condition?.isComplete(player, quest, stage) == true) {
                    progress.stageIndex++

                    player.sendSystemMessage(Component.literal("§aStage Complete: ${stage.objective.string}"))

                    // Quest finished?
                    if (progress.stageIndex >= quest.stages.size) {
                        cap.finishQuest(currentQuestId, player)
                        player.sendSystemMessage(Component.literal("§bQuest Complete: ${quest.title.string}"))
                    }
                }
            }
        }

    }
}