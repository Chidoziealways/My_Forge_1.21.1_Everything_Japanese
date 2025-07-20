package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object ModQuests {
    val FIND_NPC1: ResourceKey<Quest> = createKey("find_npc1")

    private fun createKey(name: String): ResourceKey<Quest> {
        return ResourceKey.create(
            ModRegistries.QUEST,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
        )
    }
}