package net.Chidoziealways.everythingjapanese.structure.custom.hellTemple

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.structure.ModPools
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import java.util.function.Function

object HellTempleStructurePools {
    fun bootstrap(pContext: BootstrapContext<StructureTemplatePool?>) {
        val holdergetter1 = pContext.lookup<StructureProcessorList?>(Registries.PROCESSOR_LIST)
        val holder1: Holder<StructureProcessorList?> =
            holdergetter1.getOrThrow(ModProcessorLists.HELL_TEMPLE_START_DEGRADATION)
        val holder4: Holder<StructureProcessorList?> =
            holdergetter1.getOrThrow(ModProcessorLists.HELL_TEMPLE_COURT_DEGRADATION)
        val holdergetter2 = pContext.lookup<StructureTemplatePool?>(Registries.TEMPLATE_POOL)
        val holder3: Holder<StructureTemplatePool?> = holdergetter2.getOrThrow(Pools.EMPTY)
        ModPools.register(
            pContext,
            "hell_temple/hell_entrance",
            StructureTemplatePool(
                holder3,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>?>(
                    Pair.of<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>(
                        StructurePoolElement.empty(),
                        7
                    ),
                    Pair.of<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>(
                        StructurePoolElement.single("everythingjapanese:hell_temple/hell_entrance1", holder1),
                        10
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
        ModPools.register(
            pContext,
            "hell_temple/court",
            StructureTemplatePool(
                holder3,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>?>(
                    Pair.of<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>(
                        StructurePoolElement.empty(),
                        7
                    ),
                    Pair.of<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>(
                        StructurePoolElement.single("everythingjapanese:hell_temple/court/hell_court1", holder4),
                        10
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
    }
}