package net.Chidoziealways.everythingjapanese.structure

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType

object ModStructureSets {
    var HELL_TEMPLE: ResourceKey<StructureSet?> = register("hell_temple")

    var DOJO: ResourceKey<StructureSet?> = register("dojo")

    fun bootstrap(context: BootstrapContext<StructureSet>) {
        val holdergetter = context.lookup(Registries.STRUCTURE)
        val holdergetter1 = context.lookup(Registries.BIOME)

        context.register(
            HELL_TEMPLE,
            StructureSet(
                holdergetter.getOrThrow(ModStructures.HELL_TEMPLE),
                RandomSpreadStructurePlacement(24, 8, RandomSpreadType.LINEAR, 20083232)
            )
        )

        context.register(
            DOJO,
            StructureSet(
                holdergetter.getOrThrow(ModStructures.DOJO),
                RandomSpreadStructurePlacement(24, 8, RandomSpreadType.LINEAR, 20083232)
            )
        )
    }

    private fun register(pName: String): ResourceKey<StructureSet?> {
        return ResourceKey.create<StructureSet?>(
            Registries.STRUCTURE_SET,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, pName)
        )
    }
}
