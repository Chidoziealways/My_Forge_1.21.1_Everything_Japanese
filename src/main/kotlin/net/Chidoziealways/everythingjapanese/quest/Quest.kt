package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

data class Quest(
    val id: ResourceLocation,
    val title: Component,
    val description: Component,
    val stages: List<QuestStage>,
    val rewards: List<QuestReward>,
    val isRepeatable: Boolean
) {

    companion object {

        val QUEST_CODEC: Codec<Quest> = RecordCodecBuilder.create { instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("id").forGetter { it.id },
                ComponentSerialization.CODEC.fieldOf("title").forGetter { it.title },
                ComponentSerialization.CODEC.fieldOf("description").forGetter { it.description },
                QuestStage.CODEC.listOf().fieldOf("stages").forGetter { it.stages },
                QuestReward.CODEC.listOf().fieldOf("rewards").forGetter { it.rewards },
                Codec.BOOL.optionalFieldOf("repeatable", false).forGetter { it.isRepeatable }
            ).apply(instance, ::Quest)
        }
    }
}