package net.Chidoziealways.everythingjapanese.event

import com.mojang.serialization.JsonOps
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.events.ClientQuestStartedEvent
import net.Chidoziealways.everythingjapanese.quest.events.ClientStageStartedEvent
import net.Chidoziealways.everythingjapanese.quest.events.QuestFinishedEvent
import net.Chidoziealways.everythingjapanese.quest.events.QuestStartedEvent
import net.Chidoziealways.everythingjapanese.quest.events.StageFinishedEvent
import net.Chidoziealways.everythingjapanese.quest.events.StageStartedEvent
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.common.NeoForge
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object QuestEvents {
    @SubscribeEvent
    fun onQuestStarted(event: QuestStartedEvent) {
        val quest = event.quest
        val player = event.player

        val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
        quests?.setQuest(quest.id) // Assuming quest.id is a ResourceLocation
        if (quest.stages.isNotEmpty()) {
            val stage = quest.stages[0]
            NeoForge.EVENT_BUS.post(StageStartedEvent(player, quest, stage))
        }
        player.displayClientMessage(Component.literal("Started Quest"), true)
        player.displayClientMessage(quest.title, true)
        player.displayClientMessage(quest.description, true)
    }

    @SubscribeEvent
    fun onClientQuestStarted(event: ClientQuestStartedEvent) {
        val quest = event.quest
        val player = event.player

        val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
        quests?.setQuest(quest.id)
        if (quest.stages.isNotEmpty()) {
            val stage = quest.stages[0]
            NeoForge.EVENT_BUS.post(ClientStageStartedEvent(player, quest, stage))
        }
    }

    @SubscribeEvent
    fun onQuestFinished(event: QuestFinishedEvent) {
        val quest = event.quest
        val player = event.player
        player.sendSystemMessage(Component.literal("§bQuest Complete: ${quest.title.string}"))

        for (reward in quest.rewards) {
            when (reward.type) {
                "item" -> {
                    val json = reward.data
                    try {
                        val itemStack = ItemStack.CODEC.parse(JsonOps.INSTANCE, json)
                            .resultOrPartial { msg -> println("Failed to parse item: $msg") }
                            .orElse(null)

                        if (itemStack != null) {
                            player.addItem(itemStack)
                        }
                    } catch (e: Exception) {
                        println("Error while giving item reward: ${e.message}")
                    }
                }
                "xp" -> {
                    val json = reward.data.asJsonObject
                    val xpAmount = json.get("count")?.asInt ?: 0
                    player.giveExperiencePoints(xpAmount)
                }
                else -> {
                    println("Unknown reward type: ${reward.type}")
                }
            }
        }
    }

    @SubscribeEvent
    fun onStageStarted(event: StageStartedEvent) {
        val player = event.player
        val stage = event.stage
        val quest = event.quest

        player.displayClientMessage(Component.literal("Stage Started: ${stage.objective.string} for Quest ${quest.title.string}"), true)
    }

    @SubscribeEvent
    fun onStageFinished(event: StageFinishedEvent) {
        val player = event.player
        val stage = event.stage
        val quest = event.quest

        player.sendSystemMessage(Component.literal("§aStage Complete: ${stage.objective.string} for Quest ${quest.title.string}"))
    }
}