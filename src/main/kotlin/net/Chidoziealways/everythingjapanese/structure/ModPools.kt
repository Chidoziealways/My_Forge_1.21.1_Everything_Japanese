package net.Chidoziealways.everythingjapanese.structure

import com.google.common.collect.ImmutableList
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.structure.custom.dojo.DojoStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.hellTemple.HellTempleStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.shinto_shrine.ShintoShrineStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.shoji_house.ShojiHouseStructurePieces
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool

object ModPools {
    val EMPTY: ResourceKey<StructureTemplatePool> = createKey("empty")

    fun createKey(p_368621_: Identifier): ResourceKey<StructureTemplatePool> {
        return ResourceKey.create(Registries.TEMPLATE_POOL, p_368621_)
    }

    fun createKey(p_256439_: String): ResourceKey<StructureTemplatePool> {
        return createKey(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, p_256439_))
    }

    fun parseKey(p_344725_: String): ResourceKey<StructureTemplatePool> {
        return createKey(Identifier.parse(p_344725_))
    }

    fun register(
        p_335139_: BootstrapContext<StructureTemplatePool>,
        p_255837_: String,
        p_256161_: StructureTemplatePool
    ) {
        p_335139_.register(createKey(p_255837_), p_256161_)
    }

    fun bootstrap(pContext: BootstrapContext<StructureTemplatePool>) {
        val holdergetter = pContext.lookup(Registries.TEMPLATE_POOL)
        val holder: Holder<StructureTemplatePool> = holdergetter.getOrThrow(EMPTY)
        pContext.register(
            EMPTY,
            StructureTemplatePool(
                holder,
                ImmutableList.of(),
                StructureTemplatePool.Projection.RIGID
            )
        )
        HellTempleStructurePieces.bootstrap(pContext)
        DojoStructurePieces.bootstrap(pContext)
        ShojiHouseStructurePieces.bootstrap(pContext)
        ShintoShrineStructurePieces.bootstrap(pContext)
    }
}
