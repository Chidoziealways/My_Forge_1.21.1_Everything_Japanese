package net.Chidoziealways.everythingjapanese.codec

import com.google.gson.JsonElement
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.Dynamic
import com.mojang.serialization.JsonOps
import net.Chidoziealways.everythingjapanese.setStreamCodecOf
import net.minecraft.network.codec.ByteBufCodecs

val JSON_ELEMENT_CODEC: Codec<JsonElement> = Codec.PASSTHROUGH.comapFlatMap(
    { dynamic ->
        val jsonDynamic = dynamic.convert(JsonOps.INSTANCE)
        val json = jsonDynamic.value
        DataResult.success(json)
    },
    { json -> Dynamic(JsonOps.INSTANCE, json) }
)

val STRING_SET_CODEC: Codec<Set<String>> =
    Codec.list(Codec.STRING)
        .xmap(
            { it.toSet() },       // Convert List -> Set when decoding
            { it.toList() }       // Convert Set -> List when encoding
        )

val STRING_SET_STREAM_CODEC = setStreamCodecOf(ByteBufCodecs.STRING_UTF8)