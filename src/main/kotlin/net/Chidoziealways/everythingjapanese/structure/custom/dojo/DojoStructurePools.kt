package net.Chidoziealways.everythingjapanese.structure.custom.dojo

import com.google.common.collect.ImmutableList
import net.Chidoziealways.everythingjapanese.structure.ModPools
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.minecraft.core.Holder
import com.mojang.datafixers.util.Pair
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import java.util.function.Function

object DojoStructurePools {

    fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        val holdergetter1 = context.lookup(Registries.PROCESSOR_LIST)
        val holder1: Holder<StructureProcessorList> =
            holdergetter1.getOrThrow(ModProcessorLists.HELL_TEMPLE_START_DEGRADATION)
        val holder4: Holder<StructureProcessorList> =
            holdergetter1.getOrThrow(ModProcessorLists.HELL_TEMPLE_COURT_DEGRADATION)
        val holdergetter2 = context.lookup(Registries.TEMPLATE_POOL)
        val holder3: Holder<StructureTemplatePool> = holdergetter2.getOrThrow(Pools.EMPTY)
        ModPools.register(
            context,
            "dojo/hallway",
            StructureTemplatePool(
                holder3,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>>(
                    Pair.of<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>(
                        StructurePoolElement.single("everythingjapanese:dojo/hallway", holder1),
                        10
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )

        ModPools.register(
            context,
            "dojo/extras",
            StructureTemplatePool(
                holder3,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>>(
                    Pair.of<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>(
                        StructurePoolElement.single("everythingjapanese:dojo/hallway", holder1),
                        5
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
    }
}