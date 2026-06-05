package net.Chidoziealways.everythingjapanese.structure

import com.google.common.collect.ImmutableList
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.templatesystem.*

object ModProcessorLists {
    private val EMPTY: ResourceKey<StructureProcessorList> = createKey("empty")
    val HELL_TEMPLE_START_DEGRADATION: ResourceKey<StructureProcessorList> = createKey("hell_temple_start_degradation")
    val HELL_TEMPLE_COURT_DEGRADATION: ResourceKey<StructureProcessorList> = createKey("hell_temple_court_degradation")

    val SHINTO_SHRINE_DEGRADATION = createKey("shinto_shrine_torii_degradation")

    private fun createKey(pName: String): ResourceKey<StructureProcessorList> {
        return ResourceKey.create(
            Registries.PROCESSOR_LIST,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, pName)
        )
    }

    private fun register(
        pContext: BootstrapContext<StructureProcessorList>,
        pKey: ResourceKey<StructureProcessorList>,
        pProcessors: MutableList<StructureProcessor>
    ) {
        pContext.register(pKey, StructureProcessorList(pProcessors))
    }

    fun bootstrap(pContext: BootstrapContext<StructureProcessorList>) {
        val holdergetter = pContext.lookup(Registries.BLOCK)
        val processorrule = ProcessorRule(
            RandomBlockMatchTest(Blocks.BLACKSTONE, 0.01f),
            AlwaysTrueTest.INSTANCE,
            Blocks.GILDED_BLACKSTONE.defaultBlockState()
        )
        val processorrule1 = ProcessorRule(
            RandomBlockMatchTest(Blocks.GILDED_BLACKSTONE, 0.5f),
            AlwaysTrueTest.INSTANCE,
            Blocks.BLACKSTONE.defaultBlockState()
        )
        register(pContext, EMPTY, ImmutableList.of<StructureProcessor>())

        register(
            pContext,
            SHINTO_SHRINE_DEGRADATION,
            ImmutableList.of(
                RuleProcessor(
                    ImmutableList.of(
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.STONE, 0.4f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.COBBLESTONE.defaultBlockState()
                        ),
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.STONE_BRICKS, 0.2f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.CRACKED_STONE_BRICKS.defaultBlockState()
                        )
                    )
                ),
                ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
            )
        )

        register(
            pContext,
            HELL_TEMPLE_COURT_DEGRADATION,
            ImmutableList.of<StructureProcessor>(
                RuleProcessor(
                    ImmutableList.of( // ⚒️ Cracked Deepslate Tiles (30% chance)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.DEEPSLATE_TILES, 0.3f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState()
                        ),  // 🧱 Remove bricks (10% chance)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.DEEPSLATE_BRICKS, 0.1f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.AIR.defaultBlockState()
                        ),  // 🌿 Turn bricks mossy (20%)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.2f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.MOSSY_COBBLESTONE.defaultBlockState()
                        ),  // 🔥 Basalt corruption (random blackstone)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.BASALT, 0.25f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.BLACKSTONE.defaultBlockState()
                        ),  // 💀 Curse with soul sand (5% floor tiles)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.STONE_BRICKS, 0.05f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.SOUL_SAND.defaultBlockState()
                        ),  // 🕸️ Add cobwebs for spice (only 5%)
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.AIR, 0.05f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.COBWEB.defaultBlockState()
                        )
                    )
                ),
                ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
            )
        )

        register(
            pContext,
            HELL_TEMPLE_START_DEGRADATION,
            ImmutableList.of(
                RuleProcessor(
                    ImmutableList.of(
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.DEEPSLATE_BRICKS, 0.3f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState()
                        ),
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.DEEPSLATE_TILES, 0.3f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState()
                        ),
                        ProcessorRule(
                            RandomBlockMatchTest(Blocks.SOUL_LANTERN, 0.05f),
                            AlwaysTrueTest.INSTANCE,
                            Blocks.AIR.defaultBlockState()
                        )
                    )
                ),
                ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
            )
        )
    }
}
