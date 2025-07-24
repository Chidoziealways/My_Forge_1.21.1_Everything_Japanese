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
            ModQuests.FIND_VILLAGE,
            Quest(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "find_village"),
                Component.literal("Find The Village"),
                Component.literal("Find the place where villagers roam free and happy"),
                listOf(
                    QuestStage(Component.literal("locate the village"), "locate:minecraft:village_plains"),
                    QuestStage(Component.literal("Obtain a green stone"), "collect:minecraft:emerald"),
                    QuestStage(Component.literal("Obtain green silk ※wool※"), "collect:minecraft:green_wool"),
                    QuestStage(Component.literal("Kill the bodyguard"), "kill:minecraft:iron_golem")
                ),
                listOf(
                    QuestReward(
                        "xp",
                        buildJsonObject {
                            addProperty("count", 10)
                        }
                    )
                ),
                true,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "air")
            )
        )
    }
}