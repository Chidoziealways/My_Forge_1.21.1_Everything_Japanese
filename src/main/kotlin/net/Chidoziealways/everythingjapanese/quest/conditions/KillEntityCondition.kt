package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.*
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.registries.ForgeRegistries

object KillEntityCondition : QuestCondition {
    private val killTracker = mutableMapOf<ServerPlayer, MutableMap<Pair<ResourceLocation, Int>, MutableSet<EntityType<*>>>>()

    fun onEntityKilled(event: LivingDeathEvent) {
        val killer = event.source.entity
        if (killer is ServerPlayer) {
            println("Kill Entity: Player is a ServerPlayer")
            killer.getCapability(ModCapabilities.QUEST_CAPABILITY)
                .ifPresent { iQuestCapability ->
                    println("Kill Entity: Player has capability")
                    val quest = iQuestCapability.getQuest() ?: return@ifPresent
                    val progress = iQuestCapability.getQuestProgress()[quest.id] ?: return@ifPresent

                    if (progress.isStarted) {
                        println("Kill Entity: Quest Started")
                        val type = event.entity.type
                        val key = quest.id to progress.stageIndex

                        println("Kill Entity: Id: ${quest.id} Index: ${progress.stageIndex}, Key: $key")

                        val playerKills = killTracker.computeIfAbsent(killer) { mutableMapOf() }
                        val mobSet = playerKills.computeIfAbsent(key) { mutableSetOf() }
                        println("Kill Entity: Adding MobType: $type to MobSet")
                        mobSet.add(type)
                        println("Kill Entity: MobSet after adding: $mobSet")
                    }
                }
        }
    }

    override fun isComplete(player: ServerPlayer, quest: Quest, stage: QuestStage): Boolean {
        val mobId = ParsedCondition.parse(stage.completeWhen).args.firstOrNull() ?: return false
        val mobType = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.parse(mobId)) ?: return false
        val key = quest.id to quest.stages.indexOf(stage)
        return killTracker[player]?.get(key)?.contains(mobType) == true
    }
}
