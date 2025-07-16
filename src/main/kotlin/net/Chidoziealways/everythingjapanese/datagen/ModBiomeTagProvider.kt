package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.KeyTagProvider
import net.minecraft.world.level.biome.Biome
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModBiomeTagProvider(
    p_255800_: PackOutput,
    p_256205_: CompletableFuture<HolderLookup.Provider?>,
    existingFileHelper: ExistingFileHelper?
) : KeyTagProvider<Biome?>(p_255800_, Registries.BIOME, p_256205_, MOD_ID, existingFileHelper) {
    override fun addTags(p_256485_: HolderLookup.Provider) {
        tag(ModTags.Biomes.IS_HELL)
            .add(ModBiomes.HELL_BIOME)

        tag(ModTags.Biomes.HAS_HELL_TEMPLE)
            .addTag(ModTags.Biomes.IS_HELL)
    }
}
