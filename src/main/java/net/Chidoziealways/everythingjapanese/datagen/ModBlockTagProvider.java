package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EverythingJapanese.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_PYRITE_BLOCK.get())
                .add(ModBlocks.PYRITE_BLOCK.get())
                .add(ModBlocks.NEPHRITE_BLOCK.get())
                .add(ModBlocks.PYRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.PYRITE_ORE.get())
                .add(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.NEPHRITE_ORE.get())
                .add(ModBlocks.TRANSFORMER_BLOCK.get());


        tag(BlockTags.FENCES).add(ModBlocks.PYRITE_FENCE.get());

        tag(ModTags.Blocks.INFINIBURN_HELL)
                .addTag(BlockTags.INFINIBURN_OVERWORLD);

        tag(BlockTags.FENCE_GATES).add(ModBlocks.PYRITE_FENCE_GATE.get());

        tag(BlockTags.WALLS).add(ModBlocks.PYRITE_WALL.get());

        tag(ModTags.Blocks.NEEDS_PYRITE_TOOL)
                .add(ModBlocks.PYRITE_BLOCK.get())
                .add(ModBlocks.RAW_PYRITE_BLOCK.get())
                .add(Blocks.OBSIDIAN)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PYRITE_ORE.get())
                .add(ModBlocks.PYRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.PYRITE_BLOCK.get())
                .add(ModBlocks.TRANSFORMER_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NEPHRITE_ORE.get())
                .add(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.NEPHRITE_BLOCK.get());

        tag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NEPHRITE_BLOCK.get());

        tag(ModTags.Blocks.INCORRECT_FOR_PYRITE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_NEPHRITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .replace(false);

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.HINOKI_MARUTA.get())
                .add(ModBlocks.HINOKI_MOKUZAI.get())
                .add(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                .add(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get());

   }
}
