package net.Chidoziealways.everythingjapanese.reload

import net.Chidoziealways.everythingjapanese.quest.QuestCapability
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimplePreparableReloadListener
import net.minecraft.util.profiling.ProfilerFiller

class QuestReloadListener(private val lookupProvider: HolderLookup.Provider): SimplePreparableReloadListener<Unit>() {
    override fun prepare(
        p0: ResourceManager,
        p1: ProfilerFiller
    ) {
        println("HELLO WORLD")
        //WE DO NOTHING
    }

    override fun apply(
        p0: Unit,
        p1: ResourceManager,
        p2: ProfilerFiller
    ) {
        println("HELLOWORLD FROM APPLY")
        val registry = lookupProvider.lookupOrThrow(ModRegistries.QUEST).listElements()

        val QUESTS = QuestCapability.QUEST_LOOKUP

        QUESTS.clear()
        for (holder in registry) {
            val quest = holder.value()
            println("Running for this quest: $quest")
            val id = holder.unwrapKey().orElseThrow().location()
            QUESTS[id] = quest
        }

        println("[EverythingJapanese] Loaded ${QUESTS.size} quests into QUEST_LOOKUP")
    }
}