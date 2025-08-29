package net.Chidoziealways.everythingjapanese.structure

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.structure.Structure

object ModStructures {
    val HELL_TEMPLE: ResourceKey<Structure?> = createKey("hell_temple")

    val DOJO: ResourceKey<Structure?> = createKey("dojo")

    private fun createKey(pName: String): ResourceKey<Structure?> {
        return ResourceKey.create<Structure?>(
            Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, pName)
        )
    }
}
