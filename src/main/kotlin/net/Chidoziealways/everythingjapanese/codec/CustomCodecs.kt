package net.Chidoziealways.everythingjapanese.codec

import com.google.gson.JsonElement
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.Dynamic
import com.mojang.serialization.JsonOps

val JSON_ELEMENT_CODEC: Codec<JsonElement> = Codec.PASSTHROUGH.comapFlatMap(
    { dynamic ->
        val jsonDynamic = dynamic.convert(JsonOps.INSTANCE)
        val json = jsonDynamic.value
        DataResult.success(json)
    },
    { json -> Dynamic(JsonOps.INSTANCE, json) }
)