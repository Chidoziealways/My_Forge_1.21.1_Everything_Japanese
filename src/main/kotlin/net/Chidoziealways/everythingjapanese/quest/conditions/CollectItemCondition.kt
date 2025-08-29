package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.*
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.UUID

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object CollectItemCondition : QuestCondition {

    private val collected: MutableMap<UUID, MutableMap<Pair<ResourceLocation, Int>, MutableSet<Item>>> = mutableMapOf()

    @SubscribeEvent
    fun onItemPickup(event: ItemEntityPickupEvent.Post) {
        val player = event.player
        if (player is ServerPlayer) {
            val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
            val quest = quests?.getQuest() ?: return
            val progress = quests.getQuestProgress()[quest.id] ?: return

            if (progress.isStarted) {
                println("Quest Started, Progress Started")

                val item = event.originalStack.item
                println("Picked up Item: ${item.name}")
                val key = quest.id to progress.stageIndex
                println("Picked up Key: $key")

                val playerId = player.uuid
                println("uuid: $playerId")
                val playerMap = collected.computeIfAbsent(playerId) { mutableMapOf() }
                val itemSet = playerMap.computeIfAbsent(key) { mutableSetOf() }
                itemSet.add(item)
                println("Added Item")
            }
        }
    }


    override fun isComplete(
        player: ServerPlayer,
        quest: Quest,
        stage: QuestStage
    ): Boolean {
        val parsed = ParsedCondition.parse(stage.completeWhen)
        val itemId = parsed.args.firstOrNull() as? String ?: run {
            println("No ItemId found for quest ${quest.id}")
            return false
        }

        val itemResource = ResourceLocation.tryParse(itemId)
        val item = itemResource?.let { BuiltInRegistries.ITEM.getValue(it) } ?: return false

        val playerId = player.uuid

        val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY) ?: return false

        val progress = quests.getQuestProgress()[quest.id] ?: return false

        val key = quest.id to progress.stageIndex
        println("IsComplete Item: ${item.name}")
        println("IsComplete Key: $key")
        return if(collected[playerId]?.get(key)?.contains(item) == true) {
            collected[playerId]?.remove(key)
            println("Matches")
            true
        } else {
            println("Does not Match.")
            false
        }
    }

}
