package net.Chidoziealways.everythingjapanese.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.advancements.AdvancementProvider
import java.util.concurrent.CompletableFuture

object ModAdvancementProvider {
    fun create(
        pOutput: PackOutput,
        pRegistries: CompletableFuture<HolderLookup.Provider>
    ): AdvancementProvider {
        return AdvancementProvider(
            pOutput,
            pRegistries,
            listOf(
                HellAdvancementProvider(),
                StoryAdvancementProvider(),
                AdventureAdvancementProvider()
            )
        )
    }
}
