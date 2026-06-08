package net.Chidoziealways.everythingjapanese.structure.custom.shinto_shrine

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.structure.ModPools
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList

object ShintoShrineStructurePools {
    val HAIDEN = "shinto_shrine/haiden"

    fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        val holdergetter2 = context.lookup(Registries.TEMPLATE_POOL)
        val holder3: Holder<StructureTemplatePool> = holdergetter2.getOrThrow(Pools.EMPTY)
        val holdergetter = context.lookup(Registries.PROCESSOR_LIST)
        val holder: Holder<StructureProcessorList> =
            holdergetter.getOrThrow(ModProcessorLists.SHINTO_SHRINE_DEGRADATION)

        ModPools.register(context,
            HAIDEN,
            StructureTemplatePool(
                holder3,
                ImmutableList.of(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:shinto_shrine/haiden1", holder),
                        1
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
            )
    }
}