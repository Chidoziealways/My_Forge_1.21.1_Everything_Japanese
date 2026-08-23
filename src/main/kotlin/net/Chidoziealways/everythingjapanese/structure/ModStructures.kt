package net.Chidoziealways.everythingjapanese.structure

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.structure.Structure

object ModStructures {
    val HELL_TEMPLE: ResourceKey<Structure> = createKey("hell_temple")

    val DOJO: ResourceKey<Structure> = createKey("dojo")

    val SHOJI_HOUSE: ResourceKey<Structure> = createKey("shoji_house")

    val SHINTO_SHRINE = createKey("shinto_shrine")

    val TEA_HOUSE = createKey("tea_house")

    private fun createKey(pName: String): ResourceKey<Structure> {
        return ResourceKey.create(
            Registries.STRUCTURE,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, pName)
        )
    }
}
