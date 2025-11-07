package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingcore.quest.Quest
import net.Chidoziealways.everythingcore.quest.QuestReward
import net.Chidoziealways.everythingcore.quest.QuestStage
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
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
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village"),
                Component.literal("The Village"),
                Component.literal("Find the place where villagers roam free and happy"),
                listOf(
                    QuestStage(Component.literal("Locate The Village"), ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village/find_village")),
                    QuestStage(Component.literal("Obtain a green stone"), ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village/green_stone")),
                    QuestStage(Component.literal("Obtain green silk ※wool※"), ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village/green_wool")),
                    QuestStage(Component.literal("Kill the bodyguard"), ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village/iron_golem"))
                ),
                listOf(
                    QuestReward(
                        ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village/give_xp")
                    )
                ),
                true,
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "air")
            )
        )

        context.register(
            ModQuests.DOJO,
            Quest(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo"),
                Component.literal("The Dojo"),
                Component.literal("Find the place where Samurai train to become FIERCE warriors"),
                listOf(
                    QuestStage(Component.literal("Locate The Dojo"), ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo/find_dojo"))
                ),
                listOf(
                    QuestReward(
                        ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo/give_xp")
                    ),
                    QuestReward(
                        ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo/iron")
                    )
                ),
                true,
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "village")
            )
        )

        context.register(
            ModQuests.SHOJI_HOUSE,
            Quest(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_house"),
                Component.literal("Shoji House"),
                Component.literal("This Quest deals with the Shoji House and it's many secrets"),
                listOf(
                    QuestStage(
                        Component.literal("Find the Shoji House"),
                        ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_house/find_shoji_house")
                    )
                ),
                listOf(
                    QuestReward(
                        ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_house/gun")
                    )
                ),
                true,
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "dojo")
            )
        )
    }
}