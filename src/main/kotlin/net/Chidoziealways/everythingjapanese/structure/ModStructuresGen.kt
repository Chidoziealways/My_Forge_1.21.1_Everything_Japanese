package net.Chidoziealways.everythingjapanese.structure

import net.Chidoziealways.everythingjapanese.structure.custom.dojo.DojoStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.hellTemple.HellTempleStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.shinto_shrine.ShintoShrineStructurePieces
import net.Chidoziealways.everythingjapanese.structure.custom.shoji_house.ShojiHouseStructurePieces
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.Map

object ModStructuresGen {
    private val log: Logger = LoggerFactory.getLogger(ModStructuresGen::class.java)

    fun bootstrap(context: BootstrapContext<Structure>) {
        val holdergetter = context.lookup(Registries.BIOME)
        val holdergetter1 = context.lookup(Registries.TEMPLATE_POOL)
        log.info("Bootstrapping Structures: {}", ModStructures.HELL_TEMPLE.registry())
        context.register(
            ModStructures.HELL_TEMPLE,
            JigsawStructure(
                StructureSettings.Builder(holdergetter.getOrThrow(ModTags.Biomes.HAS_HELL_TEMPLE))
                    .spawnOverrides(
                        Map.of<MobCategory?, StructureSpawnOverride?>(
                            MobCategory.MONSTER,
                            StructureSpawnOverride(
                                StructureSpawnOverride.BoundingBoxType.PIECE,
                                NetherFortressStructure.FORTRESS_ENEMIES
                            )
                        )
                    )
                    .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .build(),
                holdergetter1.getOrThrow(HellTempleStructurePieces.START),
                Optional.empty<Identifier?>(),
                7,
                ConstantHeight.of(VerticalAnchor.absolute(-27)),
                false,
                Optional.empty<Heightmap.Types?>(),
                JigsawStructure.MaxDistance(116),
                mutableListOf<PoolAliasBinding>(),
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                JigsawStructure.DEFAULT_LIQUID_SETTINGS
            )
        )

        context.register(
            ModStructures.DOJO,
            JigsawStructure(
                StructureSettings.Builder(holdergetter.getOrThrow(ModTags.Biomes.HAS_DOJO))
                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                    .build(),
                holdergetter1.getOrThrow(DojoStructurePieces.START),
                6,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
            )
        )

        context.register(
            ModStructures.SHOJI_HOUSE,
            JigsawStructure(
                StructureSettings.Builder(holdergetter.getOrThrow(ModTags.Biomes.HAS_SHOJI_HOUSE))
                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                    .build(),
                holdergetter1.getOrThrow(ShojiHouseStructurePieces.START),
                19,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
            )
        )

        context.register(
            ModStructures.SHINTO_SHRINE,
            JigsawStructure(
                StructureSettings.Builder(holdergetter.getOrThrow(ModTags.Biomes.HAS_SHINTO_SHRINE))
                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                    .build(),
                holdergetter1.getOrThrow(ShintoShrineStructurePieces.START),
                19,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
            )
        )

        log.info("Finished with Bootstrap")
    }
}
