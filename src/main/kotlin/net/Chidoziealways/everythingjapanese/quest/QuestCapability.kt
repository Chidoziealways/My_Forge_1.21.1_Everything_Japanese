package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.quest.events.ClientQuestFinishedEvent
import net.Chidoziealways.everythingjapanese.quest.events.ClientQuestStartedEvent
import net.Chidoziealways.everythingjapanese.quest.events.QuestFinishedEvent
import net.Chidoziealways.everythingjapanese.quest.events.QuestStartedEvent
import net.Chidoziealways.everythingjapanese.toast.QuestToast
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.common.NeoForge

class QuestCapability(
    private var questProgress: MutableMap<ResourceLocation, QuestProgress> = mutableMapOf()
) : IQuestCapability {
    override var currentQuestId: ResourceLocation? = null
    override var completedQuests: MutableSet<ResourceLocation> = mutableSetOf()

    override fun giveQuest(id: ResourceLocation, player: ServerPlayer): Boolean {
        val quest = QUEST_LOOKUP[id] ?: return false //Not found

        if (quest.dependency.path != "air" && !completedQuests.contains(quest.dependency)) {
            player.sendSystemMessage(Component.literal("You haven't completed the dependency for this quest: ${quest.dependency.path}"))
            return false
        }

        if (!quest.isRepeatable && completedQuests.contains(id)) return false

        if (questProgress.containsKey(id)) return false //Already has it

        questProgress[id] = QuestProgress(id, 0, true) //initialize progress

        NeoForge.EVENT_BUS.post(QuestStartedEvent(player, quest))
        return true
    }

    override fun giveQuest(id: ResourceLocation, player: LocalPlayer): Boolean {
        val quest = QUEST_LOOKUP[id] ?: return false //Not found

        if (quest.dependency.path != "air" && !completedQuests.contains(quest.dependency)) {
            return false
        }

        if (!quest.isRepeatable && completedQuests.contains(id)) return false

        if (questProgress.containsKey(id)) return false //Already has it

        questProgress[id] = QuestProgress(id, 0, true)

        Minecraft.getInstance().toastManager.addToast(QuestToast(quest))

        NeoForge.EVENT_BUS.post(ClientQuestStartedEvent(player, quest))
        return true
    }

    override fun finishQuest(id: ResourceLocation, player: ServerPlayer): Boolean {
        val progress = questProgress[id] ?: return false
        val quest = progress.getQuest()

        questProgress[id]!!.isStarted = false
        questProgress.remove(id)
        if (currentQuestId == id) currentQuestId = null

        completedQuests.add(id)

        println("Finished Quest")

        NeoForge.EVENT_BUS.post(QuestFinishedEvent(player, progress.getQuest()))

        currentQuestId = null


        return true
    }

    override fun finishQuest(id: ResourceLocation, player: LocalPlayer): Boolean {
        val progress = questProgress[id] ?: return false
        val quest = progress.getQuest()

        questProgress[id]!!.isStarted = false
        questProgress.remove(id)
        if (currentQuestId == id) currentQuestId = null

        completedQuests.add(id)

        println("Finished Quest(Client Side)")

        NeoForge.EVENT_BUS.post(ClientQuestFinishedEvent(player, quest))

        currentQuestId = null

        return true
    }

    override fun getQuest(): Quest? {
        return QUEST_LOOKUP[currentQuestId]
    }

    override fun setQuest(id: ResourceLocation) {
        currentQuestId = id
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
                completedQuests = pair.first.completedQuests
            }
    }

    companion object {
        var QUEST_LOOKUP: MutableMap<ResourceLocation, Quest> = mutableMapOf()

        val CODEC: Codec<QuestCapability> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.unboundedMap(ResourceLocation.CODEC, QuestProgress.CODEC)
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
