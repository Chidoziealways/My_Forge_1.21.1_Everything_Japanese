package net.Chidoziealways.everythingjapanese.quest

import com.google.gson.JsonElement
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.buildJsonObject
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

object ModQuestsGen {

    fun bootstrap(context: BootstrapContext<Quest>) {
        context.register(
            ModQuests.FIND_NPC1,
            Quest(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "find_npc1"),
                Component.literal("Find NPC 1"),
                Component.literal("find the first npc hidden somewhere in this blocky world"),
                listOf(
                    QuestStage(Component.literal("locate the village"), "collect:minecraft:emerald")
                ),
                listOf(
                    QuestReward(
                        "item",
                        buildJsonObject {
                            addProperty("id", "minecraft:diamond")
                            addProperty("count", 5)
                        }
                    )
                ),
                true
            )
        )
    }
}