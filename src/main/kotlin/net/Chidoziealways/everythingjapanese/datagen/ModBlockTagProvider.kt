package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class ModBlockTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Block>(
    output,
    Registries.BLOCK,
    lookupProvider,
    Function { block: Block? -> block!!.builtInRegistryHolder().key() },
    MOD_ID
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.RAW_PYRITE_BLOCK)
            .add(ModBlocks.PYRITE_BLOCK)
            .add(ModBlocks.NEPHRITE_BLOCK)
            .add(ModBlocks.PYRITE_DEEPSLATE_ORE)
            .add(ModBlocks.PYRITE_ORE)
            .add(ModBlocks.NEPHRITE_DEEPSLATE_ORE)
            .add(ModBlocks.NEPHRITE_ORE)
            .add(ModBlocks.TRANSFORMER_BLOCK)

        tag(ModTags.Blocks.HELL_FIRE_BASE_BLOCK)
            .add(Blocks.NETHERRACK)

        tag(BlockTags.FENCES).add(ModBlocks.PYRITE_FENCE)

        tag(ModTags.Blocks.INFINIBURN_HELL)
            .addTag(BlockTags.INFINIBURN_OVERWORLD)

        tag(BlockTags.FENCE_GATES).add(ModBlocks.PYRITE_FENCE_GATE)

        tag(BlockTags.WALLS).add(ModBlocks.PYRITE_WALL)

        tag(ModTags.Blocks.NEEDS_PYRITE_TOOL)
            .add(ModBlocks.PYRITE_BLOCK)
            .add(ModBlocks.RAW_PYRITE_BLOCK)
            .add(Blocks.OBSIDIAN)
            .addTag(BlockTags.NEEDS_IRON_TOOL)

        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.PYRITE_ORE)
            .add(ModBlocks.PYRITE_DEEPSLATE_ORE)
            .add(ModBlocks.PYRITE_BLOCK)
            .add(ModBlocks.TRANSFORMER_BLOCK)

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.NEPHRITE_ORE)
            .add(ModBlocks.NEPHRITE_DEEPSLATE_ORE)
            .add(ModBlocks.NEPHRITE_BLOCK)

        tag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL)
            .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.NEPHRITE_BLOCK)

        tag(ModTags.Blocks.INCORRECT_FOR_PYRITE_TOOL)
            .addTag(BlockTags.NEEDS_IRON_TOOL)
            .addTag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL)

        tag(ModTags.Blocks.INCORRECT_FOR_NEPHRITE_TOOL)
            .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
            .replace(false)

        tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.HINOKI_MARUTA)
            .add(ModBlocks.HINOKI_MOKUZAI)
            .add(ModBlocks.STRIPPED_HINOKI_MARUTA)
            .add(ModBlocks.STRIPPED_HINOKI_MOKUZAI)
    }
}
