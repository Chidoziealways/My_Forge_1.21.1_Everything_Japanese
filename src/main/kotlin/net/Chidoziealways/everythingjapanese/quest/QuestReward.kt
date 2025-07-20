package net.Chidoziealways.everythingjapanese.quest

import com.google.gson.JsonElement
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.codec.JSON_ELEMENT_CODEC

data class QuestReward(
    val type: String, // "item", "xp", "jutsu", etc.
    val data: JsonElement
) {
    companion object {
        val CODEC: Codec<QuestReward>
            get() = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("type").forGetter { it.type },
                    JSON_ELEMENT_CODEC.fieldOf("data").forGetter { it.data }
                ).apply(instance) {
                    type, data ->
                    QuestReward(type, data)
                }
            }
    }
}

