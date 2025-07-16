package net.Chidoziealways.everythingjapanese.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.ForgeAdvancementProvider
import net.minecraftforge.common.data.ForgeAdvancementProvider.AdvancementGenerator
import java.util.List
import java.util.concurrent.CompletableFuture

object ModAdvancementProvider {
    fun create(
        pOutput: PackOutput,
        pRegistries: CompletableFuture<HolderLookup.Provider?>,
        fileHelper: ExistingFileHelper
    ): ForgeAdvancementProvider {
        return ForgeAdvancementProvider(
            pOutput,
            pRegistries,
            fileHelper,
            List.of<AdvancementGenerator?>(
                HellAdvancementProvider(),
                StoryAdvancementProvider()
            )
        )
    }
}
