package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EverythingJapanese.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.RAW_PYRITE_BLOCK.get())
            .add(ModBlocks.PYRITE_BLOCK.get())
            .add(ModBlocks.PYRITE_DEEPSLATE_ORE.get())
            .add(ModBlocks.PYRITE_ORE.get());

   tag(BlockTags.NEEDS_DIAMOND_TOOL)
          .add(ModBlocks.PYRITE_ORE.get())
           .add(ModBlocks.RAW_PYRITE_BLOCK.get());
   }
}
