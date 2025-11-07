package net.Chidoziealways.everythingjapanese.worldgen.tree

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.worldgen.JModConfiguredFeatures
import net.minecraft.world.level.block.grower.TreeGrower
import java.util.*

object ModTreeGrowers {
    val HINOKI: TreeGrower = TreeGrower(
        "$JAPANESE_MOD_ID:hinoki",
        Optional.empty(),
        Optional.of(JModConfiguredFeatures.HINOKI_KEY),
        Optional.empty()
    )
}
