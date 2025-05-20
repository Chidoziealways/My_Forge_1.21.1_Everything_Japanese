package net.Chidoziealways.everythingjapanese.structure;

import com.google.common.collect.ImmutableList;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;

public class ModProcessorLists {
    private static final ResourceKey<StructureProcessorList> EMPTY = createKey("empty");
    public static final ResourceKey<StructureProcessorList> HELL_TEMPLE_START_DEGRADATION = createKey("hell_temple_start_degradation");

    private static ResourceKey<StructureProcessorList> createKey(String pName) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }

    private static void register(
            BootstrapContext<StructureProcessorList> pContext, ResourceKey<StructureProcessorList> pKey, List<StructureProcessor> pProcessors
    ) {
        pContext.register(pKey, new StructureProcessorList(pProcessors));
    }

    public static void bootstrap(BootstrapContext<StructureProcessorList> pContext) {
        HolderGetter<Block> holdergetter = pContext.lookup(Registries.BLOCK);
        ProcessorRule processorrule = new ProcessorRule(new RandomBlockMatchTest(Blocks.BLACKSTONE, 0.01F), AlwaysTrueTest.INSTANCE, Blocks.GILDED_BLACKSTONE.defaultBlockState());
        ProcessorRule processorrule1 = new ProcessorRule(new RandomBlockMatchTest(Blocks.GILDED_BLACKSTONE, 0.5F), AlwaysTrueTest.INSTANCE, Blocks.BLACKSTONE.defaultBlockState());
        register(pContext, EMPTY, ImmutableList.of());

        register(
                pContext,
                HELL_TEMPLE_START_DEGRADATION,
                ImmutableList.of(
                        new RuleProcessor(
                                ImmutableList.of(
                                        new ProcessorRule(new RandomBlockMatchTest(Blocks.DEEPSLATE_BRICKS, 0.3F), AlwaysTrueTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState()),
                                        new ProcessorRule(new RandomBlockMatchTest(Blocks.DEEPSLATE_TILES, 0.3F), AlwaysTrueTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState()),
                                        new ProcessorRule(new RandomBlockMatchTest(Blocks.SOUL_LANTERN, 0.05F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
                                )
                        ),
                        new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
                )
        );
    }
}
