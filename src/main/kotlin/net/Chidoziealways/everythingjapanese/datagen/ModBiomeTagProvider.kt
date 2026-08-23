package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class ModBiomeTagProvider(
    p_255800_: PackOutput,
    p_256205_: CompletableFuture<HolderLookup.Provider>
) : BiomeTagsProvider(p_255800_, p_256205_, JAPANESE_MOD_ID) {
    override fun addTags(p_256485_: HolderLookup.Provider) {
        tag(ModTags.Biomes.IS_HELL)
            .add(ModBiomes.HELL_BIOME)

        tag(ModTags.Biomes.HAS_HELL_TEMPLE)
            .addTag(ModTags.Biomes.IS_HELL)

        tag(ModTags.Biomes.HAS_SHINTO_SHRINE)
            .add(Biomes.CHERRY_GROVE)
            .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
            .add(Biomes.TAIGA)
            .add(Biomes.JAGGED_PEAKS)

        tag(ModTags.Biomes.HAS_SHOJI_HOUSE)
            .add(Biomes.DARK_FOREST)
            .add(Biomes.BADLANDS)
            .add(Biomes.BAMBOO_JUNGLE)
            .add(Biomes.JUNGLE)
            .add(Biomes.SPARSE_JUNGLE)

        tag(ModTags.Biomes.HAS_DOJO)
            .add(Biomes.PLAINS)
            .add(Biomes.CHERRY_GROVE)
            .add(Biomes.FOREST)
            .add(Biomes.BIRCH_FOREST)
    }
}
