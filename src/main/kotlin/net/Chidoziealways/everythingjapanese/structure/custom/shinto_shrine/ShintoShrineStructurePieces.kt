package net.Chidoziealways.everythingjapanese.structure.custom.shinto_shrine

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList

object ShintoShrineStructurePieces {
    val START = Pools.createKey(
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shinto_shrine/torii_gate")
    )

    fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        val holdergetter = context.lookup(Registries.PROCESSOR_LIST)
        val holder: Holder<StructureProcessorList> =
            holdergetter.getOrThrow(ModProcessorLists.SHINTO_SHRINE_DEGRADATION)
        val holdergetter1 = context.lookup(Registries.TEMPLATE_POOL)
        val holder1: Holder<StructureTemplatePool> = holdergetter1.getOrThrow(Pools.EMPTY)
        context.register(
            START,
            StructureTemplatePool(
                holder1,
                ImmutableList.of(
                    Pair.of(
                        StructurePoolElement.single("everythingjapanese:shinto_shrine/torii", holder),
                        1
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
        ShintoShrineStructurePools.bootstrap(context)
    }
}