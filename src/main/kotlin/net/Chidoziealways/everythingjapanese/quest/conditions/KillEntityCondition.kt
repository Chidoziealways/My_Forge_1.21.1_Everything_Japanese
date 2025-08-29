package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.*
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object KillEntityCondition : QuestCondition {
    private val killTracker = mutableMapOf<ServerPlayer, MutableMap<Pair<ResourceLocation, Int>, MutableSet<EntityType<*>>>>()

    @SubscribeEvent
    fun onEntityKilled(event: LivingDeathEvent) {
        val killer = event.source.entity
        if (killer is ServerPlayer) {
            val quests = killer.getCapability(ModCapabilities.QUEST_CAPABILITY)
            val quest = quests?.getQuest() ?: return
            val progress = quests.getQuestProgress()[quest.id] ?: return

            if (progress.isStarted) {
                val type = event.entity.type
                val key = quest.id to progress.stageIndex

                val playerKills = killTracker.computeIfAbsent(killer) { mutableMapOf() }
                val mobSet = playerKills.computeIfAbsent(key) { mutableSetOf() }
                mobSet.add(type)
            }
        }
    }

    override fun isComplete(player: ServerPlayer, quest: Quest, stage: QuestStage): Boolean {
        val mobId = ParsedCondition.parse(stage.completeWhen).args.firstOrNull() ?: return false
        val mobType = BuiltInRegistries.ENTITY_TYPE.getValue(ResourceLocation.parse(mobId)) ?: return false
        val key = quest.id to quest.stages.indexOf(stage)
        return killTracker[player]?.get(key)?.contains(mobType) == true
    }
}
