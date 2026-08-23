package net.Chidoziealways.everythingjapanese.structure.custom.teaHouse

import com.google.common.collect.ImmutableList
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement

object TeaHouseStructure {
    val START = Pools.createKey(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "tea_house/tea_gardens"))
    val HOUSE = Pools.createKey(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "tea_house/tea_houses"))

    fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        val processors = context.lookup(Registries.PROCESSOR_LIST)
        val garden_processor = processors.getOrThrow(ModProcessorLists.TEA_GARDEN_DEGRADATION)
        val pools = context.lookup(Registries.TEMPLATE_POOL)
        val fallback = pools.getOrThrow(Pools.EMPTY)
        context.register(
            START,
            StructureTemplatePool(
                fallback,
                listOf(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:tea_house/tea_garden", garden_processor),
                        1
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
        context.register(
            HOUSE,
            StructureTemplatePool(
                fallback,
                listOf(
                    Pair.of(
                        StructurePoolElement.single("$JAPANESE_MOD_ID:tea_house/tea_house"),
                        1
                    )
                ),
                StructureTemplatePool.Projection.RIGID
            )
        )
    }
}