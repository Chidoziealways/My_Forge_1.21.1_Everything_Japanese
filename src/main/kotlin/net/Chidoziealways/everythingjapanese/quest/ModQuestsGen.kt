package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.buildJsonObject
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

object ModQuestsGen {

    fun bootstrap(context: BootstrapContext<Quest>) {
        println("Bootstrapping Quests")

        context.register(
            ModQuests.VILLAGE,
            Quest(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "village"),
                Component.literal("The Village"),
                Component.literal("Find the place where villagers roam free and happy"),
                listOf(
                    QuestStage(Component.literal("Locate The Village"), "locate:minecraft:village_plains"),
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

        context.register(
            ModQuests.DOJO,
            Quest(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "dojo"),
                Component.literal("The Dojo"),
                Component.literal("Find the place where Samurai train to become FIERCE warriors"),
                listOf(
                    QuestStage(Component.literal("Locate The Dojo"), "locate:everythingjapanese:dojo")
                ),
                listOf(
                    QuestReward(
                        "xp",
                        buildJsonObject {
                            addProperty("count", 30)
                        }
                    ),
                    QuestReward(
                        "item",
                        buildJsonObject {
                            addProperty("id", "minecraft:iron")
                            addProperty("count", 65)
                        }
                    )
                ),
                true,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "village")
            )
        )
    }
}