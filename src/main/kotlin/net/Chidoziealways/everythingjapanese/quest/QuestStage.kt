package net.Chidoziealways.everythingjapanese.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization

data class QuestStage(
    val objective: Component,
    val completeWhen: String, // could be "kill:modid:entity", "collect:item", etc.
) {
    companion object {
        val CODEC: Codec<QuestStage>
            get() = RecordCodecBuilder.create { instance ->
                instance.group(
                    ComponentSerialization.CODEC.fieldOf("objective").forGetter { it.objective },
                    Codec.STRING.fieldOf("complete_when").forGetter { it.completeWhen }
                ).apply(instance, ::QuestStage)
            }
    }
}