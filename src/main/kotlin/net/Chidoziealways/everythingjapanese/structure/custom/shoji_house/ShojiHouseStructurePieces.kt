package net.Chidoziealways.everythingjapanese.structure.custom.shoji_house

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import java.util.function.Function

object ShojiHouseStructurePieces {
    val START: ResourceKey<StructureTemplatePool> =
        Pools.createKey(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_house/pathway"))

    fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        val holdergetter1 = context.lookup(Registries.TEMPLATE_POOL)
        val holder1: Holder<StructureTemplatePool> = holdergetter1.getOrThrow(Pools.EMPTY)
        context.register(
            START,
            StructureTemplatePool(
                holder1,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>>(
                    Pair.of<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>(
                        StructurePoolElement.single("everythingjapanese:shoji_house/stone_path"),
                        1
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
        ShojiHouseStructurePools.bootstrap(context)
    }
}
