package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingcore.capabilities.SharedCapabilities
import net.Chidoziealways.everythingcore.quest.Quest
import net.Chidoziealways.everythingcore.quest.QuestReward
import net.Chidoziealways.everythingcore.quest.QuestStage
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.buildJsonObject
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier

object ModQuestsGen {

    fun bootstrap(context: BootstrapContext<Quest>) {
        println("Bootstrapping Quests")
        context.register(
            ModQuests.VILLAGE,
            Quest(
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "village"),
                Component.literal("The Village"),
                Component.literal("Find the place where villagers roam free and happy"),
                listOf(
                    QuestStage(Component.literal("Locate The Village"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "find_village")),
                    QuestStage(Component.literal("Obtain a green stone"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "green_stone")),
                    QuestStage(Component.literal("Obtain green silk ※wool※"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "green_wool")),
                    QuestStage(Component.literal("Kill the bodyguard"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "iron_golem"))
                ),
                listOf(
                    QuestReward(
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "give_xp")
                    )
                ),
                true,
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "air")
            )
        )

        context.register(
            ModQuests.DOJO,
            Quest(
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo"),
                Component.literal("The Dojo"),
                Component.literal("Find the place where Samurai train to become FIERCE warriors"),
                listOf(
                    QuestStage(Component.literal("Locate The Dojo"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "find_dojo"))
                ),
                listOf(
                    QuestReward(
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "give_xp")
                    ),
                    QuestReward(
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "iron")
                    )
                ),
                true,
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "village")
            )
        )

        context.register(
            ModQuests.SHOJI_HOUSE,
            Quest(
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_house"),
                Component.literal("Shoji House"),
                Component.literal("This Quest deals with the Shoji House and it's many secrets"),
                listOf(
                    QuestStage(
                        Component.literal("Find the Shoji House"),
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "find_shoji_house")
                    )
                ),
                listOf(
                    QuestReward(
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "gun")
                    )
                ),
                true,
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo")
            )
        )
    }
}