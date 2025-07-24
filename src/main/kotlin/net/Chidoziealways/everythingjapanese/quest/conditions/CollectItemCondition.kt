package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.registries.ForgeRegistries

object CollectItemCondition : QuestCondition {

    private val collected: MutableMap<ServerPlayer, MutableMap<Pair<ResourceLocation, Int>, MutableSet<Item>>> = mutableMapOf()

    fun onItemPickup(event: EntityItemPickupEvent) {
        val player = event.entity
        if (player is ServerPlayer) {
            println("Collect Item: Player is a ServerPlayer")
            player.getCapability(ModCapabilities.QUEST_CAPABILITY)
                .ifPresent { iQuestCapability ->
                    println("Collect Item: Player has capability")
                    val quest = iQuestCapability.getQuest() ?: return@ifPresent
                    val progress = iQuestCapability.getQuestProgress()[quest.id] ?: return@ifPresent

                    if (progress.isStarted) {
                        println("Collect Item: Quest Started")
                        val item = event.item.item.item
                        val key = quest.id to progress.stageIndex

                        println("Collect Item: Id: ${quest.id} Index: ${progress.stageIndex}, key: $key")

                        val playerMap = collected.computeIfAbsent(player) { mutableMapOf() }
                        val itemSet = playerMap.computeIfAbsent(key) { mutableSetOf() }
                        println("Collect Item: Adding Item: $item to ItemSet")
                        itemSet.add(item)
                        println("Collect Item: ItemSet after adding: $itemSet")
                    }
                }
        }
    }


    override fun isComplete(
        player: ServerPlayer,
        quest: Quest,
        stage: QuestStage
    ): Boolean {
        val parsed = ParsedCondition.parse(stage.completeWhen)
        val itemId = when (val args = parsed.args) {
            is List<*> -> args.firstOrNull() as? String
            is Map<*, *> -> args["item"] as? String
            else -> null
        } ?: return false

        val item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemId)) ?: return false
        val key = quest.id to quest.stages.indexOf(stage)

        return collected[player]?.get(key)?.contains(item) == true
    }
}
