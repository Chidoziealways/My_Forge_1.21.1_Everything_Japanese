package net.Chidoziealways.everythingjapanese.event

import com.mojang.serialization.JsonOps
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object QuestEvents {
    @SubscribeEvent
    fun onQuestStarted(event: QuestStartedEvent) {
        val quest = event.quest
        val player = event.player

        player.getCapability(ModCapabilities.QUEST_CAPABILITY).ifPresent { cap ->
            cap.setQuest(quest.id) // Assuming quest.id is a ResourceLocation
        }

        player.displayClientMessage(Component.literal("Started Quest"), true)
        player.displayClientMessage(quest.title, true)
        player.displayClientMessage(quest.description, true)
    }

    @SubscribeEvent
    fun onQuestFinished(event: QuestFinishedEvent) {
        val quest = event.quest
        val player = event.player

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
}