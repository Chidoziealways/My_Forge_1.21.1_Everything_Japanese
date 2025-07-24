package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.event.QuestFinishedEvent
import net.Chidoziealways.everythingjapanese.event.QuestStartedEvent
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.bus.EventBus
import net.minecraftforge.eventbus.api.listener.EventListener
import net.minecraftforge.registries.DataPackRegistryEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class QuestCapability(
    private var questProgress: MutableMap<ResourceLocation, QuestProgress> = mutableMapOf()
) : IQuestCapability {
    override var currentQuestId: ResourceLocation? = null
    private var completedQuests: MutableSet<ResourceLocation> = mutableSetOf()

    override fun giveQuest(id: ResourceLocation, player: ServerPlayer): Boolean {
        val quest = QUEST_LOOKUP[id] ?: return false //Not found

        if (quest.dependency.path != "air" && !completedQuests.contains(quest.dependency)) {
            player.sendSystemMessage(Component.literal("You haven't completed the dependency for this quest: ${quest.dependency.path}"))
            return false
        }

        if (!quest.isRepeatable && completedQuests.contains(id)) return false

        if (questProgress.containsKey(id)) return false //Already has it

        questProgress[id] = QuestProgress(quest, 0, true) //initialize progress

        QuestStartedEvent.BUS.post(QuestStartedEvent(player, quest))
        return true
    }

    override fun getQuest(): Quest? {
        return QUEST_LOOKUP[currentQuestId]
    }

    override fun setQuest(id: ResourceLocation) {
        currentQuestId = id
    }

    override fun finishQuest(id: ResourceLocation, player: ServerPlayer): Boolean {
        val progress = questProgress[id] ?: return false
        val quest = progress.quest

        if (progress.stageIndex < quest.stages.lastIndex) return false

        questProgress.remove(id)
        if (currentQuestId == id) currentQuestId = null

        completedQuests.add(id)

        QuestFinishedEvent.BUS.post(QuestFinishedEvent(player, progress.quest))

        return true
    }

    override fun getQuestProgress(): Map<ResourceLocation, QuestProgress> = questProgress

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart(NbtOps.INSTANCE, this)
            .resultOrPartial { error -> System.err.println("Failed to serialize QuestCapability: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        CODEC.decode(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error -> System.err.println("Failed to deserialize QuestCapability: $error") }
            .ifPresent { pair ->
                this.questProgress = pair.first.getQuestProgress().toMutableMap()
            }
    }

    companion object {
        var QUEST_LOOKUP: MutableMap<ResourceLocation, Quest> = mutableMapOf() // You inject this globally

        val CODEC: Codec<QuestCapability> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.unboundedMap(ResourceLocation.CODEC, QuestProgress.codec(QUEST_LOOKUP))
                    .fieldOf("quests")
                    .forGetter { it.questProgress },
                ResourceLocation.CODEC.listOf()
                    .fieldOf("completed")
                    .orElse(emptyList())
                    .forGetter { it.completedQuests.toList() }
            ).apply(instance) { progressMap, completedList ->
                QuestCapability(progressMap.toMutableMap()).apply {
                    completedQuests.addAll(completedList)
                }
            }
        }
    }
}
