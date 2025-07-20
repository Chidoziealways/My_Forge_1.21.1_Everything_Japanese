package net.Chidoziealways.everythingjapanese

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonArray

fun buildJsonObject(builder: JsonObject.() -> Unit): JsonObject {
    val obj = JsonObject()
    obj.builder()
    return obj
}
