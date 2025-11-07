package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingcore.Everythingcore
import net.Chidoziealways.everythingcore.quest.Quest
import net.Chidoziealways.everythingcore.util.SharedRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object ModQuests {
    val VILLAGE: ResourceKey<Quest> = createKey("village")
    val DOJO: ResourceKey<Quest> = createKey("dojo")

    val SHOJI_HOUSE: ResourceKey<Quest> = createKey("shoji_house")

    private fun createKey(name: String): ResourceKey<Quest> {
        return ResourceKey.create(
            SharedRegistries.QUEST,
            ResourceLocation.fromNamespaceAndPath(Everythingcore.ID, name)
        )
    }
}