package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.TagAppender
import net.minecraft.data.tags.TagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.neoforged.neoforge.common.data.BlockTagsProvider
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class ModBlockTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : BlockTagsProvider(
    output,
    lookupProvider,
    JAPANESE_MOD_ID
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(JModBlocks.RAW_PYRITE_BLOCK)
            .add(JModBlocks.PYRITE_BLOCK)
            .add(JModBlocks.NEPHRITE_BLOCK)
            .add(JModBlocks.PYRITE_DEEPSLATE_ORE)
            .add(JModBlocks.PYRITE_ORE)
            .add(JModBlocks.NEPHRITE_DEEPSLATE_ORE)
            .add(JModBlocks.NEPHRITE_ORE)
            .add(JModBlocks.TRANSFORMER_BLOCK)

        tag(ModTags.Blocks.HELL_FIRE_BASE_BLOCK)
            .add(Blocks.NETHERRACK)

        tag(BlockTags.FENCES).add(JModBlocks.PYRITE_FENCE).add(JModBlocks.HINOKI_FENCE)

        tag(ModTags.Blocks.INFINIBURN_HELL)
            .addTag(BlockTags.INFINIBURN_OVERWORLD)

        tag(BlockTags.FENCE_GATES).add(JModBlocks.PYRITE_FENCE_GATE).add(JModBlocks.HINOKI_FENCE_GATE)

        tag(BlockTags.WALLS).add(JModBlocks.PYRITE_WALL)

        tag(ModTags.Blocks.NEEDS_PYRITE_TOOL)
            .add(JModBlocks.PYRITE_BLOCK)
            .add(JModBlocks.RAW_PYRITE_BLOCK)
            .add(Blocks.OBSIDIAN)
            .addTag(BlockTags.NEEDS_IRON_TOOL)

        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(JModBlocks.PYRITE_ORE)
            .add(JModBlocks.PYRITE_DEEPSLATE_ORE)
            .add(JModBlocks.PYRITE_BLOCK)
            .add(JModBlocks.TRANSFORMER_BLOCK)

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(JModBlocks.NEPHRITE_ORE)
            .add(JModBlocks.NEPHRITE_DEEPSLATE_ORE)
            .add(JModBlocks.NEPHRITE_BLOCK)

        tag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL)
            .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(JModBlocks.NEPHRITE_BLOCK)

        tag(ModTags.Blocks.INCORRECT_FOR_PYRITE_TOOL)
            .addTag(BlockTags.NEEDS_IRON_TOOL)
            .addTag(ModTags.Blocks.NEEDS_NEPHRITE_TOOL)

        tag(ModTags.Blocks.INCORRECT_FOR_NEPHRITE_TOOL)
            .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
            .replace(false)
    }

    private fun TagAppender<Block>.add(block: Block): TagAppender<Block> {
        return add(BuiltInRegistries.BLOCK.wrapAsHolder(block).key!!)
    }
}
