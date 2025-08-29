package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.KeyTagProvider
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class ModBiomeTagProvider(
    p_255800_: PackOutput,
    p_256205_: CompletableFuture<HolderLookup.Provider?>
) : KeyTagProvider<Biome?>(p_255800_, Registries.BIOME, p_256205_, MOD_ID) {
    override fun addTags(p_256485_: HolderLookup.Provider) {
        tag(ModTags.Biomes.IS_HELL)
            .add(ModBiomes.HELL_BIOME)

        tag(ModTags.Biomes.HAS_HELL_TEMPLE)
            .addTag(ModTags.Biomes.IS_HELL)

        tag(ModTags.Biomes.HAS_DOJO)
            .add(Biomes.PLAINS)
            .add(Biomes.CHERRY_GROVE)
            .add(Biomes.DARK_FOREST)
            .add(Biomes.FOREST)
            .add(Biomes.BIRCH_FOREST)
    }
}
