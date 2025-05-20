package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, EverythingJapanese.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256485_) {
        tag(ModTags.Biomes.IS_HELL)
                .add(ModBiomes.HELL_BIOME);

        tag(ModTags.Biomes.HAS_HELL_TEMPLE)
                .addTag(ModTags.Biomes.IS_HELL);
    }
}
