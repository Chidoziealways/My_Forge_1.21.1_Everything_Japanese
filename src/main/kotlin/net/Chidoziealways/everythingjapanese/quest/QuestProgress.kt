package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation

data class QuestProgress(
    val questId: ResourceLocation,
    var stageIndex: Int,
    var isStarted: Boolean = false
) {
    fun getQuest(): Quest =
        QuestCapability.QUEST_LOOKUP[questId] ?: error("Quest not found: $questId")

    fun currentStage(): QuestStage =
        getQuest().stages[stageIndex]

    fun advance() {
        val quest = getQuest()
        if (stageIndex < quest.stages.lastIndex) {
            stageIndex++
        }
    }

    companion object {
        val CODEC: Codec<QuestProgress> = RecordCodecBuilder.create { instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("quest_id").forGetter { it.questId },
                Codec.INT.fieldOf("stage_index").forGetter { it.stageIndex },
                Codec.BOOL.fieldOf("isStarted").forGetter { it.isStarted }
            ).apply(instance, ::QuestProgress)
        }
    }
}
