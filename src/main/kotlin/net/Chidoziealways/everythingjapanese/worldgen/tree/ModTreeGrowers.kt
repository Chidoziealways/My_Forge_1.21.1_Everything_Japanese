package net.Chidoziealways.everythingjapanese.worldgen.tree

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.worldgen.ModConfiguredFeatures
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import java.util.*

object ModTreeGrowers {
    val HINOKI: TreeGrower = TreeGrower(
        "$MOD_ID:hinoki",
        Optional.empty(),
        Optional.of(ModConfiguredFeatures.HINOKI_KEY),
        Optional.empty()
    )
}
