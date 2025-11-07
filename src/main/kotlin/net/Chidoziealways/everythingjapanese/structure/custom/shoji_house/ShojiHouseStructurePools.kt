package net.Chidoziealways.everythingjapanese.structure.custom.shoji_house

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.structure.ModPools
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool

object ShojiHouseStructurePools {

    fun bootstrap(pContext: BootstrapContext<StructureTemplatePool>) {
        val holdergetter2 = pContext.lookup(Registries.TEMPLATE_POOL)
        val holder3: Holder<StructureTemplatePool> = holdergetter2.getOrThrow(Pools.EMPTY)

        ModPools.register(pContext,
            "shoji_house/yamazaki_gardens",
            StructureTemplatePool(
                holder3,
                ImmutableList.of(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:shoji_house/yamazaki_garden"),
                        10
                    )
                ),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
            )
        )
        ModPools.register(pContext,
            "shoji_house/rice_gardens",
            StructureTemplatePool(
                holder3,
                ImmutableList.of(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:shoji_house/rice_garden"),
                        10
                    )
                ),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
            )
        )
        ModPools.register(pContext,
            "shoji_house/house",
            StructureTemplatePool(
                holder3,
                ImmutableList.of(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:shoji_house/main_house"),
                        10
                    )
                ),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
            )
        )
    }
}