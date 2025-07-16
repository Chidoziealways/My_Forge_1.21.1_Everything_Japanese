package net.Chidoziealways.everythingjapanese.structure

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.structure.custom.hellTemple.HellTempleStructurePieces
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import java.util.function.Function

object ModPools {
    val EMPTY: ResourceKey<StructureTemplatePool?> = createKey("empty")

    fun createKey(p_368621_: ResourceLocation): ResourceKey<StructureTemplatePool?> {
        return ResourceKey.create<StructureTemplatePool?>(Registries.TEMPLATE_POOL, p_368621_)
    }

    fun createKey(p_256439_: String): ResourceKey<StructureTemplatePool?> {
        return createKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, p_256439_))
    }

    fun parseKey(p_344725_: String): ResourceKey<StructureTemplatePool?> {
        return createKey(ResourceLocation.parse(p_344725_))
    }

    fun register(
        p_335139_: BootstrapContext<StructureTemplatePool?>,
        p_255837_: String,
        p_256161_: StructureTemplatePool
    ) {
        p_335139_.register(createKey(p_255837_), p_256161_)
    }

    fun bootstrap(pContext: BootstrapContext<StructureTemplatePool?>) {
        val holdergetter = pContext.lookup<StructureTemplatePool?>(Registries.TEMPLATE_POOL)
        val holder: Holder<StructureTemplatePool?> = holdergetter.getOrThrow(EMPTY)
        pContext.register(
            EMPTY,
            StructureTemplatePool(
                holder,
                ImmutableList.of<Pair<Function<StructureTemplatePool.Projection?, out StructurePoolElement?>?, Int?>?>(),
                StructureTemplatePool.Projection.RIGID
            )
        )
        HellTempleStructurePieces.bootstrap(pContext)
    }
}
