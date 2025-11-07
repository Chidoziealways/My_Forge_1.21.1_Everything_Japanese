package net.Chidoziealways.everythingjapanese.worldgen

import net.minecraft.world.level.levelgen.placement.*

object JModOrePlacement {
    fun orePlacement(
        pCountPlacement: PlacementModifier,
        pHeightRange: PlacementModifier
    ): MutableList<PlacementModifier?> {
        return listOf<PlacementModifier?>(
            pCountPlacement,
            InSquarePlacement.spread(),
            pHeightRange,
            BiomeFilter.biome()
        ) as MutableList<PlacementModifier?>
    }

    fun commonOrePlacement(pCount: Int, pHeightRange: PlacementModifier): MutableList<PlacementModifier?> {
        return orePlacement(CountPlacement.of(pCount), pHeightRange)
    }

    fun rareOrePlacement(pChance: Int, pHeightRange: PlacementModifier): MutableList<PlacementModifier?> {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange)
    }
}
