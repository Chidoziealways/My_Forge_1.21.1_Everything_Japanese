package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation

data class QuestProgress(
    val quest: Quest,
    var stageIndex: Int,
    var isStarted: Boolean = false
) {
    val currentStage: QuestStage
        get() = quest.stages[stageIndex]

    fun advance() {
        if (stageIndex < quest.stages.lastIndex) {
            stageIndex++
        }
    }

    companion object {
        fun codec(questLookup: Map<ResourceLocation, Quest>): Codec<QuestProgress> {
            return RecordCodecBuilder.create { instance ->
                instance.group(
                    ResourceLocation.CODEC.fieldOf("quest_id").forGetter { it.quest.id },
                    Codec.INT.fieldOf("stage_index").forGetter { it.stageIndex },
                    Codec.BOOL.fieldOf("isStarted").forGetter { it.isStarted }
                ).apply(instance) { id, stage, start ->
                    val quest = questLookup[id] ?: error("Unknown quest: $id")
                    QuestProgress(quest, stage, start)
                }
            }
        }
    }
}
