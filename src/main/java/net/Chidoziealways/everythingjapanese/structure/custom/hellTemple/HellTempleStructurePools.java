package net.Chidoziealways.everythingjapanese.structure.custom.hellTemple;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class HellTempleStructurePools {
    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        HolderGetter<StructureProcessorList> holdergetter1 = pContext.lookup(Registries.PROCESSOR_LIST);
        Holder<StructureProcessorList> holder1 = holdergetter1.getOrThrow(ProcessorLists.ANCIENT_CITY_GENERIC_DEGRADATION);
        HolderGetter<StructureTemplatePool> holdergetter2 = pContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder3 = holdergetter2.getOrThrow(Pools.EMPTY);
        Pools.register(
                pContext,
                "hell_temple/structures",
                new StructureTemplatePool(
                        holder3,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.empty(), 7),
                                Pair.of(StructurePoolElement.single("everythingjapanese:hell_temple/hell_temple", holder1), 4)
                                ),
                        StructureTemplatePool.Projection.RIGID
                )
        );
    }
}
